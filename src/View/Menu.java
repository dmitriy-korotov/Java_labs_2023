package View;



import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Menu {

    private final Scanner m_scanner;
    private final Map<String, Runnable> m_menu_items;

    public Menu()
    {
        m_scanner = new Scanner(System.in);
        m_menu_items = new HashMap<>();
    }

    public void addMenuItem(String _menu_title, Runnable _callback)
    {
        m_menu_items.put((m_menu_items.size() + 1) + " - " + _menu_title, _callback);
    }


    public void showGreetings(String _name)
    {
        System.out.printf("Hello, %s", _name);
    }


    public void showMenu()
    {
       for (String title : m_menu_items.keySet())
       {
           System.out.println(title);
       }
    }


    public boolean selectMenuItem()
    {
        System.out.println("Select menu item:\t");

        int number_of_menu_item = 0;
        try {
            number_of_menu_item = m_scanner.nextInt();
            if (number_of_menu_item > m_menu_items.size())
            {
                System.out.printf("Please select correct menu item (from 1 to %s):\t%n", m_menu_items.size());
                return false;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Please select correct menu item:\t");
            m_scanner.next();
            return false;
        }

        int i = 1;
        for (Runnable callback : m_menu_items.values())
        {
            if (i == number_of_menu_item)
            {
                callback.run();
                break;
            }
        }

        return true;
    }
}
