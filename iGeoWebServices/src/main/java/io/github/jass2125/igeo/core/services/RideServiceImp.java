/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.services;

import io.github.jass2125.igeo.core.dao.RideDao;
import io.github.jass2125.igeo.core.dao.RouteDao;
import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.entity.Route;
import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.exceptions.ApplicationException;
import io.github.jass2125.igeo.core.exceptions.EntityException;
import io.github.jass2125.igeo.core.services.client.RideService;
import io.github.jass2125.igeo.core.services.client.UserPrincipalService;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 24, 2017 2:20:27 PM
 */
@Stateless
@Remote(RideService.class)
public class RideServiceImp implements RideService {

    @EJB
    private RideDao rideDao;
    @EJB
    private UserPrincipalService userPrincipalService;
    @EJB
    private RouteDao routeDao;

    @Override
    public Ride register(Ride ride, Long idUserPrincipal) throws ApplicationException {
        try {
            UserPrincipal userPrincipal = this.userPrincipalService.searchUserPrincipalById(idUserPrincipal);
            if (userPrincipal != null) {
//                Route routeOrigin = ride.getRouteOrigin();
//                Route routeDestiny = ride.getRouteDestiny();
//                routeDao.save(routeOrigin);
//                routeDao.save(routeDestiny);
                userPrincipal.addRide(ride);
                userPrincipalService.update(userPrincipal);
            }
            return null;
        } catch (EntityException e) {
            throw new ApplicationException(e, e.getMessage());
        }
    }

    @Override
    public Ride delete(Long id) throws ApplicationException {
        try {
            Ride ride = rideDao.searchById(id);
            return rideDao.delete(ride);
        } catch (Exception e) {
            throw new ApplicationException(e, e.getMessage());
        }
    }

    @Override
    public Ride updateRide(Ride ride) throws ApplicationException {
        try {
            Ride rideTemp = rideDao.searchById(ride.getId());
            if (rideTemp == null) {
                throw new ApplicationException("Não existe carona com o id especificado!!");
            }
            return rideDao.update(ride);
        } catch (Exception e) {
            throw new ApplicationException(e, e.getMessage());
        }
    }
}
