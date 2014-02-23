/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service.aspects.Validators;

import cz.cvut.kbss.wpa.exceptions.AbstractServiceException;
import cz.cvut.kbss.wpa.exceptions.ExceptionCodes;
import cz.cvut.kbss.wpa.service.aspects.anotations.Interval;
import java.lang.annotation.Annotation;

/**
 *
 * @author zdeněk
 */
public class IntervalValidator extends Validator{

    @Override
    public void validate(Object o, Class<? extends AbstractServiceException> ex, Annotation e) {
        Interval in = (Interval) e;
        if(o!=null && o instanceof Integer){
            Integer i = (Integer) o;
            if(( in.high() < i) || ( in.low() > i)){
                throw createException(ex, in.code());
            }
        }
           
    }
    
}
