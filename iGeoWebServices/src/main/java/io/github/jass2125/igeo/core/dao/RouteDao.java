/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.dao;

import io.github.jass2125.igeo.core.entity.Route;
import io.github.jass2125.igeo.core.exceptions.EntityException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jul 5, 2017 5:20:43 PM
 */
@Stateless
public class RouteDao {

    @PersistenceContext
    private EntityManager em;

    public void save(Route route) throws EntityException {
        try {
            em.merge(route);
        } catch (Exception e) {
            throw new EntityException(e, "Não foi possível salvar a Rota");
        }

    }
}
