/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author jenishshrestha
 */
import java.sql.*;
import java.util.ArrayList;
public class DatabaseHelper {
    String user = "root";
    String password = "root@123";
    String url = "jdbc:mysql://localhost:3306/mydatabase";
    String query = "";
    Connection conn = null;

    DatabaseHelper(){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            try{
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connection succeeded");
            }catch (SQLException sqle){
                System.err.println("Connection Failed: " + sqle.getMessage());
            }
        }catch(Exception e){
            System.err.println("Unexpected exception " + e.getMessage() );
        }
    }
    
    
    public boolean checkForUserCredentials(String username, String password){
        Statement stmt;
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_cred where username="+ "\""+username+"\"");
            String usernameDB = "";
            String passwordDB = "";
            while(rs.next()){
                usernameDB = rs.getString(1);
                passwordDB = rs.getString(2);
            }
            if(usernameDB.compareTo(username)==0 && passwordDB.compareTo(password)==0){
                return true;
            }
        }catch(SQLException e){
            System.out.print("Error Occured: "+ e);
        }
        return false;
    }
    public int getNumOfCar(){
        Statement stmt;
        int NumOfCar = 0;
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM cardetails");
            while(rs.next()){
                NumOfCar = rs.getInt(1);
            }
            
        }catch(SQLException e){
            System.out.print("Error Occured: "+ e);
        }
        return NumOfCar;
    }
    public ArrayList<ArrayList<String>> getCarDetails(){
        Statement stmt = null;
        ArrayList<ArrayList<String>> carDetails = new ArrayList<ArrayList<String>>();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cardetails");
            while(rs.next()){
                ArrayList<String> carDetail = new ArrayList<>();
                carDetail.add(rs.getString(1));
                carDetail.add(rs.getString(2));
                carDetail.add(rs.getString(3));
                carDetails.add(carDetail);
            }
            System.out.print(carDetails);
            
        }catch(SQLException e){
            System.out.print("Error Occured: "+ e);
        }
        return carDetails;
    }
    public boolean insertBookingRecord(String[] data){
       Statement stmt=null;
       try{
            stmt = conn.createStatement();
            PreparedStatement prep = conn.prepareStatement("INSERT INTO booking VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            prep.setString(1, data[0]);
            prep.setString(2, data[1]);
            prep.setString(3, data[2]);
            prep.setString(4, data[3]);
            prep.setString(5, data[4] );
            prep.setString(6, data[5]);
            prep.setString(7, data[6]);
            prep.setString(8, data[7]);
            prep.setString(9, data[8]);
           int rs = prep.executeUpdate();
            return rs>0;
        }catch(SQLException e){
            System.out.print("Error Occured: "+ e);
            return false;
        }    
    } 
  //  public boolean showData(String[] data){
    //    Statement stmt=null;
      // try{
        //    stmt = conn.createStatement();
          //  PreparedStatement prep = conn.prepareStatement("select * FROM booking");
            
       //}catch(SQLException e){
           
    //}
}
  