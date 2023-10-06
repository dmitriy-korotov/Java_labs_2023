package Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Preloader {
    public Preloader(String _file_name, Properties _properties) throws Exception
    {
        loadConfigFile(_file_name, _properties);
        Preloader.class.getResource("config.ini");
    }


    private static void loadConfigFile(String _file_name, Properties _properties) throws Exception
    {
        InputStream fs;
        try
        {
            File f = new File(_file_name);
            if (f.exists())
            {
                fs = new FileInputStream(f);
            }
            else
            {
                fs = Preloader.class.getResourceAsStream(_file_name);
            }
            if (fs == null)
                throw new Exception("Can't open file.");
            _properties.load(fs);
            fs.close();
        }
        catch (FileNotFoundException _ex)
        {
            System.err.println("ERROR finding config file. ERROR: " + _ex.getMessage());
        }
        catch (IOException _ex)
        {
            System.err.println("ERROR reading file. ERROR: " + _ex.getMessage());
        }
        catch (Exception _ex)
        {
            throw new Exception("ERROR loading config file: => " + _ex.getMessage());
        }
    }
}