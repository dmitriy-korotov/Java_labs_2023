package Common;

import java.io.*;

public class FileWriter {

    private final String m_path_to_file;

    public FileWriter(String _path_to_file)
    {
        m_path_to_file = _path_to_file;
    }


    public void write(String _data) throws IOException
    {
        java.io.FileWriter file_writer = new java.io.FileWriter(m_path_to_file, true);
        file_writer.write(_data);
        file_writer.close();
    }
}
