/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.services;

import io.github.jass2125.igeo.core.dao.RideDao;
import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.services.client.RideService;
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
    private RideDao dao;

    @Override
    public Ride register(Ride ride) {
        try {
            return dao.save(ride);
        } catch (Exception e) {
            return null;
        }
    }
}
