package raig.org;



import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.instanceOf;

public class OrderProcessingAppTest {


  @Test
  public void business_rule_1() {

  //  If_the_payment_is_for_a physical product_ // generate a packing slip for shipping.
    Payment pysical = new Payment("physical");

    receiveAPayment(pysical).generatesPackingSlip().forShipping();

  }

  private Operation receiveAPayment(Payment payment) {
    return new Operation(payment);
  }


  @Test
  public void business_rule_2() {
  //If the payment is for a book, create a duplicate packing slip for the royalty department.

    OrderProcessingApp operations = new OrderProcessingApp();
    Payment book = new Payment("book");
    receiveAPayment(book).generatesDuplicatePackingSlip().forRoyaltyDepartment();
  }

  private class Operation {

    private Payment payment;
    private PackingSlip packingSlip;

    public Operation(Payment payment) {
      this.payment = payment;
      OrderProcessingApp processor = new OrderProcessingApp();
      packingSlip = processor.process(payment);
    }

    public Operation generatesPackingSlip() {
      assertThat(packingSlip, instanceOf(PackingSlip.class));
      return this;
    }

    public Operation generatesDuplicatePackingSlip() {
      assertThat(packingSlip, instanceOf(DuplicatePackingSlip.class));
      return this;
    }

    public Operation forShipping() {
      assertThat(packingSlip.destiny(), instanceOf(Shipping.class));
      return this;
    }

    public Operation forRoyaltyDepartment() {
      assertThat(packingSlip.destiny(), instanceOf(RoyaltyDepartment.class));
      return this;
    }
  }

}
