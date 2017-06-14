/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  James Constance
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Bean to manage registration
 */
@ManagedBean
@RequestScoped
public class RegistrationBean
{
    private DatabaseManager databaseManager;
    private String forename;
    private String surname;
    private String email;
    private String password;
    private String passwordConfirm;
    private String username;
    
    
    /**
     * Creates a new instance of RegistrationBean
     */
    public RegistrationBean()
    {
        databaseManager = new DatabaseManager();
    }
    
    /**
     * Method to store registration in database
     */
    public void register() 
    {
        databaseManager.registerUser(forename, surname, email, username, password);
        
    }

    /**
     * Getter
     * @return forename
     */
    public String getForename()
    {
        return forename;
    }

    /**
     * Setter
     * @param forename
     */
    public void setForename(String forename)
    {
        this.forename = forename;
    }

    /**
     * Getter
     * @return surname
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Setter
     * @param surname
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * Getter
     * @return email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * setter
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Getter
     * @return pasword
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Setter
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Getter
     * @return passwordConfirm
     */
    public String getPasswordConfirm()
    {
        return passwordConfirm;
    }

    /**
     * Setter
     * @param passwordConfirm
     */
    public void setPasswordConfirm(String passwordConfirm)
    {
        this.passwordConfirm = passwordConfirm;
    }

    /**
     * Getter
     * @return username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Setter
     * @param username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
   
}
