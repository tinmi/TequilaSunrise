package src;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Tinnacher
 */
public class Main {

    public static void main(String[] args) {
        try {
            new Main().loadExif();
        } catch (IOException | ImageProcessingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadExif() throws FileNotFoundException, IOException, ImageProcessingException {

        InputStream ins = new FileInputStream(new File("C:/Users/Michael Tinnacher/Desktop/Nikon D810.nef"));
        Metadata metadata = ImageMetadataReader.readMetadata(ins);

        for (Directory directory : metadata.getDirectories()) {
            directory.getTags().forEach((tag) -> {
                System.out.println(directory.getName() + " " + tag.getTagName() + " " + tag.getDescription());
            });
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }
    }
}
