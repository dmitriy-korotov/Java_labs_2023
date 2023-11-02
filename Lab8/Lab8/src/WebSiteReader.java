import com.sun.net.httpserver.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpResponse;

public class WebSiteReader {

    static final int CONNECTION_TIMEUOT = 1000;



    public static String ReadPageFrom(URL _url) throws IOException {

        final HttpURLConnection connection = (HttpURLConnection) _url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/html");
        connection.setConnectTimeout(CONNECTION_TIMEUOT);
        connection.setReadTimeout(CONNECTION_TIMEUOT);

        try (final BufferedReader reader = new BufferedReader(
                                           new InputStreamReader(connection.getInputStream()))) {

            String line;
            final StringBuilder sBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sBuilder.append(line);
            }

            return sBuilder.toString();
        }
        catch (Exception | Error _ex) {
            _ex.printStackTrace();
            return "";
        }
    }

}
