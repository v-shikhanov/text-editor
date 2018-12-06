package editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSearch extends Thread {

    static  ArrayList<Integer> startIndexes = new ArrayList<>();
    static  ArrayList<Integer> endIndexes = new ArrayList<>();

    private static int matchNum = 0;

    static void search(){
        matchNum = 0;
        startIndexes.clear();
        endIndexes.clear();
        findAllEntrings();

    }

    static private void findAllEntrings() {
        String  foundText = TextEditor.textField.getText();
        String text = TextEditor.textArea.getText();

        if (foundText.length() == 0) {
            return;
        }

        if (TextEditor.useRegex.isSelected()) {
            Pattern pattern = Pattern.compile(foundText);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find() ){
                startIndexes.add(matcher.start());
                endIndexes.add(matcher.end());
            }

        } else {
            int index = 0;

            while (true) {
                index = text.indexOf(foundText, index);
                if (index == -1) {
                    break;
                }
                startIndexes.add(index);
                index += foundText.length();
                endIndexes.add(index);
            }
        }
        selectMatch(matchNum);
    }

    private static void selectMatch( int matchNum ) {
        if (startIndexes.size() <= matchNum) {
            return;
        }
        TextEditor.textArea.setCaretPosition(endIndexes.get(matchNum));
        TextEditor.textArea.select(startIndexes.get(matchNum), endIndexes.get(matchNum));
        TextEditor.textArea.grabFocus();
    }

    @Override
    public void run(){
        search();
    }
}




class FindAllEntrings{

}
