package editor.search;

/**
 *  Class for highlighting found elements of text
 * @see TextSearch
 * @see FindMatches
 */
public class ShowNextMatch extends Thread{

    /**
     *  Method just surfing inside of list of matches and selects the next one
     */
    private void selectNext(){
        if (TextSearch.matchNum < TextSearch.startIndexes.size()-1) {
            TextSearch.matchNum++;
        } else {
            TextSearch.matchNum = 0;
        }
        TextSearch.selectMatch(TextSearch.matchNum);
    }

    @Override
    public void run(){
        selectNext();
    }
}
