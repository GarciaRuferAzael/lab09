package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String DEF_FILE = "output.txt";

    private File dest = new File(HOME + File.separator + DEF_FILE);

    /**
     * Returns the current file
     * 
     * @return the current file
     */
    public File getCurrentFile(){
        return dest;
    }

    /**
     * Returns the current file path
     * 
     * @return the current file path
     */
    public String getCurrentFilePath(){
        return dest.getPath();
    }

    /**
     * Save content on current file
     * 
     * @param string the text to save
     * 
     * @throws IOException if it fails
     */
    public void content(final String text) throws IOException{
        try(PrintStream out = new PrintStream(dest, StandardCharsets.UTF_8)){
            out.println(text);
        }
    }

    /**
     * Sets a new destination file
     * 
     * @param file where to write
     */
    public void setDestination(final File file){
        final File parent = file.getParentFile();
        if (parent.exists()) {
            dest = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder");
        }
    }

    /**
     * Sets a new destination file
     * 
     * @param file where to write
     */
    public void setDestination(final String file){
        setDestination(new File(file));
    }
}
