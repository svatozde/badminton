/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.validator;

import cz.cvut.kbss.wpa.support.FacesUtil;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author zdeněk
 */
@FacesValidator(value="matchDateValidator")
@Configurable
public class MatchDateValidator implements Validator{

    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        Date d = (Date) o;
        if(d.before(new Date())) {
            throw new ValidatorException(new FacesMessage(FacesUtil.getMessage("wrongPropDate")));
        }
                
    }
    
}
