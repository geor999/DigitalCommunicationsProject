import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class RemoteAccountholder extends UnicastRemoteObject implements Accountholder {
    public RemoteAccountholder() throws RemoteException {
        super();
    }

    public String CreateAccount(String username) throws RemoteException {

        for (int j = 0; j < Server.Accounts.size(); j++) {
            if (Server.Accounts.get(j).getUsername().equals(username)) {
                return "Sorry, the user already exists";
            }
        }
        for (int j = 0; j < username.length(); j++) {
            char c = username.charAt(j);
            if (!(Character.isDigit(c) || Character.isLetter(c) || c == '_')) {
                return "Invalid Username";
            }
        }
        Random rand = new Random();
        int ID = rand.nextInt(9999999);
        Account a = new Account(username, ID);
        Server.Accounts.add(a);
        String s = String.valueOf(ID);
        return s;
    }

    public String ShowAccounts(String authtoken) throws RemoteException {
        for (int j = 0; j < Server.Accounts.size(); j++) {
            if ((Server.Accounts.get(j).getAuthtoken() == Integer.parseInt(authtoken))) {
                String a = "";
                for (int k = 0; k < Server.Accounts.size(); k++) {
                    String b = String.valueOf(k + 1);
                    a = a + b + ". " + Server.Accounts.get(k).getUsername();
                    if (k + 1 != Server.Accounts.size()) {
                        a += '\n';
                    }
                }
                return a;
            }
        }
        return "Invalid Auth Token\n";
    }

    public String SendMessage(String authtoken, String recipient, String text) throws RemoteException {
        for (int j = 0; j < Server.Accounts.size(); j++) {
            if (Server.Accounts.get(j).getAuthtoken() == Integer.parseInt(authtoken)) {
                for (int k = 0; k < Server.Accounts.size(); k++) {
                    if ((Server.Accounts.get(k).getUsername().equals(recipient))) {
                        Server.Accounts.get(k).Messagebox.add(new Message(Server.Accounts.get(j).getUsername(), Server.Accounts.get(k).getUsername(), text));
                        return "OK";
                    }
                }
            }
            return "User does not exist";
        }
        return "Invalid Auth Token\n";
    }

    public String ShowInbox(String authtoken) throws RemoteException {
        for (int j = 0; j < Server.Accounts.size(); j++) {
            if ((Server.Accounts.get(j).getAuthtoken() == Integer.parseInt(authtoken))) {
                String v = "";
                for (int q = 0; q < Server.Accounts.get(j).Messagebox.size(); q++) {
                    int r = q + 1;
                    v = v + r + ". from:" + Server.Accounts.get(j).Messagebox.get(q).getSender();
                    if (Server.Accounts.get(j).Messagebox.get(q).isRead()) {
                        v += "\n";
                    } else {
                        v += "*\n";
                    }
                }
                return v;
            }
        }
        return "Invalid Auth Token\n";
    }

    public String ReadMessage(String authtoken, String messageID) throws RemoteException {
        for (int j = 0; j < Server.Accounts.size(); j++) {
            if ((Server.Accounts.get(j).getAuthtoken() == Integer.parseInt(authtoken))) {
                String a = "";
                int t = 0;
                for (int q = 0; q < Server.Accounts.get(j).Messagebox.size(); q++) {
                    if (Integer.parseInt(messageID) == q) {
                        Server.Accounts.get(j).Messagebox.get(q).setIsread(true);
                        a = a + "(" + Server.Accounts.get(j).Messagebox.get(q).getSender() + ")" + Server.Accounts.get(j).Messagebox.get(q).getBody() + '\n';
                        return a;
                    }
                }
                return "Message ID does not exist";
            }
        }
        return "Invalid Auth Token\n";
    }

    public String DeleteMessage(String authtoken, String messageID) throws RemoteException {
        for (int j = 0; j < Server.Accounts.size(); j++) {
            if ((Server.Accounts.get(j).getAuthtoken() == Integer.parseInt(authtoken))) {
                for (int q = 0; q < Server.Accounts.get(j).Messagebox.size(); q++) {
                    if (Integer.parseInt(messageID) == q) {
                        Server.Accounts.get(j).Messagebox.remove(q);
                        return "OK";
                    }
                }
                return "Message ID does not exist";
            }
        }
        return "Invalid Auth Token\n";
    }
}
//9749976 7544252