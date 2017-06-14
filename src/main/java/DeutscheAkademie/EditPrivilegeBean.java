/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  Luke Williams
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Class to manage user privilege access
 */
@Named(value = "userPrivilegeLevelBean")
@SessionScoped
public class EditPrivilegeBean implements Serializable {

    /**
     * Creates a new instance of UserPrivilegeLevelBean
     */
    private DatabaseManager database;
    private String username;
    private String permissionLevel;
    
    /**
     * Instantiates the Database Manager. 
     */
    public EditPrivilegeBean() 
    {
        database = new DatabaseManager();
    }
    
    /**
     * Setter
     * @param permissionLevel
     */
    public void setPermissionLevel(String permissionLevel)
    {
        this.permissionLevel = permissionLevel;
    }
    
    /**
     * Setter
     * @param username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * Getter
     * @return
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Getter
     * @return
     */
    public String getPermissionLevel()
    {
        return permissionLevel;
    }
    
    /**
     * Instructs the DBM to update username with permission level
     */
    public void updateUser()
    {
        database.editUser(username, permissionLevel);
    }
      
}
