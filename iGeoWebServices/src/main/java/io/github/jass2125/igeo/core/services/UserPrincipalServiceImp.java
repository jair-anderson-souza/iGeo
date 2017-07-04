/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.services;

import io.github.jass2125.igeo.core.dao.UserPrincipalDao;
import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.exceptions.ApplicationException;
import io.github.jass2125.igeo.core.exceptions.CryptographyException;
import io.github.jass2125.igeo.core.exceptions.EncodingException;
import io.github.jass2125.igeo.core.exceptions.EntityException;
import io.github.jass2125.igeo.core.services.client.UserPrincipalService;
import io.github.jass2125.igeo.core.util.PasswordEncriptor;
import io.github.jass2125.igeo.core.vo.LoginVO;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 24, 2017 2:20:27 PM
 */
@Stateless
@Remote(UserPrincipalService.class)
public class UserPrincipalServiceImp implements UserPrincipalService {

    @EJB
    private UserPrincipalDao dao;
    private PasswordEncriptor encriptor;

    public UserPrincipalServiceImp() {
        this.encriptor = new PasswordEncriptor();
    }

    @Override
    public UserPrincipal login(LoginVO loginVO) throws ApplicationException {
        try {
            String encryptPassword = encriptor.encryptPassword(loginVO.getPassword());
            return dao.login(loginVO.getEmail(), encryptPassword);
        } catch (EncodingException | CryptographyException e) {
            throw new ApplicationException(e, e.getMessage());
        } catch (EntityException e) {
            e.printStackTrace();
            throw new ApplicationException(e, e.getMessage());
        }
    }

    @Override
    public UserPrincipal register(UserPrincipal userPrincipal) {
        try {
            return dao.save(userPrincipal);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserPrincipal searchUserPrincipalById(Long id) throws ApplicationException {
        try {
            return dao.searchById(id);
        } catch (EntityException e) {
            throw new ApplicationException(e, e.getMessage());
        }
    }

    @Override
    public void addRide(Long id, Ride ride) throws ApplicationException {
        try {
            UserPrincipal userPrincipal = searchUserPrincipalById(id);
            if (userPrincipal != null) {
                userPrincipal.addRide(ride);
                dao.update(userPrincipal);
            }
        } catch (ApplicationException | EntityException e) {
            throw new ApplicationException(e, e.getMessage());
        }
    }
}
