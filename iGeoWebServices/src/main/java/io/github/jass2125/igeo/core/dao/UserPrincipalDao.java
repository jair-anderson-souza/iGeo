/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.dao;

import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.entity.UserPrincipal_;
import io.github.jass2125.igeo.core.exceptions.EntityException;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    private CriteriaQuery<UserPrincipal> query;
    private Root<UserPrincipal> root;

    @PostConstruct
    public void init() {
        this.criteriaBuilder = em.getCriteriaBuilder();
        this.query = criteriaBuilder.createQuery(UserPrincipal.class);
        this.root = query.from(UserPrincipal.class);
    }

    public UserPrincipal login(String email, String password) {
//        Join<UserPrincipal, Count> join = root.join(UserPrincipal_.count);
        try {
            query.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get(UserPrincipal_.email), email), criteriaBuilder.equal(root.get(UserPrincipal_.password), password)));
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            //mudar pra exceção filha de Exception
            throw new NoResultException("Os dados esão inválidos!");

        }
    }

    public UserPrincipal save(UserPrincipal userPrincipal) throws EntityException {
        try {
            em.persist(userPrincipal);
            return userPrincipal;
        } catch (Exception e) {
            throw new EntityException("Erro ao salvar entidade");
        }
    }
}
