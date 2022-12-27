package legacyfigher.dietary.newproducts;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {
  BigDecimal priceValue;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Price price = (Price) o;
    return Objects.equals(priceValue, price.priceValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priceValue);
  }

  public static Price of(BigDecimal price) {
    return new Price(price);
  }

  private Price(BigDecimal price) {
    this.priceValue = price;
  }

  public boolean isValid() {
    return priceValue != null && priceValue.signum() > 0;
  }

  public void update(BigDecimal newPrice) {
    if (newPrice == null) {
      throw new IllegalStateException("new price null");
    }
            priceValue = newPrice;
  }
}
