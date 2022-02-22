package net.jar.login.Model;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import net.jar.login.Controller.SessionUtils;
import net.jar.login.Controller.DAOlogin;

@ManagedBean(name = "beanUser", eager = true)
@SessionScoped
public class User {

  private String username = "";
  private String password = "";
  private String password2 = "";
  private String email = "";

  public User() {}

  public void setUsername(String user) { this.username = user; }
  public void setPassword(String pass) { this.password = pass; }
  public void setPassword2(String pass) { this.password2 = pass; }
  public void setEmail(String em) { this.email = em; }

  public String getUsername() { return this.username; }
  public String getPassword() { return this.password; }
  public String getPassword2() { return this.password2; }
  public String getEmail() { return this.email; }

  public String validateUsernamePassword() {
    boolean valid = DAOlogin.validateUser(this.username,this.password);
    if (valid) {
      HttpSession sess = SessionUtils.getSession();
      sess.setAttribute("username",this.username);
      return "main";
    } else {
      FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Incorrect username or password","..."));
      return "login";
    }
  }

  public String logout() {
    HttpSession sess = SessionUtils.getSession();
    sess.invalidate();
    return "index";
  }

}
