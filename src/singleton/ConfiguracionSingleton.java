package singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguracionSingleton {
    
    private static ConfiguracionSingleton instance;
    private Properties properties;

    private ConfiguracionSingleton() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("No se puede encontrar el archivo properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ConfiguracionSingleton obtenerInstancia() {
        if (instance == null) {
            instance = new ConfiguracionSingleton();
        }
        return instance;
    }

    public String obtenerParametro(String key) {
        return properties.getProperty(key);
    }

}
