package Swing_Network_Forms;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Date;

// Student class is to create a template object which will be useful for handling data in forms and database
public class Student implements Serializable {

        // Data members
        private String RegNo;
        private String Name;
        private String Dept;
        private int YrOfStudy;
        private Email email;
        private Date dob;
        private String password;
        private char operation;

        // Constructor
        public Student(String r,String n,String d,int y,String e,Date date) {
                this.RegNo = r;
                this.Name = n;
                this.Dept = d;
                this.YrOfStudy = y;
                this.dob = date;
                this.operation = 'N';
                this.password = null;
                try {
                        this.email = new Email(e);
                }
                catch (Exception ex) {
                        System.out.println("Creating Student : "+ ex);
                }
        }

        public Student(String r,String pass) {
                this.RegNo = r;
                this.password = pass;
                this.Name = null;
                this.Dept = null;
                this.email = null;
                this.dob = null;
                this.YrOfStudy = 0;
                this.operation = 'L';
        }

        public Student(String r,String n,String d,int y,String e,Date date,char o,String pass) {
                this.RegNo = r;
                this.Name = n;
                this.Dept = d;
                this.YrOfStudy = y;
                this.dob = date;
                this.operation = o;
                this.password = pass;
                try{
                        this.email = new Email(e);
                }
                catch (Exception ex) {
                        System.out.println("Creating Student : "+ex);
                }
        }

        public Student(ResultSet set) {
                try {
                        this.RegNo = set.getString("regno");
                        this.Name = set.getString("Studentname");
                        this.Dept = set.getString("department");
                        this.YrOfStudy = set.getInt("YearOfStudy");
                        this.email = new Email(set.getString("Email"));
                        this.dob = set.getDate("Dob");

                }
                catch(Exception e) {
                        System.out.println("Student Constructor : "+e);

                }

        }

        // Getter Functions
        public String getRegNo() {
                return this.RegNo;
        }

        public String getName() {
                return this.Name;
        }

        public String getDept() {
                return this.Dept;
        }

        public int getYrOfStudy() {
                return this.YrOfStudy;
        }

        public String getEmail() {
                return this.email.getEmail();
        }

        public String getDOB() {
                return this.dob.toString();
        }

        public char getOperation() {
                return this.operation;
        }

        public String getPassword() {
                return this.password;
        }

        // Setter Functions
        public void setRegNo(String regNo) {
                this.RegNo = regNo;
        }

        public void setName(String name) {
                this.Name = name;
        }

        public void setDept(String dept) {
                this.Dept = dept;
        }

        public void setYrOfStudy(int yrOfStudy) {
                this.YrOfStudy = yrOfStudy;
        }

        public void setEmail(Email email) {
                this.email = email;
        }

        public void setOperation(char operation) {
                this.operation = operation;
        }

        public void setDOB(String s) {
                try {
                        this.dob = Date.valueOf(s);
                }
                catch(Exception e) {
                        System.out.println("setDOB : "+e);
                }
        }

        // utility Functions

        public String getInsertQuery() {
                return String.format("insert into Student(RegNo,StudentName,Department,YearOfStudy,Email,Dob) " +
                        "values( '%s','%s','%s','%s','%s','%s');",this.RegNo,this.Name,this.Dept, this.YrOfStudy,
                        this.getEmail(),this.dob);
        }

        public String getUpdateQuery() {
                return String.format("update Student set StudentName = '%s',Department = '%s' ,YearOfStudy = '%s',Email = '%s'"+
                        ",DOb = '%s' where RegNo = '%s' ;",this.Name,this.Dept, this.YrOfStudy,this.getEmail(),this.RegNo,this.dob);
        }

        public String getDeleteQuery() {
                return String.format("delete from Student where RegNo = '%s';",this.RegNo);
        }
}
