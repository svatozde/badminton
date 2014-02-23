/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service.aspects.anotations;

import cz.cvut.kbss.wpa.exceptions.ExceptionCodes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author zdeněk
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Interval {
    public ExceptionCodes code();
    public int low() default Integer.MIN_VALUE;
    public int high() default Integer.MAX_VALUE;
    
}
