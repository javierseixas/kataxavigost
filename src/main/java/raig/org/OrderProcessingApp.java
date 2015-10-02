package raig.org;

public class OrderProcessingApp {
  public PackingSlip process(Payment payment) {

    if (payment.getType().equals("physical")) {
      return new PackingSlip();
    }
    if (payment.getType().equals("book")) {
      return new DuplicatePackingSlip();
    }

    return null;
  }
}
