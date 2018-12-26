package editor.search;

import editor.ui.TextEditor;

import java.util.ArrayList;

/**
 *  Base class for searching elements of text inside of it
 *
 * @see FindMatches -creates list of indexes of match(their coordinates in text)
 * @see ShowPrevMatch -selects previous match
 * @see ShowNextMatch -selects next match
 */
public class TextSearch {

    /**
     *  List of indexes of matches inside of text
     */
    static  ArrayList<Integer> startIndexes;
    static  ArrayList<Integer> endIndexes;
    /**
     *  Current position inside of matches indexes
     */
    static int matchNum;

    /**
     * Thread for work with searching
     */
    private Thread find;
    private Thread showNextMatch;
    private Thread showPrevMatch;

    /**
     * Constants for work with searching
     */
    final public static int  NEXT = 3;
    final public static int  FIND = 2;
    final public static int  PREV = 1;

    /**
     *  Constructor of class
     */
    public TextSearch() {
        startIndexes = new ArrayList<>();
        endIndexes = new ArrayList<>();
        matchNum = 0;

        find = new FindMatches();
        showNextMatch = new ShowNextMatch();
        showPrevMatch = new ShowPrevMatch();

    }

    /**
     * Method for matches searching and surfing inside of matches
     * @param operationCode what operation should be completed
     */
    public void search(int operationCode){

        try {
            find.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            showNextMatch.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            showPrevMatch.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (operationCode){
            case NEXT : {
                showNextMatch.run();
                break;
            }

            case FIND : {
                find.run();
                break;
            }

            case PREV : {
                showPrevMatch.run();
                break;
            }

            default : break;
        }
    }

    /**
     * Method highlits found match
     * @param matchNum -position of match inside of match list( what match should be selected)
     */
    static void selectMatch(int matchNum) {
        if (startIndexes.size() <= matchNum || matchNum < 0) {
            return;
        }

        TextEditor.getTextArea().setCaretPosition(endIndexes.get(matchNum));
        TextEditor.getTextArea().select(startIndexes.get(matchNum), endIndexes.get(matchNum));
        TextEditor.getTextArea().grabFocus();
    }
}


