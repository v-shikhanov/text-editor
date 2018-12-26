package editor.search;

import editor.ui.TextEditor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  This class using for searching elements of text inside iu
 */
public class FindMatches extends Thread{
    /**
     *  this method finds all start and end indexes for text fragment from text area.
     */
    private void findMatches() {
        TextSearch.matchNum = 0;
        TextSearch.startIndexes.clear();
        TextSearch.endIndexes.clear();

        String  foundText = TextEditor.getTextField().getText();
        String text = TextEditor.getTextArea().getText();

        if (foundText.length() == 0) {
            return;
        }

        if (TextEditor.getUseRegex().isSelected()) {
            Pattern pattern = Pattern.compile(foundText);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find() ){
                TextSearch.startIndexes.add(matcher.start());
                TextSearch.endIndexes.add(matcher.end());
            }

        } else {
            int index = 0;

            while (true) {
                index = text.indexOf(foundText, index);
                if (index == -1) {
                    break;
                }
                TextSearch.startIndexes.add(index);
                index += foundText.length();
                TextSearch.endIndexes.add(index);
            }
        }
        TextSearch.selectMatch(TextSearch.matchNum);
    }

    @Override
    public void run(){
        findMatches();
    }
}
