import java.rmi.*;

public interface Accountholder extends Remote {
    String CreateAccount(String username) throws RemoteException;
    String ShowAccounts(String ID) throws RemoteException;
    String SendMessage(String authtoken,String recipient,String text) throws RemoteException;
    String ShowInbox(String authtoken) throws RemoteException;
    String  ReadMessage(String authtoken ,String messageID) throws RemoteException;
    String  DeleteMessage(String authtoken,String messageID) throws RemoteException;

}
