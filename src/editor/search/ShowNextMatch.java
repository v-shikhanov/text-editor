package editor.search;

/**
 * Class for highlighting found elements of text
 * @see TextSearch
 * @see FindMatches
 */
public class ShowNextMatch extends Thread {
    /**
     *  Method just surfing inside of list of matches and selects the next one
     */
    private void selectNext() {
        int matchNum = TextSearch.getMatchNum();
        if (matchNum < TextSearch.getStartIndexes().size() - 1) {
            matchNum++;
            TextSearch.setMatchNum(matchNum);
        } else {
            matchNum = 0;
            TextSearch.setMatchNum(matchNum);
        }
        TextSearch.selectMatch(matchNum);
    }

    @Override
    public void run(){
        selectNext();
    }
}
