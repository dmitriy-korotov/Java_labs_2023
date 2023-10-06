import Common.Preloader;
import Controller.Application;
import Model.Model;
import View.Menu;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        try
        {
            Model model = new Model();
            Menu menu = new Menu();


            Properties config = new Properties();
            new Preloader("config.ini", config);


            Application application = new Application(model, menu, config);

            application.start();
        }
        catch (Exception | Error _ex)
        {
            System.out.println("Application ERROR:\n" + _ex.getMessage());
        }
        finally
        {
            System.out.println("Application closed.");
        }
    }
}