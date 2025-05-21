package utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by sajeekam on 5/15/2025
 */


//Data retrieve from property file
public class PropertyFileReader {


    //Singleton Design Pattern
    private static PropertyFileReader object;

    public static PropertyFileReader getInstance() {
        if (object == null) {
            object = new PropertyFileReader();
            System.out.println("Creating New Object");
            return object;
        } else {
            System.out.println("Using Existing Object");
            return object;
        }
    }


    private Properties getData(String fileName) {

        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "test" + File.separator + "resources" + File.separator + fileName + ".properties";

        File file = new File(path);

        if (!file.exists()) {
            throw new RuntimeException("Property file not found at :" + path);
        }

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties prop = new Properties();
        try {
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public String getProperty(String fileName, String key) {
        return getData(fileName).getProperty(key);
    }
}
