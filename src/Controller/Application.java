package Controller;


import Common.UserGroup;
import Model.Model;
import View.Menu;

import java.io.IOException;
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

        if (_config.containsKey("debug"))
        {

        }
    }


    private void setupMenu()
    {
        m_view_component.addMenuItem("Close program", this::close);
    }


    public void start() throws IOException
    {
        m_is_closed = false;
        while (!m_is_closed)
        {
            m_view_component.showMenu();
            if (!m_view_component.selectMenuItem())
            {
                if (m_model_component.isNeedLogging())
                {
                    m_model_component.getLogger().log("Selected incorrect menu item");
                }
            }
        }
    }


    public void close()
    {
        m_is_closed = true;
    }
}
