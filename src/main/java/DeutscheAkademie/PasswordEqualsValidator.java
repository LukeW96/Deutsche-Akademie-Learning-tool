/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  James Constance
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator to check same password is entered twice in form
 */
@FacesValidator(value="passwordEqualsValidator")
public class PasswordEqualsValidator implements Validator 
{

    /**
     * Validates that password is confirmed
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException 
    {
        Object confirmPassword = value;
        Object password = component.getAttributes().get("passwordComponent");
        
        if (password == null || confirmPassword == null) return;

        if (!password.equals(confirmPassword)) 
        {
            throw new ValidatorException(new FacesMessage("Passwords do not match"));
        }
    }

}
