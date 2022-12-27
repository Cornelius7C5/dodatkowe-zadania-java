package legacyfigher.dietary.newproducts;

import java.math.BigDecimal;
import java.util.UUID;

public class OldProduct {

  UUID serialNumber = UUID.randomUUID();

  Price price;
  Description description;
  Counter counter;

  public OldProduct(BigDecimal price, String desc, String longDesc, Integer counter) {
    this.price = Price.of(price);
    this.description = Description.of(desc, longDesc);
    this.counter = Counter.of(counter);
  }

  void decrementCounter() {
    if (this.price.isValid()) {
      counter.decrement();
    } else {
      throw new IllegalStateException("Invalid price");

    }

  }

  void incrementCounter() {
    if (price.isValid()) {
      counter.increment();
    } else {
      throw new IllegalStateException("Invalid price");

    }
  }

  void changePriceTo(BigDecimal newPrice) {
    this.counter.validate();

    this.price.update(newPrice);
  }

  void replaceCharFromDesc(String charToReplace, String replaceWith) {
        description.replace(charToReplace, replaceWith);
  }

  String formatDesc() {
    return description.getFormatted();
  }


}
