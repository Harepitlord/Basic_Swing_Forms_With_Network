package Swing_Network_Forms;

import java.io.ObjectInputStream;
import java.net.ServerSocket;

public class ServerHandler {

    private final int port;
    private ServerSocket ss;
    private Database dbase;

    public ServerHandler(int p) {
        this.port = p;
        this.prepareServer();
        this.runServer();
    }

    private void prepareServer() {
        try {
            this.ss = new ServerSocket(this.port);
            this.dbase = new Database();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void runServer() {
        System.out.println("Server is running ....."+this.port);
        while(true) {
            try{
                new Server(this.ss.accept(),this.dbase).start();
            }
            catch (Exception e) {
                System.out.println("Server Handler: "+e);
            }
        }
    }

    public void stopServer() {
        try {
            this.ss.close();
        }
        catch (Exception ex) {
            System.out.println("Stop Server : "+ex);
        }
    }
}
