package editor.search;
/**
 *  Class for highlighting found elements of text
 * @see TextSearch
 * @see FindMatches
 */
public class ShowPrevMatch extends Thread {

    /**
     *  Method just surfing inside of list of matches and selects the previous one
     */
    private void selectPrev( ) {
        int matchNum = TextSearch.getMatchNum();
        if (matchNum > 0) {
            matchNum--;
            TextSearch.setMatchNum(matchNum);
        } else {
            matchNum = TextSearch.getStartIndexes().size() - 1;
            TextSearch.setMatchNum(matchNum);
        }
        TextSearch.selectMatch(matchNum);
    }

    @Override
    public void run(){
        selectPrev();
    }
}
