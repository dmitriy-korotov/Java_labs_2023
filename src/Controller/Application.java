package Controller;


import Common.FileWriter;
import Common.UserGroup;
import Lab2.Forest;
import Lab2.GrassType;
import Lab2.TreeType;
import Model.Model;
import View.Menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Properties;


public class Application {

    private final Menu m_view_component;
    private final Model m_model_component;
    private boolean m_is_closed;


    public Application(Model _model, Menu _menu, Properties _config)
    {
        m_is_closed = true;

        m_model_component = _model;
        m_view_component = _menu;

        setupModel(_config);
        setupMenu();
    }


    private void setupModel(Properties _config) throws NoSuchFieldError
    {
        if (!_config.containsKey("login"))
            throw new NoSuchFieldError("Config is not contains nessessory field 'login'");
        m_model_component.getUser().setLogin(_config.get("login").toString());

        if (!_config.containsKey("password"))
            throw new NoSuchFieldError("Config is not contains nessessory field 'password'");
        m_model_component.getUser().setPassword(_config.get("password").toString());

        if (!_config.containsKey("is_need_logging"))
            throw new NoSuchFieldError("Config is not contains nessessory field 'is_need_logging'");
        if (Objects.equals(_config.get("is_need_logging").toString(), "true"))
        {
            m_model_component.enableLogging();
        }

        if (!_config.containsKey("group"))
            throw new NoSuchFieldError("Config is not contains nessessory field 'group'");
        UserGroup group = Objects.equals(_config.get("group").toString(), "root") ? UserGroup.Root : UserGroup.User;
        m_model_component.getUser().setGroup(group);

        if (!_config.containsKey("db"))
            throw new NoSuchFieldError("Config is not contains nessessory field 'db'");
        String path_to_db = _config.get("db").toString();
        m_model_component.setPathToDB(path_to_db);
    }


    private void setupMenu()
    {
        m_view_component.addMenuItem("Close program", this::close);
        m_view_component.addMenuItem("Load models from file", this::readModelsFromFile);
        m_view_component.addMenuItem("Write models to file", this::writeModelsToFile);
        m_view_component.addMenuItem("Add new animal", this::addNewAnimal);
        m_view_component.addMenuItem("Add new plant", this::addNewPlant);
        m_view_component.addMenuItem("Print forest", this::printForest);
    }


    public void start() throws IOException
    {
        m_view_component.showGreetings(m_model_component.getUser().getLogin());

        if (m_model_component.isNeedLogging())
            m_model_component.getLogger().log("Application started, user: " + m_model_component.getUser().getLogin());

        m_is_closed = false;
        while (!m_is_closed)
        {
            m_view_component.showMenu();
            if (!m_view_component.selectMenuItem())
            {
                if (m_model_component.isNeedLogging())
                    m_model_component.getLogger().log("Selected incorrect menu item");
            }
        }
    }


    public void close()
    {
        m_is_closed = true;
        if (m_model_component.isNeedLogging())
            m_model_component.getLogger().log("Application closed");
    }



    private String generateDumpedView(BufferedReader file_reader) throws IOException
    {
        StringBuilder dumped_view = new StringBuilder();
        while (true)
        {
            String line = file_reader.readLine();
            if (Objects.equals(line, "Animal") || Objects.equals(line, "Plant") || Objects.equals(line, null))
                break;

            dumped_view.append(line).append("\n");
        }
        return dumped_view.toString();
    }



    public void readModelsFromFile()
    {
        try
        {
            BufferedReader file_reader = new BufferedReader(new FileReader(m_model_component.getPathToDB()));

            String dumped_view;

            while (true)
            {
                String line = file_reader.readLine();
                if (Objects.equals(line, null) || Objects.equals(line, "\n"))
                    break;

                if (Objects.equals(line, "Predator"))
                {
                    dumped_view = generateDumpedView(file_reader);
                    m_model_component.getForest().addNewPredator(dumped_view);
                }

                if (Objects.equals(line, "Herbivore"))
                {
                    dumped_view = generateDumpedView(file_reader);
                    m_model_component.getForest().addNewHerbivore(dumped_view);
                }

                if (Objects.equals(line, "Tree"))
                {
                    dumped_view = generateDumpedView(file_reader);
                    m_model_component.getForest().addNewTree(dumped_view);
                }

                if (Objects.equals(line, "Grass"))
                {
                    dumped_view = generateDumpedView(file_reader);
                    m_model_component.getForest().addNewGrass(dumped_view);
                }
            }

            file_reader.close();
        }
        catch (Exception ex)
        {
            if (m_model_component.isNeedLogging())
                m_model_component.getLogger().log(ex.getMessage());
        }

        if (m_model_component.isNeedLogging())
            m_model_component.getLogger().log("Read data from Data Base");
    }


    public  void writeModelsToFile()
    {
        try
        {
            FileWriter writer = new FileWriter(m_model_component.getPathToDB());
            writer.write(m_model_component.getForest().dump());
        }
        catch (Exception ex)
        {
            if (m_model_component.isNeedLogging())
                m_model_component.getLogger().log(ex.getMessage());
        }

        if (m_model_component.isNeedLogging())
            m_model_component.getLogger().log("Write data to Data Base");
    }


    public void addNewAnimal()
    {
        String animal_type = m_view_component.selectAnimalType();
        int animal_size = m_view_component.inputAnimalSize();

        if (Objects.equals(animal_type, "P"))
            m_model_component.getForest().addNewPredator(animal_size);
        else
            m_model_component.getForest().addNewHerbivore(animal_size, new HashSet<>(), new HashSet<>());
    }


    public void addNewPlant()
    {
        Forest forest = m_model_component.getForest();

        String plant_type = m_view_component.selectPlantType();
        if (Objects.equals(plant_type, "Tree"))
        {
            String tree_type = m_view_component.selectTreeType();
            forest.addNewTree(Objects.equals(tree_type, "Upper") ? TreeType.UpperTree : TreeType.LowerTree);
        }
        else
        {
            String grass_type = m_view_component.selectGrassType();
            forest.addNewGrass(Objects.equals(grass_type, "Upper") ? GrassType.UpperGrass : GrassType.LowerGrass);
        }
    }


    public void printForest()
    {
        System.out.println(m_model_component.getForest());
    }
}
