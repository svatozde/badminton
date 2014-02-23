/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service.aspects.Validators;

import cz.cvut.kbss.wpa.exceptions.AbstractServiceException;
import cz.cvut.kbss.wpa.exceptions.ExceptionCodes;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zdeněk
 */
public abstract class Validator {

    static final Logger log = Logger.getLogger(Validator.class.getName());

    public static AbstractServiceException createException(Class<? extends AbstractServiceException> ex, ExceptionCodes e) {

        try {
            Constructor<? extends AbstractServiceException> exi = ex.getConstructor(ExceptionCodes.class);
            return exi.newInstance(e);
        } catch (IllegalArgumentException ex1) {
            log.log(Level.SEVERE, null, ex1);
        } catch (InvocationTargetException ex1) {
            log.log(Level.SEVERE, null, ex1);
        } catch (InstantiationException ex1) {
            log.log(Level.SEVERE, null, ex1);
        } catch (IllegalAccessException ex1) {
            log.log(Level.SEVERE, null, ex1);
        } catch (NoSuchMethodException ex1) {
            log.log(Level.SEVERE, null, ex1);
        } catch (SecurityException ex1) {
            log.log(Level.SEVERE, null, ex1);
        }

        return null;

    }

    public abstract void validate(Object o, Class<? extends AbstractServiceException> ex, Annotation e);

}
