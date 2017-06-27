/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.dao;

import io.github.jass2125.igeo.core.entity.UserPrincipal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

    public UserPrincipal login(String email, String password) {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<UserPrincipal> query = criteriaBuilder.createQuery(UserPrincipal.class);
//        Root<UserPrincipal> root = query.from(UserPrincipal.class);
        
//        query.select(root.getModel());
//        query.where(criteriaBuilder.equal(root.get(UserPrincipal_.count.getName()))
        return null;

    }
}
