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
public abstract class AbstractServiceException extends RuntimeException{
    private ExceptionCodes code;
    
    public AbstractServiceException(ExceptionCodes code){
        super(code.getMessage());
        this.code = code;
    }
    
    public AbstractServiceException(ExceptionCodes code, Exception cause){
        super(code.getMessage(),cause);
        this.code = code;    
    }

    /**
     * @return the code
     */
    public ExceptionCodes getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(ExceptionCodes code) {
        this.code = code;
    }
}
