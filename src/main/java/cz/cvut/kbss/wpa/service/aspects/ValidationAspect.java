/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service.aspects;

import cz.cvut.kbss.wpa.exceptions.AbstractServiceException;
import cz.cvut.kbss.wpa.exceptions.ExceptionCodes;
import cz.cvut.kbss.wpa.service.aspects.Validators.DateOfbirthValidator;
import cz.cvut.kbss.wpa.service.aspects.Validators.IntervalValidator;
import cz.cvut.kbss.wpa.service.aspects.Validators.NotBlankValidator;
import cz.cvut.kbss.wpa.service.aspects.Validators.NotNullValidator;
import cz.cvut.kbss.wpa.service.aspects.Validators.Validator;
import cz.cvut.kbss.wpa.service.aspects.anotations.DateOfBirth;
import cz.cvut.kbss.wpa.service.aspects.anotations.Interval;
import cz.cvut.kbss.wpa.service.aspects.anotations.NotBlank;
import cz.cvut.kbss.wpa.service.aspects.anotations.NotNull;
import cz.cvut.kbss.wpa.service.aspects.anotations.Validate;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;

/**
 *
 * @author zdeněk
 */
@Aspect
public class ValidationAspect {

    public Map<Class<? extends Annotation>, Validator> validators;

    public ValidationAspect() {
        validators = new HashMap<Class<? extends Annotation>, Validator>();
        validators.put(NotNull.class, (Validator) new NotNullValidator());
        validators.put(NotBlank.class,(Validator) new NotBlankValidator());
        validators.put(Interval.class,(Validator) new IntervalValidator());
        validators.put(DateOfBirth.class,(Validator) new DateOfbirthValidator());
    }

    /**
     * All infaces frm package with one parameter {convention}
     * @param joinPoint
     * @throws Throwable 
     */
    @Before("execution(* cz.cvut.kbss.wpa.service.api..*(..))")
    public void validate(JoinPoint joinPoint) throws Throwable {
        Class[] classes =  ((CodeSignature) joinPoint.getSignature()).getParameterTypes() ;
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i<args.length;i++){
            Validate valAnnotation = (Validate) classes[i].getAnnotation(Validate.class);
            Object o = args[i];
            if (valAnnotation != null) {
                Class<? extends AbstractServiceException> eClass = valAnnotation.exceptionClass();
                if(o == null){
                    throw Validator.createException(eClass, valAnnotation.code());
                }
                for (Field f : o.getClass().getDeclaredFields()) {
                    for (Annotation a : f.getAnnotations()) {
                        Validator validator = validators.get(a.annotationType());
                        if (validator != null) {
                            boolean access = f.isAccessible();
                            f.setAccessible(true);
                            validator.validate(f.get(o), eClass, a);
                            f.setAccessible(access);
                        }
                    }
                }
            }
        }
    }
   

}
