package editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSearch {

    static  ArrayList<Integer> startIndexes = new ArrayList<>();
    static  ArrayList<Integer> endIndexes = new ArrayList<>();

    static int matchNum = 0;

    static boolean threadAtWork = false;

    void search(){
        Thread find = new FindMatches();
        /*
            protect from second call
         */
        if (!find.isAlive()) {
            find.start();
        }
    }

    void nextMatch(){
        ShowNextMatch showNextMatch = new ShowNextMatch();
        if(!showNextMatch.isAlive()) {
            showNextMatch.run();
        }
    }

    void prevMatch(){
        ShowPrevMatch showPrevMatch = new ShowPrevMatch();
        if (!showPrevMatch.isAlive()) {
            showPrevMatch.run();
        }
    }

    synchronized static void setThreadAtWork(boolean val) {
        threadAtWork = val;
    }

    static void selectMatch( int matchNum ) {
        if (startIndexes.size() <= matchNum) {
            return;
        }
        TextEditor.textArea.setCaretPosition(endIndexes.get(matchNum));
        TextEditor.textArea.select(startIndexes.get(matchNum), endIndexes.get(matchNum));
        TextEditor.textArea.grabFocus();
    }
}


