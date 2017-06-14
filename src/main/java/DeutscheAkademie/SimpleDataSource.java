/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  James Constance
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to initialise connection to database
 */
public class SimpleDataSource
{
    
    private static String url;
    private static String username;
    private static String password;
    
    /**
       Initialises the data source.
       @param fileName the name of the property file that 
       contains the database driver, URL, username, and password
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
    */    
    public static void init(InputStream fileName) throws IOException, 
    ClassNotFoundException
    {  
                
       Properties props = new Properties();
       props.load(fileName);
 
       String driver = props.getProperty("jdbc.driver");
       url = props.getProperty("jdbc.url");
       url += "?useUnicode=true&characterEncoding=UTF-8"; //added by james
        System.out.println(url);
       username = props.getProperty("jdbc.username");
       password = props.getProperty("jdbc.password");
 
       
        Class.forName(driver);
    }
 
   /**
       Gets a connection to the database.
       @return the database connection
     * @throws java.sql.SQLException
    */
    public static Connection getConnection() throws SQLException
    {
       return DriverManager.getConnection(url, username, password);
    }
 

 }
 
 