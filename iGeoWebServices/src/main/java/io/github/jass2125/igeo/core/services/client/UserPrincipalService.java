/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.services.client;

import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.webservices.LoginVO;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 24, 2017 2:26:48 PM
 */
public interface UserPrincipalService {

    public UserPrincipal login(LoginVO loginVO) throws Exception;

    public UserPrincipal register(UserPrincipal userPrincipal);

}
