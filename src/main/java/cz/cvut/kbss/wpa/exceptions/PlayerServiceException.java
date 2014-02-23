/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.exceptions;

/**
 *
 * @author zdeněk
 */
public class PlayerServiceException extends AbstractServiceException {

    public PlayerServiceException(ExceptionCodes code) {
        super(code);
    }

    public PlayerServiceException(ExceptionCodes code, Exception cause) {
        super(code, cause);

    }

}
