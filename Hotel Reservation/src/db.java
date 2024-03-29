import java.sql.*;
import java.util.Vector;
public class db {
  Connection conn;
  Statement stmt;
  db(){
      try{
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation","root","");
      }
      catch(Exception ex){
          ex.printStackTrace();
      }
  }
 
  public boolean pass(String u,String p)
  {
	  boolean login=false;
	  try {
          String query="SELECT username,password from admin_info";
		  stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while(rs.next()){

               String name = rs.getString("username");
               String pass = rs.getString("password");
               if(name.equals(u) && pass.equals(p))
               {
            	   login=true;
               } 
          }
		  
	  } 
	  catch (Exception ex)
	  {
		  ex.printStackTrace();
		  }
	  return login;
	  
  }
  
  
  public Vector<Vector<String>> getuserinformation()
	{
		try
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM user_info";
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Vector<String>> userinfoList = new Vector<Vector<String>>();
			while(rs.next())
			{
				Vector<String> user_info = new Vector<String>();
				user_info.add(rs.getString("room_no"));
				user_info.add(rs.getString("name"));
				user_info.add(rs.getString("phone"));
				user_info.add(rs.getString("address"));
				user_info.add(rs.getString("date_from"));
				user_info.add(rs.getString("date_to"));
				userinfoList.add(user_info);
			}
			return userinfoList;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
  public void insertToDB(int room_no,String name, String phone, String address, String date_from, String date_to){
      
      try{
    	  stmt = conn.createStatement();
    	  String sql = "INSERT INTO user_info (room_no, name, phone,address, date_from, date_to)" +
    		        "VALUES ('"+room_no+"','"+name+"','"+phone+"','"+address+"','"+date_from+"','"+date_to+"')";
    	  stmt.execute(sql);
          
      }
      catch(Exception ex){
          ex.printStackTrace();
      }
     
  }
  public void updateDB(int room_no,String name, String phone, String address, String date_from, String date_to){
	 
      
      try{
    	  stmt = conn.createStatement();
    	  String sql = "UPDATE user_info SET  name ='" + name +  "', phone ='" + phone +  "',"
    	  				+ " address = '" + address +  "'," + " date_from = '" + date_from +  "',"
    	  				+ " date_to =  '" + date_to +  "' where room_no ='"+room_no+"'";
    	  stmt.execute(sql);

      }
      catch(Exception ex){
          ex.printStackTrace();
      }
  }
  
}

