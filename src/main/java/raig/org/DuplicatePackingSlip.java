package raig.org;

public class DuplicatePackingSlip extends PackingSlip {

  @Override
  public Destiny destiny() {
    return new RoyaltyDepartment();
  }
}
