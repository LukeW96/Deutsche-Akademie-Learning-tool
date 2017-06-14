/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  Declan Fitzpatrick, Ryan French
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
* Class to manage interactions between beans and the MySQL database
*/
public class DatabaseManager
{

    Connection con;
    /**
    * Create a database connection using the properties file.
    */
    public DatabaseManager()
    {
        try {
            InputStream stream = DatabaseManager.class.getResourceAsStream("/database.properties");
            SimpleDataSource.init(stream);
            con = SimpleDataSource.getConnection();
            
            System.out.println("connected now");
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
    * Method to get all of the specified user details
    * @param username The username of the user.
    * @param password The password of the user.
    * @return The arraylist of details for that user.
    */
    public ArrayList<String> getUserDetails(String username, String password)
    {
        ArrayList<String> details = new ArrayList();
        try {
            System.out.println("checking login/password");
            Statement statement = con.createStatement( );
            String command = "SELECT fName,sName,access,userID FROM User "
                    + "WHERE `password` = '" + password + "' AND "
                    + "`username` = '" + username +"'";
            ResultSet rs = statement.executeQuery(command);
            if(rs.next())
            {
                System.out.println("login found");
                details.add(rs.getString(1));
                details.add(rs.getString(2));
                details.add(rs.getString(3));
                details.add(rs.getString(4));
                return details;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(details.toString());
        return null;
    }
    
    /**
    * Method to check whether a user exists
    * @param username the username you want to check
    * @return true if exists
    */
    public boolean doesUsernameExist(String username)
    {
        boolean exist = false;
        try {
            Statement statement = con.createStatement( );
            String command = "SELECT 1 FROM User "
                    + "WHERE `username` = '" + username +"'";
            ResultSet rs = statement.executeQuery(command);
            if(rs.next())
            {
                System.out.println("Username already exists");
                exist = true;
            }
            else
            {
                System.out.println("Username does not exist");
                exist = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exist;
    }
    
    /**
    * Method to register a user on the database
    * @param fName the forename of the user
    * @param sName the surname of the user.
    * @param email the email address of the user.
    * @param password the password of the user.
    * @param username user name
    */
    public void registerUser(String fName, String sName, String email, 
            String username, String password) 
    {
        try {
            
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO User (`fName`,`sName`,`email`,`password`,`username`,`access`) "
                        + "VALUES ('" + fName + "','" + sName + "','" + email 
                        + "','" + password + "','" + username  + "','student')");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
  /**
     * Method to add a Vocab Entry into the Vocab table
     * @param germanWord the German version of the word
     * @param genderType the Gender Type of the word
     * @param englishWord  the English version on the word
     * @return String indicate whether statement was successful or not
     */
    public String addVocab(String germanWord, String genderType, String englishWord)
    {
        try 
        {
            Statement st = con.createStatement();            
            String command = String.format
                    ("INSERT INTO `Vocab` (`german`, `gender`, `english`)"
                   + "VALUES('%s', '%s', '%s');", 
                      germanWord, genderType, englishWord);  
            
            st.execute(command);                                                            
            
            return "Entry Succesfully Added";
        } 
        
        catch (SQLException ex) 
        {
            return ex.getMessage();
        } 
    }
    
      /**
     * Method to Edit an existing Vocab entry in the Vocab table
     * @param editOption the field to have its value changed
     * @param editValue the new value of the field
     * @param vocabID  the unique identifier for the row
     * @return String indicate whether statement was successful or not
     */
    public String editVocab(String editOption, String editValue, int vocabID)
    {
        try 
        {
            Statement st = con.createStatement();
            String command = String.format
                    ("UPDATE `Vocab` SET `%s` = '%s'"
                   + "WHERE `vocabID` = %d", 
                    editOption, editValue, vocabID); 
                                                
            st.execute(command);                                                                            
            
            return "Entry Successfully Edited";
        } 
        
        catch (SQLException ex) 
        {
            return ex.getMessage();
        } 
    }
    
    /**
     * Method to Delete an existing Vocab entry in the Vocab table
     * @param vocabID the unique identifier for the row
     * @return String indicate whether statement was successful or not
     */
    public String deleteVocab(int vocabID)
    {
        try 
        {
            Statement st = con.createStatement();
            String command = String.format
                    ("DELETE FROM `Vocab` "
                   + "WHERE `vocabID` = %d", 
                     vocabID); 
                                                
            st.execute(command);   
            
            return "Entry Successfully Deleted";
        } 
        
        catch (SQLException ex) 
        {
            return ex.getMessage();
        } 
    }
    
    /**
     * Method to obtain the amount of rows returned for a corresponding query
     * Designed for use with COUNT Queries
     * @param query the Query to be executed
     * @return int searchSize - the amount of rows returned for a corresponding query
     *                      used to create a Array of certain size for return
     *                      results of corresponding query
     */
    public int searchSize(String query)
    {
        try 
        {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);
            rs.next();
            return rs.getInt(1);                                                
        } 
        
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
        
        return 0;
    }
    
    /**
     * Method to Create an Array of VocabEntry Objects, each containing equivalent 
     * data that is in a row in the Vocab table
     * @param query the search query to be executed
     * @param searchSize the amount of VocabEntry Objects to be created
     * @return VocabEntry[] sqlSearchData - array with VocabEntry Objects 
     *                                      containing row data
     */
    public VocabEntry[] searchVocab(String query, int searchSize)
    {
        try 
        {
            VocabEntry[] sqlSearchData = new VocabEntry[searchSize];
            
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);
            
            for(int i = 0; i < searchSize; i++)            
            {         
                rs.next();
                VocabEntry v = new VocabEntry();
                v.setVocabID(rs.getInt("vocabID"));
                v.setGermanWord(rs.getString("german"));
                v.setGenderType(rs.getString("gender"));
                v.setEnglishWord(rs.getString("english"));
                
                sqlSearchData[i] = v;
            }           
            
            return sqlSearchData;
        } 
        
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
        
        return null;
    }
    
    /**
     * Method to create 2 SQL Queries to be processed. First Query is to deduce how
     * many rows will be returned by the search of the Second Query. The Second
     * Query returns all the data which fits the search terms
     * @param searchOption the field the search is being acted on
     * @param searchTerm the term used for the search
     * @return String[] Queries - the 2 SQL Commands to later be processed
     */
    public String[] createVocabSearchQueries(String searchOption, String searchTerm)
    {
        String[] Queries = new String[2];
        
        Queries[0] = String.format("SELECT COUNT(*) FROM `Vocab` WHERE `%s` LIKE '%%%s%%'", searchOption, searchTerm); 
        Queries[1] = String.format("SELECT * FROM `Vocab` WHERE `%s` LIKE '%%%s%%'", searchOption, searchTerm); 
        
        return Queries;        
    }
    
     /**  
     * Method to create 2 SQL Queries to be processed. First Query is to deduce how
     * many rows will be returned by the search of the Second Query. The Second
     * Query returns all the data which fits the search terms
     * @param searchOption the field the search is being acted on
     * @param searchTerm the term used for the search
     * @param access produce query according to database access level
     * @param userID produce query according to userID
     * @return String[] Queries - the 2 SQL Commands to later be processed
     */
    public String[] createGradeSearchQueries(String searchOption, String searchTerm, String access, int userID)
    {
        String[] Queries = new String[2];
                         
        Queries[0] = "SELECT COUNT(*) AS 'count' "
                   + "FROM `Results`, `User` "
                   + "WHERE `Results`.`userID` = `User`.`userID`"; 
         
        Queries[1] = "SELECT  `Results`.`userID`, `fname`, `sname`, `resultDate`, `reportType`, `Score`\n"
                   + "FROM `Results`, `User`\n"
                   + "WHERE `Results`.`userID` = `User`.`userID`";     
        String s;
                               
        if(!searchTerm.equals("") && !access.equals("user"))
        {
           switch(searchOption)
           {
                case "userID" :     s = (String.format(" AND `Results`.`userID` = %d", Integer.valueOf(searchTerm)));
                                    Queries[0] += s; 
                                    Queries[1] += s; break;                                 
                case "fName"  :
                case "sName"  :  
                case "resultDate" :
                case "reportType" : s = (String.format(" AND `%s` LIKE '%%%s%%'", searchOption, searchTerm));
                                    Queries[0] += s; 
                                    Queries[1] += s; break;   
           }
        }
         
        else if(access.equals("user"))
        {
            s = String.format(" AND `Results`.`userID` = %d", userID);
            Queries[0] += s; 
            Queries[1] += s;
        }
        
        return Queries;
    }
    
    /**
     * Method to Create an Array of GradeEntry Objects, each containing equivalent
     * data that is in a row in the Results Table
     * @param query the search query to be executed
     * @param searchSize the amount of GradeObjects to be created
     * @return GradeEntry[] sqlSearchData - array with GradeEntry Objects
     *                                      containing row data
     */
    public GradeEntry[] searchGrade(String query, int searchSize)
    {
        try 
        {
            GradeEntry[] sqlSearchData = new GradeEntry[searchSize];
            
            Statement st = con.createStatement();
                        
            ResultSet rs = st.executeQuery(query);
            
            for(int i = 0; i < searchSize; i++)            
            {               
                rs.next();                
                GradeEntry g = new GradeEntry();
                g.setUserID(rs.getInt("userID"));
                g.setfName(rs.getString("fname"));
                g.setsName(rs.getString("sname"));
                g.setResultDate(rs.getString("resultDate"));
                g.setReportType(rs.getString("reportType"));
                g.setScore(rs.getInt("score"));
                
                sqlSearchData[i] = g;                              
            }    
            
            return sqlSearchData;                        
        } 
        
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        } 
        
        return null;
    }
    
    /**
     * Returns an ArrayList of 20 random question objects from the database
     * for a German Test
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Question> getRandomGermanTest() throws IOException, SQLException, ClassNotFoundException
    {
        ArrayList<Question> questions = new ArrayList<>();
        

        Statement s = con.createStatement();
        ResultSet results = s.executeQuery("SELECT german, english FROM Vocab ORDER BY RAND() LIMIT 20");
        
        while (results.next()) 
        {
            Question q = new Question();
            q.setQuestionValue(results.getString("german"));
            q.setCorrectAnswerValue(results.getString("english"));
            questions.add(q);
        }
        
        con.close();
        
        return questions;
    }
    
    /**
     * Returns an ArrayList of 20 random question objects from the database
     * for an English Test
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Question> getRandomEnglishTest() throws IOException, SQLException, ClassNotFoundException
    {
        ArrayList<Question> questions = new ArrayList<>();
        
        Statement s = con.createStatement();
        ResultSet results = s.executeQuery("SELECT english, gender, german FROM Vocab ORDER BY RAND() LIMIT 20");
        
        while (results.next()) 
        {
            Question q = new Question();
            q.setQuestionValue(results.getString("english"));
            String gender = "";
            switch(results.getString("gender"))
            {
                case "f": gender = "die";
                    break;
                case "m": gender = "der";
                        break;
                case "n": gender = "das";
            }
            
            
            q.setCorrectAnswerValue(gender + " " + results.getString("german"));
            questions.add(q);
        }
        
        con.close();
        
        return questions;
    }
    
    /**
     * Returns an ArrayList of 20 random question objects from the database
     * for a Gender Test
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Question> getRandomGenderTest() throws IOException, SQLException, ClassNotFoundException
    {
        ArrayList<Question> questions = new ArrayList<>();
        
        Statement s = con.createStatement();
        ResultSet results = s.executeQuery("SELECT german, gender FROM Vocab ORDER BY RAND() LIMIT 20");
        
        while (results.next()) 
        {
            Question q = new Question();
            q.setQuestionValue(results.getString("german"));
            q.setCorrectAnswerValue(results.getString("gender"));
            questions.add(q);
        }
        
        con.close();
        
        return questions;
    }
    
    /**
     * Stores a users test result in the database upon test completion
     * @param userID
     * @param date
     * @param reportType
     * @param score
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public  void storeTestResult(int userID, Date date, String reportType, int score) throws IOException, ClassNotFoundException, SQLException
    {
        Statement s = con.createStatement();
        
        s.execute("INSERT INTO Results(userID, resultDate, reportType, score) VALUES (" + userID + ", '"+ date.toString() + "', '" + reportType + "'," + score + ")");
        
        con.close();
    }
    
    /**
     * Edits the users privilege levels. 
     * @param username - the username of the user to change. 
     * @param permissionLevel - the level of permissions that is to be given to the user.
     */
    public void editUser(String username, String permissionLevel)
    {
        try 
        {
            Statement st = con.createStatement();
            String command = String.format
                    ("UPDATE User SET `access` = '%s'"
                   + "WHERE `username` = '%s'", 
                     permissionLevel, username); 
                                                
            st.execute(command);                                                                            
        } 
        
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        } 
    }



    //insert vocal into tables

    /**
     * Class to insert vocab data from csv file into SQL database.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void VocabInsert() throws SQLException, ClassNotFoundException
    {

        
        System.out.println("Reading csv file");
        String location = "new.csv"; //Location to csv file
	BufferedReader br = null;
	String split = ",";
        String line = "";
        String insert = "INSERT INTO Vocab(german,gender,english) "
                + "Values(?,?,?)";
        PreparedStatement insertVocab = con.prepareStatement(insert);
        
        try {
             br = new BufferedReader(new InputStreamReader(new FileInputStream(location), "UTF-8" )  );
            //br = new BufferedReader(new FileReader(location));
            while ((line = br.readLine()) != null) {   
                String[] vocab = line.split(split);
                insertVocab.setString(1,vocab[0]);
                insertVocab.setString(2,vocab[1]);
                insertVocab.setString(3,vocab[2]);
                insertVocab.execute();
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        } catch (IOException ex) {
            System.out.println("io exception");
        }
        con.close();
    }
    
    /**
     * Main class to call vocab data insertion
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
    {     
       DatabaseManager dbm = new DatabaseManager();
       dbm.VocabInsert();
    }
}
