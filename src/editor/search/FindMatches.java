package editor.search;

import editor.ui.TextEditor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class using for searching elements of text inside iu
 */
public class FindMatches extends Thread {
    /**
     * This method finds all start and end indexes for text fragment from text area.
     */
    private void findMatches() {
        TextSearch.setMatchNum(0);
        TextSearch.getStartIndexes().clear();
        TextSearch.getEndIndexes().clear();

        String text = TextEditor.getTextField().getText();
        String patternS = TextEditor.getTextArea().getText();

        if (text.length() == 0) {
            return;
        }

        if (TextEditor.getUseRegex().isSelected()) {
            Pattern pattern = Pattern.compile(text);
            Matcher matcher = pattern.matcher(patternS);

            while (matcher.find()) {
                TextSearch.getStartIndexes().add(matcher.start());
                TextSearch.getEndIndexes().add(matcher.end());
            }

        } else {
            int index = 0;

            while (true) {
                index = patternS.indexOf(text, index);
                if (index == -1) {
                    break;
                }
                TextSearch.getStartIndexes().add(index);
                index += text.length();
                TextSearch.getEndIndexes().add(index);
            }
        }
        TextSearch.selectMatch(TextSearch.getMatchNum());
    }

    @Override
    public void run(){
        findMatches();
    }
}
