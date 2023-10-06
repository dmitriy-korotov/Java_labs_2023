package Common;

public class User {

    private String m_login;
    private String m_password;

    private UserGroup m_group;

    public User() { }

    public void setLogin(String _login) { m_login = _login; }
    public void setPassword(String _password) { m_password = _password; }
    public void setGroup(UserGroup _user_group) { m_group = _user_group; }

    public String getLogin() { return m_login; }
    public String getPassword() {return m_password; }
    public  UserGroup getGroup() { return m_group; }
}
