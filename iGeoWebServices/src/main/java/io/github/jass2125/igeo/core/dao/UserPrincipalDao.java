/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.dao;

import io.github.jass2125.igeo.core.entity.Count;
import io.github.jass2125.igeo.core.entity.Count_;
import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.entity.UserPrincipal_;
import io.github.jass2125.igeo.core.entity.enums.Status;
import io.github.jass2125.igeo.core.exceptions.EntityException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 27, 2017 12:56:57 PM
 */
@Stateless
public class UserPrincipalDao {

    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;

    private CriteriaQuery<UserPrincipal> criteriaQueryUserPrincipal;
    private CriteriaQuery<Count> criteriaQueryCount;

    private Root<UserPrincipal> rootUserPrincipal;
    private Root<Count> rootCount;

    @PostConstruct
    public void init() {
        this.criteriaBuilder = em.getCriteriaBuilder();
        this.criteriaQueryUserPrincipal = criteriaBuilder.createQuery(UserPrincipal.class);
        this.criteriaQueryCount = criteriaBuilder.createQuery(Count.class);
        this.rootUserPrincipal = criteriaQueryUserPrincipal.from(UserPrincipal.class);
        this.rootCount = criteriaQueryCount.from(Count.class);
    }

    @PreDestroy
    public void onDestroy() {
        this.em = null;
        this.criteriaBuilder = null;
        this.criteriaQueryUserPrincipal = null;
        this.criteriaQueryCount = null;
        this.rootUserPrincipal = null;
        this.rootCount = null;
    }

    public UserPrincipal login(String email, String password) throws EntityException {
        try {
            Count count = searchUserPrincipalByEmail(email);
            if (count != null) {
                Join<UserPrincipal, Count> join = rootUserPrincipal.join(UserPrincipal_.count);

                this.criteriaQueryUserPrincipal.
                        multiselect(
                                rootUserPrincipal.get(UserPrincipal_.id),
                                rootUserPrincipal.get(UserPrincipal_.name),
                                rootUserPrincipal.get(UserPrincipal_.profileStatus));

                Predicate equalEmail = criteriaBuilder.equal(join.get(Count_.email), email);
                Predicate equalPassword = criteriaBuilder.equal(join.get(Count_.password), password);
                Predicate equalProfileStatus = criteriaBuilder.equal(rootUserPrincipal.get(UserPrincipal_.profileStatus), Status.ACTIVE);

                this.criteriaQueryUserPrincipal.where(equalEmail, equalPassword, equalProfileStatus);

                UserPrincipal user = em.createQuery(this.criteriaQueryUserPrincipal).getSingleResult();
                user.setCount(count);
                return user;
            }
            return null;
        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
            throw new EntityException(e, "Verifique os dados e tente novamente!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntityException(e, "Ocorreu um erro inesperado!");
        }
    }

    public UserPrincipal save(UserPrincipal userPrincipal) throws EntityException {
        try {
            em.persist(userPrincipal.getCount());
            em.persist(userPrincipal);
            return userPrincipal;
        } catch (Exception e) {
            throw new EntityException(e, "Erro ao salvar entidade");
        }
    }

    public UserPrincipal searchById(Long id) throws EntityException {
        try {
            Join<UserPrincipal, Count> join = this.rootUserPrincipal.join(UserPrincipal_.count);
            this.criteriaQueryUserPrincipal.multiselect(
                    rootUserPrincipal.get(UserPrincipal_.id),
                    rootUserPrincipal.get(UserPrincipal_.phone),
                    rootUserPrincipal.get(UserPrincipal_.name),
                    rootUserPrincipal.get(UserPrincipal_.birthday),
                    rootUserPrincipal.get(UserPrincipal_.profileStatus),
                    join.get(Count_.id),
                    join.get(Count_.email),
                    join.get(Count_.password));
            Predicate equalId = criteriaBuilder.equal(rootUserPrincipal.get(UserPrincipal_.id), id);
            this.criteriaQueryUserPrincipal.where(equalId);
            return this.em.createQuery(this.criteriaQueryUserPrincipal).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntityException(e, "Erro ao buscar entidade");
        }
    }

    public UserPrincipal update(UserPrincipal userPrincipal) throws EntityException {
        try {
            return em.merge(userPrincipal);
        } catch (Exception e) {
            throw new EntityException(e, "Não foi possível atualizar UserPrincipal");
        }
    }

    public Count searchUserPrincipalByEmail(String email) throws EntityException {
        try {
            this.criteriaQueryCount
                    .select(rootCount)
                    .where(criteriaBuilder.equal(criteriaBuilder.lower(rootCount.get(Count_.email)), email.toLowerCase()));
            return em.createQuery(this.criteriaQueryCount).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntityException(e, "Não existe");
        }
    }
}
