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

@FacesValidator(value="birthDateValidator")
@Configurable
public class BirthDateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date d = (Date) value;
        if(d.after(new Date()))
            throw new ValidatorException(new FacesMessage(FacesUtil.getMessage("wrongPropDate")));
    }

}
