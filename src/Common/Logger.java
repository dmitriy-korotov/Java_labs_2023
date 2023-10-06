package Common;

import java.io.*;

public class Logger {

    private final String m_path_to_log_file;



    public Logger()
    {
        m_path_to_log_file = "journal.log";
    }

    public Logger(String _path_to_log_file)
    {
        m_path_to_log_file = _path_to_log_file;
    }

    public String getPathToLogFile() { return m_path_to_log_file; }



    public void log(String _message) throws IOException
    {
        File log_file = new File(m_path_to_log_file);

        OutputStream file_stream = new FileOutputStream(log_file);

        file_stream.write((_message + "\n").getBytes());

        file_stream.close();
    }
}
