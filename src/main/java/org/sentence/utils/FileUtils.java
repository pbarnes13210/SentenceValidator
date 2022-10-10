package org.sentence.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtils {
    private static final Logger LOGGER = LogManager.getLogger(FileUtils.class.getName());

    public static void findPropertiesFile(Properties properties) {

        try(InputStream input = new FileInputStream(Constants.PROPERTIES_FILE_LOCATION)){
            properties.load(input);
            LOGGER.warn("Properties file found, loading config");
        }catch (IOException ex) {
            LOGGER.warn("Properties file not found");
        }
    }


}
