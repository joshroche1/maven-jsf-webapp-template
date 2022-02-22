package net.jar.login.Controller;


public class DAOlogin {

  public static boolean validateUser(String user, String pass) {
    String username = user.trim();
    String password = pass.trim();
    if (username.equals("test")) {
      if (password.equals("test")) { return true; }
    } // else if; else clauses for more passwords
    return false;
  }

}
