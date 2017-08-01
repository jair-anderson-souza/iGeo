/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.services.client;

import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.exceptions.ApplicationException;
import java.util.Set;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 24, 2017 2:26:48 PM
 */
public interface RideService {

    public Ride register(Ride ride, Long idUserPrincipal) throws ApplicationException;

    public Ride delete(Long id) throws ApplicationException;

    public Ride updateRide(Ride ride) throws ApplicationException;

    public Set<Ride> getRides() throws ApplicationException;

    public Set<Ride> getRides(String origin, String destination, String date) throws ApplicationException;

}
