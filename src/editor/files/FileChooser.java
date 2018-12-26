package editor.files;

import editor.ui.TextEditor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *  Class for selects files via JFileChooser UI element for open and save it
 *
 * @see FileLoader - opens and saves files
 */
public class FileChooser {

    /**
     * This method opens file
     */
    public static void open() {
        JFileChooser fileChooser;
        String fileContent;
        /*
           Try to use last path, or start with default one
         */
        String path = FileLoader.loadFile("path.txt");
        if (path != null){
            fileChooser = new JFileChooser(path);
        } else {
            fileChooser = new JFileChooser((FileSystemView.getFileSystemView().getHomeDirectory()));
        }

        fileChooser.setDialogTitle("Select text file");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files","mytxt");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(true);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            /*
                Remember new start path
             */
            path = fileChooser.getSelectedFile().getParent();
            FileLoader.saveFile("path.txt", path, false);
            /*
                Load contents from file
             */
            fileContent = FileLoader.loadFile(fileChooser.getSelectedFile().getAbsolutePath());
            TextEditor.setFileName(fileChooser.getSelectedFile().getAbsolutePath());
            TextEditor.getTextArea().setText(fileContent);
        }
    }

    /**
     * This metod saves the file
     * @return path to it
     */
    static String save() {
        JFileChooser fileChooser;
        /*
           Try to use last path, or start with default one
         */
        String path = FileLoader.loadFile("path.txt");
        if (path != null){
            fileChooser = new JFileChooser(path);
        } else {
            fileChooser = new JFileChooser((FileSystemView.getFileSystemView().getHomeDirectory()));
        }

        fileChooser.setDialogTitle("Save text file");
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            /*
                Remember new start path
             */
            FileLoader.saveFile("path.txt", fileChooser.getSelectedFile().getParent(), false);

            return fileChooser.getSelectedFile().getAbsolutePath() + ".mytxt" ;
        }

        return null;
    }
}
