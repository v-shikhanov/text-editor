package editor;

public class ShowPrevMatch extends Thread{

    private void selectPrev(){
        if (TextSearch.matchNum > 0) {
            TextSearch.matchNum--;
        } else {
            TextSearch.matchNum = TextSearch.startIndexes.size()-1;
        }
        TextSearch.selectMatch(TextSearch.matchNum);
    }

    @Override
    public void run(){
        selectPrev();
    }
}
