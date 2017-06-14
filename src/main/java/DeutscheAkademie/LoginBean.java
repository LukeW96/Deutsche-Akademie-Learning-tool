/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  James Constance
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;
 
import java.io.Serializable;
import java.util.ArrayList;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
// Based on: http://www.journaldev.com/7252/jsf-authentication-login-logout-database-example

/**
 * Class to manage session login and logout
 */
 
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
 
    private DatabaseManager databaseManager;
    private String password;
    private String username;
    
    /**
     * Constructor
     */
    public LoginBean()
    {
        databaseManager = new DatabaseManager();
    }
 
    /**
     * Getter
     * @return password
     */
    public String getPassword() {
        return password;
    }
 
    /**
     * Setter
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
 
 
    /**
     * Getter
     * @return username
     */
    public String getUsername() {
        return username;
    }
 
    /**
     * Setter
     * @param user
     */
    public void setUsername(String user) {
        this.username = user;
    }
 

    /**
     * Checks whether user/password in database
     * @return link to welcome page, or login page on failure
     */
    public String validateUsernamePassword() 
    {
        if (isUserInDatabase(username, password)) 
        {    
            //gets options page appropriate to access level
            return "UserOptions";
        }
        else 
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "Login";
        }
    }
 

    /**
     * Logout invalidate session
     * @return link to login page
     */
    public String logout() 
    {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "LoggedOut?faces-redirect=true";
    }

    //returns true if match found for username and password
    private boolean isUserInDatabase(String username, String password)
    {
        ArrayList<String> userDetails = databaseManager.getUserDetails(username, password);

        if (userDetails!=null)
        {
            //adds attributes to the httpsession
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", username);
            session.setAttribute("forename", userDetails.get(0));
            session.setAttribute("surname", userDetails.get(1));
            session.setAttribute("access", userDetails.get(2));
            session.setAttribute("userid", userDetails.get(3));
            return true;
        }

        return false;
    }    
    
    /**
     * Returns true if user has admin or instructor access level
     * @return true if user is admin or instructor
     */
    public boolean isAdminOrInstructor()
    {     
        if(SessionBean.getAccess().equals("admin")|| SessionBean.getAccess().equals("instructor"))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Returns true if user has admin access level
     * @return true if user is admin 
     */
    public boolean isAdmin()
    {     
        if(SessionBean.getAccess().equals("admin"))
        {
            return true;
        }
        return false;
    }
     
    /**
     * Returns true if user has instructor access level
     * @return true if user is instructor
     */
    public boolean isInstructor()
    {
        if(SessionBean.getAccess().equals("instructor"))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Returns true if user has student, admin or instructor access level
     * @return true if user is student, admin or instructor
     */
    public boolean isStudentAdminOrInstructor()
    {
        if(SessionBean.getAccess().equals("student") || 
                SessionBean.getAccess().equals("admin")|| 
                SessionBean.getAccess().equals("instructor"))
        {
            return true;
        }
        return false;
    }
    
}