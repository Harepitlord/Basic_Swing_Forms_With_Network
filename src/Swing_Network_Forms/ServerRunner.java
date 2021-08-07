package Swing_Network_Forms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Scanner;

public class ServerRunner {

    // Data Members
    private JFrame frame;
    private JPanel spanel,panel;
    private JLabel port,response;
    private JTextField portInput;
    private JButton submit,close;
    private ServerHandler handle;


    public ServerRunner() {
        this.prepareFrames();
        this.preparePanels();
        this.prepareLabels();
        this.prepareTextFields();
        this.prepareButtons();
        this.prepareActionListeners();
        this.addElements();
    }

    private void addElements() {
        this.frame.add(spanel);

        this.spanel.add(panel);

        this.panel.add(this.port);
        this.panel.add(this.portInput);
        this.panel.add(this.submit);
        this.panel.add(this.response);
        this.panel.add(this.close);
    }

    private void prepareActionListeners() {
        this.submit.addActionListener(e->{
            if(this.handle != null)
                return;
            String s = this.portInput.getText();
            if (s == null)
                this.response.setText("Enter Port number:");
            else {
                System.out.println("Server is Running.....");
                this.handle =new ServerHandler(Integer.parseInt(s));
                this.response.setText("Server is Running.......... ");
            }
        });

        this.close.addActionListener(e->{
            if(this.handle != null)
                this.handle.stopServer();
        });
    }

    private void prepareButtons() {
        this.submit = new JButton("submit");
        this.submit.setBackground(Color.white);
        this.submit.setForeground(Color.blue);

        this.close = new JButton("Stop");
        this.close.setForeground(Color.blue);
        this.close.setBackground(Color.white);
    }
    private void prepareTextFields() {
        this.portInput = new JTextField();
        //this.portInput.setBounds(50,100,20,20);
        this.portInput.setSize(20,20);
        this.portInput.setBackground(Color.white);
    }

    private void prepareLabels() {
        this.port = new JLabel("Port: ");
        this.port.setForeground(Color.white);
        this.port.setBounds(50,50,20,20);

        this.response = new JLabel();
        this.response.setBounds(50,150,30,20);
        this.response.setForeground(Color.white);
    }

    private void preparePanels() {
        this.spanel = new JPanel();
        this.spanel.setLayout(null);
        this.spanel.setBounds(50,50,500,500);
        this.spanel.setBackground(Color.white);
        this.spanel.setBorder(new EmptyBorder(new Insets(50,50,50,50)));

        this.panel = new JPanel();
        this.panel.setBackground(Color.blue);
        this.panel.setBounds(50,50,300,300);
        this.panel.setLayout(new FlowLayout());
        this.panel.setBorder(new LineBorder(Color.BLACK));

    }

    private void prepareFrames() {
        this.frame = new JFrame("Server");
        this.frame.setLayout(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.setBackground(Color.white);
        this.frame.setSize(600,600);

    }

    public static void main(String[] args) {
         //new ServerRunner();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Port : ");
        int port = sc.nextInt();
        new ServerHandler(port);
        sc.close();
    }

}
