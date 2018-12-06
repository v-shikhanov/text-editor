package editor;

public class ShowPrevMatch extends Thread{
    private void selectPrev(){
        while (TextSearch.threadAtWork) {}
        TextSearch.setThreadAtWork(true);
        if (TextSearch.matchNum > 0) {
            TextSearch.matchNum--;
        } else {
            TextSearch.matchNum = TextSearch.startIndexes.size()-1;
        }
        TextSearch.selectMatch(TextSearch.matchNum);
        TextSearch.setThreadAtWork(false);
    }

    @Override
    public void run(){
        selectPrev();
    }
}
