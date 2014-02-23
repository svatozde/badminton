/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service.aspects.Validators;

import cz.cvut.kbss.wpa.exceptions.AbstractServiceException;
import cz.cvut.kbss.wpa.service.aspects.anotations.DateOfBirth;
import java.lang.annotation.Annotation;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author zdeněk
 */
public class DateOfbirthValidator extends Validator {

    @Override
    public void validate(Object o, Class<? extends AbstractServiceException> ex, Annotation e) {
        DateOfBirth dob = (DateOfBirth) e;
        if (o != null && o instanceof Date) {
            Calendar a = Calendar.getInstance();
            a.setTime((Date) o);

            Calendar b = Calendar.getInstance();
            b.set(Calendar.HOUR, 0);
            b.set(Calendar.MINUTE, 0);
            b.set(Calendar.SECOND, 0);

            int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
            if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
                    || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
                diff--;
            }
            if(diff < dob.years()) throw createException(ex,dob.code());
        }
    }

}
