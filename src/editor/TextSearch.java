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

    private Thread find = new FindMatches();
    private Thread showNextMatch = new ShowNextMatch();
    private Thread showPrevMatch = new ShowPrevMatch();

    final public static int  NEXT = 3;
    final public static int  FIND = 2;
    final public static int  PREV = 1;


    void search(int operationCode){

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



    static void selectMatch( int matchNum ) {
        if (startIndexes.size() <= matchNum || matchNum < 0) {
            return;
        }
        TextEditor.textArea.setCaretPosition(endIndexes.get(matchNum));
        TextEditor.textArea.select(startIndexes.get(matchNum), endIndexes.get(matchNum));
        TextEditor.textArea.grabFocus();
    }
}


