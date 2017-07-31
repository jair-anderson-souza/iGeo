/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.dao;

import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.entity.Ride_;
import io.github.jass2125.igeo.core.exceptions.EntityException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 27, 2017 12:56:57 PM
 */
@Stateless
public class RideDao {

    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Ride> criteriaQuery;
    private CriteriaDelete<Ride> criteriaDelete;
    private Root<Ride> rootQuery;
    private Root<Ride> rootDelete;

    @PostConstruct
    public void init() {
        this.criteriaBuilder = em.getCriteriaBuilder();
        this.criteriaQuery = criteriaBuilder.createQuery(Ride.class);
        this.criteriaDelete = criteriaBuilder.createCriteriaDelete(Ride.class);
        this.rootQuery = criteriaQuery.from(Ride.class);
        this.rootDelete = criteriaDelete.from(Ride.class);
    }

    @PreDestroy
    public void destroy() {
        this.em = null;
        this.criteriaBuilder = null;
        this.criteriaQuery = null;
        this.criteriaDelete = null;
        this.rootQuery = null;
        this.rootDelete = null;
    }

    public Ride save(Ride ride) throws EntityException {
        try {
            em.persist(ride);
            return ride;
        } catch (Exception e) {
            throw new EntityException(e, "Erro ao salvar entidade Ride");
        }
    }

    public Ride searchById(Long id) throws EntityException {
        try {
            return em.find(Ride.class, id);
        } catch (Exception e) {
            throw new EntityException(e, "Não foi possível consultar Ride!!");
        }
    }

    public Ride delete(Ride ride) throws EntityException {
        try {
            em.remove(ride);
//            criteriaDelete.where(criteriaBuilder.equal(rootDelete.get(Ride_.id), id));
//            int numberOfLines = em.createQuery(criteriaDelete).executeUpdate();
//            if (numberOfLines <= 0) {
//                throw new EntityException("Não foi possível excluir a entidade!!");
//            }
            return ride;
        } catch (Exception e) {
            throw new EntityException(e, "Não foi possível excluir a Carona!!");
        }
    }

    public Ride update(Ride ride) throws EntityException {
        try {
            return em.merge(ride);
        } catch (Exception e) {
            throw new EntityException(e, "Ocorreu um erro de sistema!!");
        }
    }

    public Set<Ride> findAll() throws EntityException {
        try {
//            this.criteriaQuery.multiselect(
//                    this.rootQuery.get(Ride_.id),
//                    this.rootQuery.get(Ride_.cityOrigin),
//                    this.rootQuery.get(Ride_.cityDestiny),
//                    this.rootQuery.get(Ride_.date),
//                    this.rootQuery.get(Ride_.departureTime)
//            );
//            List<Ride> resultList = em.createQuery(criteriaQuery).getResultList();
            return new HashSet<>(null);
        } catch (Exception e) {
            throw new EntityException(e, "Não foi possível buscar as caronas!");
        }
    }
}
