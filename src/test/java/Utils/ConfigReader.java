package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static String getPropValue(String key) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.getProperty(key);
    }
}
