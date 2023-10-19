package Common;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private final String m_path_to_log_file;



    public Logger()
    {
        m_path_to_log_file = "journal.log";
        clearLog(m_path_to_log_file);
    }

    public Logger(String _path_to_log_file)
    {
        m_path_to_log_file = _path_to_log_file;
        clearLog(m_path_to_log_file);
    }

    public String getPathToLogFile() { return m_path_to_log_file; }



    public void log(String _message)
    {
        try
        {
            FileWriter file_writer = new FileWriter(m_path_to_log_file);

            LocalTime time = LocalTime.now();
            String log = time.format(DateTimeFormatter.ofPattern("H:mm:ss")) + " => " + _message + "\n";

            file_writer.write(log);
        }
        catch (Exception ex)
        {
            System.out.println("Catch exception: " + ex.getMessage());
        }
    }


    private void clearLog(String _path_to_log)
    {
        try
        {
            java.io.FileWriter fwOb = new java.io.FileWriter(_path_to_log);
            PrintWriter pwOb = new PrintWriter(fwOb, true);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        }
        catch (Exception _ex)
        {
            System.out.println("Can't flush file: " + _path_to_log);
        }
    }
}
