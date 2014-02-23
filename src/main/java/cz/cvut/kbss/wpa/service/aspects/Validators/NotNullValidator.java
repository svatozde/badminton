/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service.aspects.Validators;

import cz.cvut.kbss.wpa.exceptions.AbstractServiceException;
import cz.cvut.kbss.wpa.exceptions.ExceptionCodes;
import cz.cvut.kbss.wpa.service.aspects.anotations.NotNull;
import java.lang.annotation.Annotation;

/**
 *
 * @author zdeněk
 */
public class NotNullValidator extends Validator{

   

    @Override
    public void validate(Object o, Class<? extends AbstractServiceException> ex, Annotation e) {
        NotNull n = (NotNull) e;
        if(o == null) throw createException(ex, n.code());
    }
    
}
