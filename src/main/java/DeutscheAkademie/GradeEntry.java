/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  Ryan French
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;

/*
* Class representing a row in the Results Table
*/

public class GradeEntry 
{
    private int userID;
    private String fName;
    private String sName;
    private String resultDate;
    private String reportType;
    private int Score;
    
    /**
     * Constructor
     */
    public GradeEntry()
    {
        
    }

    /**
     * Getter
     * @return userID
     */
    public int getUserID() {
        return userID;
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
     * @return fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * Setter
     * @param fName
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * Getter
     * @return sName
     */
    public String getsName() {
        return sName;
    }

    /**
     * Setter
     * @param sName
     */
    public void setsName(String sName) {
        this.sName = sName;
    }

    /**
     * Getter
     * @return resultDate
     */
    public String getResultDate() {
        return resultDate;
    }

    /**
     * Setter
     * @param resultDate
     */
    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    /**
     * Getter
     * @return reportType
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * Setter
     * @param reportType
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    /**
     * Getter
     * @return score
     */
    public int getScore() {
        return Score;
    }

    /**
     * Setter
     * @param Score
     */
    public void setScore(int Score) {
        this.Score = Score;
    }
}



