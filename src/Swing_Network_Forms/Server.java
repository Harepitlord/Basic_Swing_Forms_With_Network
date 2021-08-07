package Swing_Network_Forms;

import org.jetbrains.annotations.NotNull;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  extends Thread{

    Socket socket;
    ObjectInputStream oin;
    DataOutputStream dos;
    Database dbase;
    ObjectOutputStream oon;

    public Server(Socket socket,Database database) {
        this.socket = socket;
        this.prepareServer();
        this.dbase = database;
    }

    private void prepareServer() {
        try {
            this.oin = new ObjectInputStream(this.socket.getInputStream());
            this.dos = new DataOutputStream(this.socket.getOutputStream());
            this.oon = new ObjectOutputStream(this.socket.getOutputStream());
        }
        catch (Exception e){
            System.out.println("Preparing Server: "+e);
        }
    }

    public void run() {
            try {
                Student student = (Student) this.oin.readObject();
                this.doOperation(student);
            }
            catch(IOException | ClassNotFoundException e) {
                System.out.println("Server is Closing ....."+e);
            }
            finally {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    private void doOperation(Student student) throws IOException {
        if(student == null) {
            this.dos.writeBytes("No Data sent.");
            this.dos.flush();
            return;
        }

        switch (student.getOperation()) {
            case 'I': {
                if(this.dbase.insertStudent(student)) {
                    this.dbase.checkLogin(student.getRegNo(),student.getPassword());
                    this.dos.writeBytes("Student registration Successful.");
                }
                else
                    this.dos.writeBytes("Student registration Failed.");
                break;
            }
            case 'U': {
                if(this.dbase.updateStudent(student))
                    this.dos.writeBytes("Student Updation Successful.");
                else
                    this.dos.writeBytes("Student Updation Failed.");
                break;
            }
            case 'D': {
                if(this.dbase.deleteStudent(student))
                    this.dos.writeBytes("Student Deletion Successful.");
                else
                    this.dos.writeBytes("Student Deletion Failed.");
                break;
            }
            case 'L': {
                Student logged = this.dbase.checkLogin(student.getRegNo(),student.getPassword());
                if(logged == null)
                    this.dos.writeBytes("Check Login Credentials.");
                else
                    this.oon.writeObject(logged);
                break;
            }

            case 'N': {
                break;
            }
            default:
                this.dos.writeBytes("Error in processing details.");
        }
        this.dos.flush();
        this.oon.flush();
    }
}
