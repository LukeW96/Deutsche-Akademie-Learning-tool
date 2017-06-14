/*
 * Title:   Group Project Deutsche Akademie
 * Group:   Alpha
 * Author:  Ryan French
 * Created: 04/05/2016
 * Version: 1.0
 */

package DeutscheAkademie;

/*
* Class to represent a row in the Vocab Table
*/

public class VocabEntry 
{
    private int vocabID;
    private String germanWord;
    private String genderType;
    private String englishWord;
    
    /**
     * Constructor
     */
    public VocabEntry()
    {
        
    }

    /**
     * Getter
     * @return vocabID
     */
    public int getVocabID() {
        return vocabID;
    }

    /**
     * Setter
     * @param vocabID
     */
    public void setVocabID(int vocabID) {
        this.vocabID = vocabID;
    }

    /**
     * Getter
     * @return germanWord
     */
    public String getGermanWord() {
        return germanWord;
    }

    /**
     * Setter
     * @param germanWord 
     */
    public void setGermanWord(String germanWord) {
        this.germanWord = germanWord;
    }

    /**
     * Getter
     * @return genderType
     */
    public String getGenderType() {
        return genderType;
    }

    /**
     * Setter
     * @param genderType
     */
    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }

    /**
     * Getter
     * @return englishWord
     */
    public String getEnglishWord() {
        return englishWord;
    }

    /**
     * Setter
     * @param englishWord
     */
    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }
}



