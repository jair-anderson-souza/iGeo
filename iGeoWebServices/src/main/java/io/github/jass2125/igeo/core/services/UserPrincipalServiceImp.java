/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.services;

import io.github.jass2125.igeo.core.dao.UserPrincipalDao;
import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.exceptions.EntityException;
import io.github.jass2125.igeo.core.services.client.UserPrincipalService;
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

    @Override
    public UserPrincipal login(LoginVO loginVO) throws Exception {
        try {
            return dao.login(loginVO.getEmail(), loginVO.getPassword());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro");
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
    public UserPrincipal searchUserPrincipalById(Long id) {
        try {
            return dao.searchById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addRide(Long id, Ride ride) throws Exception {
        try {
            UserPrincipal userPrincipal = searchUserPrincipalById(id);
            userPrincipal.addRide(ride);
            dao.updateUserPrincipal(userPrincipal);
        } catch (EntityException ex) {
            throw new Exception("Não foi possível salvar a rota no usuário selecionado!");
        }
    }
}
