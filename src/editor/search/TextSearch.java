package editor.search;

import editor.ui.TextEditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for searching elements of text inside of it
 * @see FindMatches -creates list of indexes of match(their coordinates in text)
 * @see ShowPrevMatch -selects previous match
 * @see ShowNextMatch -selects next match
 */
public class TextSearch {
    /**
     * List of indexes of matches inside of text for highlighting found pattern in text
     * end indexes used for regex use case when length of pattern could be different
     */
    private static List<Integer> startIndexes = Collections.synchronizedList(new ArrayList<>());
    private static List<Integer> endIndexes = Collections.synchronizedList(new ArrayList<>());
    /**
     * Current position inside of matches indexes
     */
    private static volatile int matchNum;

    /**
     * Thread for work with searching
     */
    private Thread find;
    private Thread showNextMatch;
    private Thread showPrevMatch;

    /**
     * Constants for work with searching
     */
    public enum SearchingAction {
        NEXT,
        FIND,
        PREV
    }

    /**
     * Constructor of class
     */
    public TextSearch() {
        matchNum = 0;
        find = new FindMatches();
        showNextMatch = new ShowNextMatch();
        showPrevMatch = new ShowPrevMatch();
    }

    /**
     * Method for matches searching and surfing inside of matches
     * @param searchingAction what operation should be completed
     */
    public void search(SearchingAction searchingAction) {

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

        switch (searchingAction){
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
        }
    }

    /**
     * Method highlights found match
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

    /*
        Getters and setters
     */
    public static List<Integer> getStartIndexes() {
        return startIndexes;
    }

    public static List<Integer> getEndIndexes() {
        return endIndexes;
    }

    public static int getMatchNum() {
        return matchNum;
    }

    public static void setMatchNum(int matchNum) {
        TextSearch.matchNum = matchNum;
    }
}


