package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserDAO 
{
    private final String URL = "";
    
    public String getPassword(String username)
    {
        try(Connection conn =DriverManager.getConnection(URL))
        {
            if(conn!=null)
            {
                String query="Select password from users where username=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, username);
                ResultSet rs=stmt.executeQuery();
                if(rs.next())
                {
                    return rs.getString("password");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
        return null;
    }

    public boolean Registration(String username, String password)
    {
        try(Connection conn=DriverManager.getConnection(URL))
        {
            if(conn!=null)
            {
                String query="Insert into users(username,password) values(?,?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                int rowsAffected=stmt.executeUpdate();
                if(rowsAffected>0)
                {
                    return true;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
        return false;
    }
}
