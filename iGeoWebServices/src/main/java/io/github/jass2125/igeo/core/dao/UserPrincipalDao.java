/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.dao;

import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.entity.UserPrincipal_;
import io.github.jass2125.igeo.core.entity.enums.Status;
import io.github.jass2125.igeo.core.exceptions.EntityException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    private CriteriaQuery<UserPrincipal> criteriaQuery;
    private Root<UserPrincipal> root;

    @PostConstruct
    public void init() {
        this.criteriaBuilder = em.getCriteriaBuilder();
        this.criteriaQuery = criteriaBuilder.createQuery(UserPrincipal.class);
        this.root = criteriaQuery.from(UserPrincipal.class);
    }

    @PreDestroy
    public void onDestroy() {
        this.criteriaBuilder = null;
        this.criteriaQuery = null;
        this.root = null;
    }

    public UserPrincipal login(String email, String password) throws EntityException {
        try {
//            Join<UserPrincipal, Ride> join = root.join(UserPrincipal_.rides, JoinType.LEFT);
//            join.on(criteriaBuilder.
//                    and(criteriaBuilder.equal(root.get(UserPrincipal_.email), email),
//                            criteriaBuilder.equal(root.get(UserPrincipal_.password), password)));
//            query.select(root);
//            return em.createQuery(query).getSingleResult();
//            CriteriaQuery<UserPrincipal> query = query.
//                    select(root);
            return (UserPrincipal) em.createQuery("SELECT U FROM UserPrincipal U LEFT JOIN FETCH U.rides WHERE LOWER(U.email) = :email AND U.password = :password AND U.profileStatus = :status")
                    .setParameter("email", email.toLowerCase())
                    .setParameter("password", password)
                    .setParameter("status", Status.ACTIVE)
                    .getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            throw new EntityException(e, "Verifique os dados e tente novamente!");
        } catch (Exception e) {
            throw new EntityException(e, "Ocorreu um erro inesperado!");
        }
    }

    public UserPrincipal save(UserPrincipal userPrincipal) throws EntityException {
        try {
            em.persist(userPrincipal);
            return userPrincipal;
        } catch (Exception e) {
            throw new EntityException(e, "Erro ao salvar entidade");
        }
    }

    public UserPrincipal searchById(Long id) throws EntityException {
        try {
            return em.find(UserPrincipal.class, id);
        } catch (Exception e) {
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

    public UserPrincipal searchUserPrincipalByEmail(String email) throws EntityException {
        try {
            this.criteriaQuery.
                    where(criteriaBuilder.
                            equal(criteriaBuilder.lower(root.get(UserPrincipal_.email)), email.toLowerCase()));
            return em.createQuery(this.criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new EntityException(e, "Não existe");
        }
    }
}
