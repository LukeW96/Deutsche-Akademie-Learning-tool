/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  Scott Davies
 * Created: 04/05/2016
 * Version: 1
 */


package DeutscheAkademie;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



/**
 * Generates the various tests
 */
@ManagedBean
@SessionScoped
public class TestGeneratorBean
{
    private ArrayList<Question> questionList; //list of questions
    private String userAnswer; //never really used but required for JSF
    private ArrayList<String> userAnswerList; //list of users answers
    private int score;
    private String testType;
    private Date date;

    
    /**
     * Creates a new instance of TestGeneratorBean
     */
    public TestGeneratorBean()
    {
        userAnswerList = new ArrayList<>();
    }

    /**
     * Returns the list of question objects
     * @return 
     */
    public ArrayList<Question> getQuestionList()
    {
        return questionList;
    }
    
    /**
     * Set a random German test
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void setGermanTest() throws IOException, SQLException, ClassNotFoundException
    {
        testType = "germanToEnglish";
        userAnswerList = new ArrayList<>();
        DatabaseManager dbm = new DatabaseManager();
        questionList = dbm.getRandomGermanTest();
    }
    
    /**
     * Set a random English test
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void setEnglishTest() throws IOException, SQLException, ClassNotFoundException
    {
        testType = "englishToGerman";
        userAnswerList = new ArrayList<>();
        DatabaseManager dbm = new DatabaseManager();
        questionList = dbm.getRandomEnglishTest();
    }
    
    /**
     * Set a random gender test
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void setGenderTest() throws IOException, SQLException, ClassNotFoundException
    {
        testType = "gender";
        userAnswerList = new ArrayList<>();
        DatabaseManager dbm = new DatabaseManager();
        questionList = dbm.getRandomGenderTest();
    }


    /**
     * Getter
     * @return the singleUserAnswer
     */
    public String getUserAnswer()
    {
        return userAnswer;
    }

    /**
     * Setter
     * @param userAnswer the singleUserAnswer to set
     */
    public void setUserAnswer(String userAnswer)
    {
        //make a new arraylist each test if taking multiple in same session
        if(userAnswerList.size() == 20)
            userAnswerList = new ArrayList<>();
        
        getUserAnswerList().add(userAnswer);
    }

    /**
     * Getter
     * @return the userAnswerList
     */
    public ArrayList<String> getUserAnswerList()
    {
        return userAnswerList;
    }

    /**
     * Setter
     * @param userAnswerList the userAnswerList to set
     */
    public void setUserAnswerList(ArrayList<String> userAnswerList)
    {
        this.userAnswerList = userAnswerList;
    }

    /**
     * Getter
     * @return the score
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * Marks the test and returns question object to a data on results page
     * @return 
     */
    public ArrayList<Question> getResults()
    {
        score = 0;
        
        //loop adds user answer to question objects then compares them
        for(int i = 0; i < 20; i++) 
        {
            String s = userAnswerList.get(i);
            questionList.get(i).setUserAnswerValue(s);
            
            //to deal with case issues
            if(testType.equals("germanToEnglish"))
            {
                
                if(questionList.get(i).getCorrectAnswerValue().equals(s))
                {
                    questionList.get(i).setCorrect(true);
                    score++;
                }
 
            }
            else
            {
                if(questionList.get(i).getCorrectAnswerValue().equalsIgnoreCase(s))
                {
                    questionList.get(i).setCorrect(true);
                    score++;
                }
            }
            
        }
        
        return questionList;
    }
    
    /**
     * Setter
     * @param score the score to set
     */
    public void setScore(int score)
    {
        this.score = score;
    }
    
    /**
     * Stores the users test result in the database
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void storeTestResult() throws IOException, ClassNotFoundException, SQLException
    {
        long time = System.currentTimeMillis();
        date = new Date(time);
        DatabaseManager dbm = new DatabaseManager();
        dbm.storeTestResult(SessionBean.getUserid(), date, testType, score);
    }

}
