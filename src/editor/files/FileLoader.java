package editor.files;
import editor.ui.TextEditor;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
/**
 * Class for working with files. *
 * @see FileChooser selects files to open or save
 */
public class FileLoader {
    /**
     * This method loads text from file
     * @param fileName
     * @return text from file, if it exists
     */
    static String loadFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile()) {
            try {
               return Files.readString(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Method saves text to file
     * @param fileName
     * @param text
     * @param usePrintLn true if every string should be started from a new line
     */
    public static void saveFile(String fileName, String text, boolean usePrintLn) {
        if (fileName == null) {
            fileName = FileChooser.save();
        }
        if (fileName == null) {
            return;
        } else {
            TextEditor.setFileName(fileName);
        }

        File file = new File(fileName);
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintStream print = new PrintStream(file);
            Scanner scText = new Scanner(text);
            while (scText.hasNext()) {
                if (usePrintLn){
                    print.println(scText.nextLine());
                } else {
                    print.print(scText.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
