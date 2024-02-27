
public class Message implements java.io.Serializable{

    String sender;
    String receiver;
    String body;
    boolean isread=false;
    public Message(String sender,String receiver,String body)
    {
        isread=false;
        this.sender=sender;
        this.receiver=receiver;
        this.body=body;
    }
    public String getBody()
    {
        return body;
    }
    public String getSender()
    {
        return sender;
    }
    public boolean isRead()
    {
        return isread;
    }
    public void setIsread(boolean isread)
    {
        this.isread=isread;
    }

}
