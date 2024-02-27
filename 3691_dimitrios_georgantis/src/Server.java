import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Server {
    static List<Account> Accounts= new ArrayList<Account>();
    public static void main(String[] args) {
        try {
            RemoteAccountholder stub = new RemoteAccountholder();
            // create the RMI registry on port 5000
            Registry rmiRegistry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
            // path to access is rmi://localhost:5000/calculator
            rmiRegistry.rebind("localhost", stub);
            System.out.println("Server is ready");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}