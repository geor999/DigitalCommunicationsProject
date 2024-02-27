import java.util.*;

public class Account implements java.io.Serializable {
    private String username;
    private int authtoken;
    int getAuthtoken()
    {
        return authtoken;
    }
    String getUsername()
    {
        return username;
    }
    List<Message> Messagebox=new ArrayList<Message>();
    public Account(String username,int authtoken)
    {
        this.username=username;
        this.authtoken=authtoken;
    }
}
