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
 * Validator to check username does not exist
 */
@FacesValidator(value="usernameValidator")
public class UsernameValidator implements Validator 
{

    DatabaseManager databaseManager = new DatabaseManager();
    /**
     * Validates that username does not already exist
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException 
    {

        if(databaseManager.doesUsernameExist(value.toString()))
        {
            System.out.println("username validator : exists");
            throw new ValidatorException(new FacesMessage("Username already exists."));
        }
        
    }


}
