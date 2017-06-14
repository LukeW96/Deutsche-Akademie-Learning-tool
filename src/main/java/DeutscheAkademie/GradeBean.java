/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  Ryan French
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
* Bean to manage Grade Viewing
*/

@ManagedBean(name="gradeBean")
@SessionScoped
public class GradeBean 
{
    
    private GradeEntry[] sqlSearchData;
    private int hasSearchData;
    private String searchOption;
    private String searchTerm;
    private String sqlMessage;
    
    private DatabaseManager db;
    
    private String access; 
    private int userID; 
    
    /**
     * Constructor
     */
    public GradeBean()
    {
        userID = SessionBean.getUserid();
        access = SessionBean.getAccess();
        db = new DatabaseManager();
        searchTerm = "";
        hasSearchData = 0;
    }
    
    /**
     * Search Results table
     */
    public void search()
    {
        if(searchOption == null)
        {
            sqlMessage = "Please select a Search Option";
            hasSearchData = 0;
        }
        else
        {
            sqlMessage = null;
            String[] Queries = db.createGradeSearchQueries(searchOption, searchTerm, access, userID);               
            int searchSize = db.searchSize(Queries[0]);  
            if(searchSize == 0 && !searchOption.equals("own"))
                hasSearchData = 2;
            else
            {
                sqlSearchData = db.searchGrade(Queries[1], searchSize);
                hasSearchData = 1;
            }
        }
    }
    
    /**
     * Grade for user currently logged in
     */
    public void personalGrade()
    {
        String[] Queries = db.createGradeSearchQueries("userID", String.valueOf(userID), access, userID);
        int searchSize = db.searchSize(Queries[0]);        
        sqlSearchData = db.searchGrade(Queries[1], searchSize);
    }
    
    /**
     * Getter
     * @return searchOption
     */
    public String getSearchOption() {
        return searchOption;
    }

    /**
     * Setter
     * @param searchOption
     */
    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    /**
     * Getter
     * @return searchItem
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * Setter
     * @param searchTerm
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Getter
     * @return GradeEntry[]
     */
    public GradeEntry[] getSqlSearchData() {
        return sqlSearchData;
    }

    /**
     * Getter
     * @return sqlMessage
     */
    public String getSqlMessage() {
        return sqlMessage;
    }

    /**
     * Setter
     * @param access
     */
    public void setAccess(String access) {
        this.access = access;
    }

    /**
     * Setter
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter
     * @return hasSearchData
     */
    public int getHasSearchData() {
        return hasSearchData;
    }

    /**
     * Setter
     * @param hasSearchData 
     */
    public void setHasSearchData(int hasSearchData) {
        this.hasSearchData = hasSearchData;
    }

}



