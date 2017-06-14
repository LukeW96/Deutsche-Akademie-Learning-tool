/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  James Constance
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Based on: http://www.journaldev.com/7252/jsf-authentication-login-logout-database-example

/**
 * Webfilter class to filter pages on access level
 */
 
@WebFilter(filterName = "AuthorisationFilter", urlPatterns = { "*.xhtml" })
public class AuthorisationFilter implements Filter {
 
    private HashMap<String,String> permissions;
    
    /**
     * Constructor
     */
    public AuthorisationFilter() 
    {
        permissions = new HashMap();
        //add any non-public page below with appropriate permission
        permissions.put("StudentOptions", "s");
        permissions.put("InstructorOptions", "i");
        permissions.put("AdministratorOptions", "a");
        permissions.put("Vocab", "i");//includes all pages ending in Vocab.xhtml
        permissions.put("ViewGradeInstructor", "ia");
        permissions.put("ViewGradeStudent", "sia");
        permissions.put("testselect", "sia");
        permissions.put("EditUser", "a");
        
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException 
    {
        try 
        {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession session = reqt.getSession(false);
            
            String access = "public";
            if(session != null && session.getAttribute("access") != null)
            {
                access = session.getAttribute("access").toString();
            }
            
            System.out.println("access: " + access);
            String reqURI = reqt.getRequestURI();
            System.out.println(reqURI);
            
            if(hasPermission(reqURI,access))
            {
                chain.doFilter(request, response);
            }
            else //not aurhorised
            {
                resp.sendRedirect(reqt.getContextPath() + "/faces/UnauthorisedAccess.xhtml");
            }
                
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    //checks if reqURI exists in permissions HashMap, and if so checks access allowed
    private boolean hasPermission(String reqURI, String access)
    {
        for(String key:permissions.keySet())
        {
            if(reqURI.contains(key + ".xhtml"))
            {
                //does entry value contain first letter of access p,s,i,a
                if(!permissions.get(key).contains(access.substring(0, 1)))
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
 
    @Override
    public void destroy() {
 
    }
}