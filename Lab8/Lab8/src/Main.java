import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.RunnableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static String url;

    private static String url_regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    private static Scanner m_scanner = new Scanner(System.in);


    private static void RunWebSiteReader() {

        System.out.println("Input site url:\t");

        while (true) {
            try {
                url = m_scanner.next();
                if (IsMatch(url, url_regex)) {
                    break;
                }
                System.out.println("ERROR: Incorrect url address, try again:");

            } catch (Exception | Error _ex) {
                System.out.println("ERROR: Incorrect url address, try again:");
                m_scanner.next();
            }
        }

        try {
            String content = WebSiteReader.ReadPageFrom(new URL(url));
            System.out.println(content);
        } catch (Exception | Error _ex) {
            System.out.println(String.format("Exception: %s", _ex.getMessage()));
        }
    }


    public static void main(String[] args) {
        RunWebSiteReader();
    }



    private static boolean IsMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException _ex) {
            return false;
        }
    }
}