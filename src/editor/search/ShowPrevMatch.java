package editor.search;
/**
 *  Class for highlighting found elements of text
 * @see TextSearch
 * @see FindMatches
 */
public class ShowPrevMatch extends Thread{

    /**
     *  Method just surfing inside of list of matches and selects the previous one
     */
    private void selectPrev(){
        if (TextSearch.matchNum > 0) {
            TextSearch.matchNum--;
        } else {
            TextSearch.matchNum = TextSearch.startIndexes.size()-1;
        }
        TextSearch.selectMatch(TextSearch.matchNum);
    }

    @Override
    public void run(){
        selectPrev();
    }
}
