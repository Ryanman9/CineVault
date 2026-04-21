package controller;

import dao.UserDAO;

public class AuthController 
{   
    UserDAO userdao = new UserDAO();
    public void auth(String username, String password)
    {
     if(password.equals(userdao.getPassword(username)))
     {
         System.out.println("Login successful");
     }
     else
     {
         System.out.println("Login failed");
     }
    }

    public void register(String username, String password)
    {
        if(userdao.Registration(username, password))
        {
            System.out.println("Registration successful");
        }
        else
        {
            System.out.println("Registration failed");
        }
    }
}
