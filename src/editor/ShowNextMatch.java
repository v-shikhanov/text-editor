package editor;

public class ShowNextMatch extends Thread{

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
