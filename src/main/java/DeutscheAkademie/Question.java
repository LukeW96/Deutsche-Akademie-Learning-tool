/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  Scott Davies
 * Created: 04/05/2016
 * Version: 1
 */

package DeutscheAkademie;

/**
 * Question object to hold question value, correct answer, user answer 
 * and boolean for correctness
 * @author Scott Davies
 */
public class Question
{
    private String questionValue;
    private String correctAnswerValue;
    private String userAnswerValue;
    private boolean correct;
    
    /**
     * Do nothing constructor
     */
    public Question(){}
    
    /**
     * Constructor to create Question Object
     * @param questionValue
     * @param answerValue
     * @param userAnswerValue 
     */
    public Question(String questionValue, String answerValue, String userAnswerValue)
    {
        this.questionValue = questionValue;
        this.correctAnswerValue = answerValue;
        this.userAnswerValue = userAnswerValue;
    }
    
    /**
     * Getter
     * @return the questionValue
     */
    public String getQuestionValue()
    {
        return questionValue;
    }

    /**
     * Setter
     * @param questionValue the questionValue to set
     */
    public void setQuestionValue(String questionValue)
    {
        this.questionValue = questionValue;
    }

    /**
     * Getter
     * @return the correctAnswerValue
     */
    public String getCorrectAnswerValue()
    {
        return correctAnswerValue;
    }

    /**
     * Setter
     * @param correctAnswerValue the correctAnswerValue to set
     */
    public void setCorrectAnswerValue(String correctAnswerValue)
    {
        this.correctAnswerValue = correctAnswerValue;
    }

    /**
     * Getter
     * @return the userAnswerValue
     */
    public String getUserAnswerValue()
    {
        return userAnswerValue;
    }

    /**
     * Setter
     * @param userAnswerValue the userAnswerValue to set
     */
    public void setUserAnswerValue(String userAnswerValue)
    {
        this.userAnswerValue = userAnswerValue;
    }

    /**
     * Returns true or false
     * @return correct
     */
    public boolean isCorrect()
    {
        return correct;
    }

    /**
     * Setter
     * @param correct is the answer correct
     */
    public void setCorrect(boolean correct)
    {
        this.correct = correct;
    }

}
