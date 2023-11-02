package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.nio.file.Path;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPLoader {

    private static final int m_default_port = 21;




    public boolean load(String _hostname, String _destination) throws IOException
    {
        Path filepath = Path.of(_destination);

        FTPClient ftpClient = new FTPClient();

        try {

            ftpClient.connect(_hostname, m_default_port);

            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println(
                        "Operation failed. Server reply code: "
                                + replyCode);
                ftpClient.disconnect();
            }

            ftpClient.login("anonymous", "");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(filepath.getParent().toString());

            String filename = filepath.getFileName().toString();

            File local_file = new File(filename);
            FileOutputStream file_out = new FileOutputStream(local_file);

            boolean is_loaded = ftpClient.retrieveFile(_destination, file_out);

            file_out.close();

            if (!is_loaded) {
                System.out.println("Fail to download file, " + ftpClient.getReplyString());
                boolean is_deleted = local_file.delete();
                ftpClient.disconnect();
                return false;
            }

            ftpClient.disconnect();
        }
        catch (UnknownHostException E) {
            System.out.println("No such ftp server");
            return false;
        }
        catch (Exception | Error _ex) {
            System.out.println(_ex.getMessage());
            return false;
        }
        return true;
    }
}