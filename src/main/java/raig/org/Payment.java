package raig.org;

public class Payment {

  protected String type;

  Payment(String type){
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
