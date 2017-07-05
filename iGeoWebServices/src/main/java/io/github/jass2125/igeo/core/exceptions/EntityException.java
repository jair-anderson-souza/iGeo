/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.exceptions;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 23, 2017 11:58:48 PM
 */
public class EntityException extends Exception {

    public EntityException(Exception e, String msg) {
        super(msg, e);
    }

    public EntityException(String msg) {
        super(msg);
    }


}
