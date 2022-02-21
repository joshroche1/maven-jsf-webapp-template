package net.jar.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import net.jar.Data.DAOlogin;
import net.jar.Controller.SessionUtils;

@ManagedBean(name = "beanUser", eager = true)
@SessionScoped
public class User {

  private String email = "";
  private String username = "";
  private String password = "";
  private String password2 = "";

  public User() {}

  public void setEmail(String em) { this.email = em;}
  public void setUsername(String user) { this.username = user;}
  public void setPassword(String pass) { this.password = pass;}

  public String getEmail() { return this.email; }
  public String getUsername() { return this.username; }
  public String getPassword() { return this.password; }
  
  public boolean validatePassword() {
    if (password.equals(password2)) { return true; }
    return false;
  }

  public String getCurrentUser() {
    String user = "";
    HttpSession ses = SessionUtils.getSession();
    String temp = ses.getAttribute("username").toString();
    user += "" + temp;
    return user;
  }

  public String validateUsernamePassword() {
    boolean valid = DAOlogin.validateUser(username, password);
    if (valid) {
      HttpSession sess = SessionUtils.getSession();
      sess.setAttribute("username", username);
      return "main";
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                   FacesMessage.SEVERITY_WARN, "Incorrect username and/or password", "..."));
      return "login";
    }
  }
  
  public String logout() {
    HttpSession session = SessionUtils.getSession();
    session.invalidate();
    return "index";
  }
}
