package Model;


import Common.User;
import Common.Logger;
import Lab2.Forest;


public class Model {

    private final String m_path_to_config;
    private String m_path_to_db;
    private boolean m_is_need_logging;
    private boolean m_is_debug_mode;
    private Logger m_logger;


    private final User m_user;

    private final Forest m_forest;



    public Model()
    {
        m_user = new User();
        m_forest = new Forest();
        m_path_to_config = "";
        m_path_to_db = "";
        m_is_need_logging = false;
        m_is_debug_mode = false;
    }


    public String getPathToConfig()
    {
        return m_path_to_config;
    }
    public  String getPathToDB() { return m_path_to_db; }


    public boolean isNeedLogging() { return m_is_need_logging; }

    public boolean isDebugMode() { return m_is_debug_mode; }

    public void enableLogging()
    {
        m_logger = new Logger();
        m_is_need_logging = true;
    }
    public void disableLogging() { m_is_need_logging = false; }


    public void enableDebugMode() { m_is_debug_mode = true; }
    public void disableDebugMode() { m_is_debug_mode = false; }


    public void setupLogger(String _path_to_log_file) { m_logger = new Logger(_path_to_log_file); }
    public void setPathToDB(String _path_to_db) { m_path_to_db = _path_to_db; }


    public User getUser() { return m_user; }
    public Logger getLogger() { return m_logger; }
    public Forest getForest() { return m_forest; }

}
