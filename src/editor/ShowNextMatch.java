package editor;

public class ShowNextMatch extends Thread{
    private void selectNext(){
        while (TextSearch.threadAtWork) {}
        TextSearch.setThreadAtWork(true);

        if (TextSearch.matchNum < TextSearch.startIndexes.size()-1) {
            TextSearch.matchNum++;
        } else {
            TextSearch.matchNum = 0;
        }
        TextSearch.selectMatch(TextSearch.matchNum);
        TextSearch.setThreadAtWork(false);
    }

    @Override
    public void run(){
        selectNext();
    }
}
