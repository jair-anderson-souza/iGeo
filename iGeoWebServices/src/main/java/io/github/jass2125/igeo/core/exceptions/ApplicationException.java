/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.exceptions;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jul 4, 2017 4:39:58 PM
 */
public class ApplicationException extends Exception {

    public ApplicationException(Exception e, String message) {
        super(message, e);
    }

    public ApplicationException(String msg) {
        super(msg);
    }

}
