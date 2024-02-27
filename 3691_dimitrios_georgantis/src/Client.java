import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            //το δεύτερο argument είναι πάντα το ποιά λειτουργία θα εκτελεστεί
            String leitourgia=args[2];
            // connect to the RMI registry
            Registry rmiRegistry = LocateRegistry.getRegistry(Integer.parseInt(args[1]));
            // get reference for remote object
            Accountholder stub = (Accountholder) rmiRegistry.lookup(args[0]);
            //ανάλογα με το τι θέλουμε να κάνουμε καλείται η κατάλληλη λειτουργία
            if(leitourgia.equals("1"))
            {
                System.out.println(stub.CreateAccount(args[3]));
            }
            if(leitourgia.equals("2"))
            {
                System.out.println(stub.ShowAccounts(args[3]));
            }
            if(leitourgia.equals("3"))
            {
                //εδώ ενώνουμε όλα τα arguments λόγω της ύπαρξης κενών για τα μηνύματα
                String a="";
                for(int i=5;i<args.length;i++)
                {
                    if(i!=5) {
                        a += " " + args[i];
                    }
                    else
                    {
                        a+=args[i];
                    }
                }
                System.out.println(stub.SendMessage(args[3],args[4],a));
            }
            if(leitourgia.equals("4"))
            {
                System.out.print(stub.ShowInbox(args[3]));
            }
            if(leitourgia.equals("5"))
            {
                System.out.print(stub.ReadMessage(args[3],args[4]));
            }
            if(leitourgia.equals("6"))
            {
                System.out.print(stub.DeleteMessage(args[3],args[4]));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}