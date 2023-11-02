package org.example;

import java.io.IOException;

public class App
{
    private static final String m_hostname = "ftp.mccme.ru";



    public static void main( String[] args ) {

        FTPLoaderApplication application = new FTPLoaderApplication(m_hostname, 1024, 720);
        application.SetHostname(m_hostname);
        application.Start();
    }
}
