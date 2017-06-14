/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  Ryan French
 * Created: 04/05/2016
 * Version: 1.0
 */


package DeutscheAkademie;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
* Bean to manage Vocabulary Search, Add, Edit and Delete
*/

@ManagedBean(name="vocabBean")
@SessionScoped
public class VocabBean 
{
    private int vocabID;
    private String germanWord;
    private String genderWord;
    private String englishWord;
    
    private String sqlMessage;
    private String editOption;
    private String searchOption;
    private String searchTerm;
       
    private String editValue;
    
    private VocabEntry[] sqlSearchData;
    private int hasSearchData;
    private DatabaseManager db;
    
    /**
     * Constructor
     */
    public VocabBean()
    {
        db = new DatabaseManager();
        //sqlMessage = "";
        hasSearchData = 0;
        searchTerm = "";
    }
    
    /**
     * Adds a row into the Vocab Table
     */
    public void add() 
    {                
        sqlMessage = db.addVocab(germanWord, genderWord, englishWord);
    }
    
    /**
     * Search the Vocab Table
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
            if(sqlMessage.equals("Please select a Search Option"))
                sqlMessage = "";
            
            String[] Queries = db.createVocabSearchQueries(searchOption, searchTerm);
            int searchSize = db.searchSize(Queries[0]);
            
            if(searchSize == 0)
                hasSearchData = 2;
            else
            {
                sqlSearchData = db.searchVocab(Queries[1], searchSize);                
                hasSearchData = 1;
            }
        }
    }
    
    /**
     * Delete a row from the Vocab Table
     */
    public void delete()
    {
        sqlMessage = db.deleteVocab(vocabID);     
        search();
        
    }
    
    /**
     * Edit a field in a row in the Vocab Table
     */
    public void edit()
    {
        if(editOption == null)
            sqlMessage = "Please select an Edit Option";
        else
        {
            sqlMessage = db.editVocab(editOption, editValue, vocabID);       
            search();
        }

    }
    
    /**
     * Getter
     * @return germanWord
     */
    public String getGermanWord() 
    {
        return germanWord;
    }

    /**
     * Setter
     * @param germanWord
     */
    public void setGermanWord(String germanWord) 
    {
        this.germanWord = germanWord;

    }

    /**
     * Getter
     * @return genderWord
     */
    public String getGenderWord() 
    {
        return genderWord;
    }

    /**
     * Setter
     * @param genderWord
     */
    public void setGenderWord(String genderWord) 
    {
        this.genderWord = genderWord;
    
    }

    /**
     * Getter
     * @return englishWord
     */
    public String getEnglishWord() 
    {
        return englishWord;
        
    }

    /**
     * Setting
     * @param englishWord
     */
    public void setEnglishWord(String englishWord) 
    {
        this.englishWord = englishWord;
    
    }    
    
    /**
     * Getter
     * @return sqlMessage
     */
    public String getSqlMessage()
    {
        return sqlMessage;
    }

    /**
     * Setter
     * @param searchOption
     */
    public void setSearchOption(String searchOption)
    {
        this.searchOption = searchOption;
    }

    /**
     * Setter
     * @param searchTerm
     */
    public void setSearchTerm(String searchTerm)
    {
        this.searchTerm = searchTerm;
    }

    /**
     * Getter
     * @return searchOption
     */
    public String getSearchOption() 
    {
        return searchOption;
    }

    /** 
     * Getter
     * @return searchTerm
     */
    public String getSearchTerm() 
    {
        return searchTerm;
    }
    
    /**
     * 
     * Getter 
     * @return VocabEntry[]
     */
    public VocabEntry[] getSqlSearchData()
    {
        return sqlSearchData;
    }

    /**
     * Getter
     * @return vocabID
     */
    public int getVocabID()
    {
        return vocabID;
    }

    /**
     * Setter
     * @param vocabID
     */
    public void setVocabID(int vocabID)
    {
        this.vocabID = vocabID;
    }

    /**
     * Setter
     * @param editValue
     */
    public void setEditValue(String editValue) {
        this.editValue = editValue;
    }

    /**
     * Getter
     * @return editValue
     */
    public String getEditValue() {
        return editValue;
    }

    /**
     * Getter
     * @return editOption
     */
    public String getEditOption() {
        return editOption;
    }

    /**
     * Setter
     * @param editOption
     */
    public void setEditOption(String editOption) {
        this.editOption = editOption;
    }
    
    /**
     * Concatenate german character to word in input field on HTML Form
     * @param s
     */
    public void concatGermanWord(String s)
    {
        germanWord += s;
    }
    
    /**
     * Clear variables, ensures no old messages appear on HTML page
     */
    public void clearVariables()
    {
        sqlSearchData = null;
        hasSearchData = 0;
        searchTerm = "";
        germanWord = null;
        genderWord = null;
        englishWord = null;
        sqlMessage = "";
        
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



