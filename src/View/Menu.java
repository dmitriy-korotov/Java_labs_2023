package View;



import com.sun.source.tree.BinaryTree;

import java.util.*;


public class Menu {

    private final Scanner m_scanner;
    private final TreeMap<String, Runnable> m_menu_items;

    public Menu()
    {
        m_scanner = new Scanner(System.in);
        m_menu_items = new TreeMap<>();
    }

    public void addMenuItem(String _menu_title, Runnable _callback)
    {
        m_menu_items.put((m_menu_items.size() + 1) + " - " + _menu_title, _callback);
    }


    public void showGreetings(String _name)
    {
        System.out.printf("Hello, %s\n", _name);
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
            if (i++ == number_of_menu_item)
            {
                callback.run();
                break;
            }
        }

        return true;
    }


    public String selectAnimalType()
    {
        System.out.println("Select animal type (P - Predator, H - Herbivore):\t");

        String animal_type = "";
        while (true)
        {
            animal_type = m_scanner.next();
            if (Objects.equals(animal_type, "H") || Objects.equals(animal_type, "P"))
                break;
            System.out.println("Select correct animal type (P - Predator, H - Herbivore):\t");
        }
        return animal_type;
    }

    public int inputAnimalSize()
    {
        System.out.println("Input animal size:\t");

        int animal_size = 0;
        while (true)
        {
            try
            {
                animal_size = m_scanner.nextInt();
                if (animal_size > 0)
                    break;
                System.out.println("Input correct animal size (greater then 0):\t");
            }
            catch (Exception ex)
            {
                System.out.println("Input correct animal size:\t");
                m_scanner.next();
            }
        }
        return animal_size;
    }


    public String selectPlantType()
    {
        System.out.println("Select plant type (Tree or Grass):\t");

        String plant_type = "";
        while (true)
        {
            plant_type = m_scanner.next();
            if (Objects.equals(plant_type, "Tree") || Objects.equals(plant_type, "Grass"))
                break;
            System.out.println("Select correct plant type (Tree or Grass):\t");
        }
        return plant_type;
    }


    public String selectTreeType()
    {
        System.out.println("Select tree type (Upper or Lower):\t");

        String tree_type = "";
        while (true)
        {
            tree_type = m_scanner.next();
            if (Objects.equals(tree_type, "Upper") || Objects.equals(tree_type, "Lower"))
                break;
            System.out.println("Select correct tree type (Upper or Lower):\t");
        }
        return tree_type;
    }


    public String selectGrassType()
    {
        System.out.println("Select grass type (Upper or Lower):\t");

        String grass_type = "";
        while (true)
        {
            grass_type = m_scanner.next();
            if (Objects.equals(grass_type, "Upper") || Objects.equals(grass_type, "Lower"))
                break;
            System.out.println("Select correct grass type (Upper or Lower):\t");
        }
        return grass_type;
    }
}
