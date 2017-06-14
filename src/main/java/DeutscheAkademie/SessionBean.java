/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  James Constance
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;

// Based on: http://www.journaldev.com/7252/jsf-authentication-login-logout-database-example

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
/**
 * Class to manage session
 */
public class SessionBean {
 
    
    /**
     * Getter
     * @return session
     */
    public static HttpSession getSession() 
    {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
 
    /**
     * Getter
     * @return username
     */
    public static String getUsername() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
    
    /**
     * Getter
     * @return forename
     */
    public static String getForename() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("forename").toString();
    }
    
    /**
     * Getter
     * @return surname
     */
    public static String getSurname() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("surname").toString();
    }
    
    /**
     * Getter
     * @return access
     */
    public static String getAccess() 
    {
        System.out.println("getting access");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("access").toString();
    }
    
    /**
     * Getter
     * @return userid
     */
    public static int getUserid() 
    {
        System.out.println("getting userID");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return Integer.parseInt(session.getAttribute("userid").toString());
    }
    

}
