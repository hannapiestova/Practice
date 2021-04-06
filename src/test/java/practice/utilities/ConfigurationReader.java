package practice.utilities;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;
    static Logger log = Logger.getLogger(ConfigurationReader.class);

    //clean test -D
    static {

        try {
            String path = "";
            if (System.getProperty("environment") == null) {
                path = System.getProperty("user.dir") + "src/test/resources/properties/configuration.properties";
            } else if (System.getProperty("environment").equals("qa")) {
                path = System.getProperty("user.dir") + "src/test/resources/properties/qa.properties";
            } else if ((System.getProperty("environment").equals("prod"))) {
                path = System.getProperty("user.dir") + "src/test/resources/properties/prod.properties";
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            configFile = new Properties();
            configFile.load(fileInputStream);
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            log.info("Failed to load properties file");
        }
    }

    //mvn clean test -P Smoke -Dbrowser=chromeheadless -Denvironment=qa


    public static String getProperty(String keyName){ return configFile.getProperty(keyName);}
}
