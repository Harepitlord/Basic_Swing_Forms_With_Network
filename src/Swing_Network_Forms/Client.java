package Swing_Network_Forms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    // Data Members
    private Socket socket;
    private ObjectOutputStream oon;
    private ObjectInputStream oin;
    private DataOutputStream dos;
    private DataInputStream din;

    public Client(String url,int port) {
        try {
            this.socket = new Socket(url, port);
            this.oon = new ObjectOutputStream(this.socket.getOutputStream());
            this.oin = new ObjectInputStream(this.socket.getInputStream());
            this.dos = new DataOutputStream(this.socket.getOutputStream());
            this.din = new DataInputStream(this.socket.getInputStream());
        }
        catch(Exception e) {
            System.out.println("Client socket: " + e);
        }
    }

    public boolean sendStudent(Student student) {
        try {
            this.oon.writeObject(student);
            this.oon.flush();
            return true;
        }
        catch (Exception ex) {
            System.out.println("Student transmit: "+ex);
            return false;
        }
    }

    public Student receiveStudent() {
        try {
            return (Student)this.oin.readObject();
        }
        catch (Exception ex) {
            System.out.println("Student receive : "+ex);
        }
        return null;
    }

    public boolean sendData(String s) {
        try {
            this.dos.writeBytes(s);
            this.dos.flush();
            return true;
        }
        catch (Exception ex) {
            System.out.println("Data send : "+ex);
            return false;
        }
    }

    public String receiveData() {
        try {
            return this.din.readUTF();
        }
        catch (Exception ex) {
            System.out.println("Data receive : "+ex);
        }
        return null;
    }
}
