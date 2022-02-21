package example;


import javax.faces.bean.ManagedBean;

@ManagedBean(name = "testbean", eager = true)
public class Example {
  private String testString = "";

  public Example() {
    this.testString = "Testing test TEST";
  }

  public String getString() {
    return this.testString;
  }
}
