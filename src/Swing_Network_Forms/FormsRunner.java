package Swing_Network_Forms;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;


import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.Properties;
import java.util.Scanner;

// This class runs the student database
public class FormsRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the url: ");
        String url = sc.nextLine();
        System.out.print("Enter the port number: ");
        int port = sc.nextInt();
        Client client = new Client(url,port);
        new Home(client);
        //new sample();
    }
}

