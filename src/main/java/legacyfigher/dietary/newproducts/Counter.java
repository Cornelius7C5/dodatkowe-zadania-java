package legacyfigher.dietary.newproducts;

import java.util.Objects;

public class Counter {
  private Integer value;

  private Counter(Integer value) {
    this.value = value;
  }

  public static Counter of(Integer value) {
    return new Counter(value);
  }

  public void decrement() {
    validate();
    value--;
  }

  public void validate() {
    if (!isNotNull()) {
      throw new IllegalStateException("null counter");
    }
    if (!isPositive()) {
      throw new IllegalStateException("Negative counter");
    }
  }

  private boolean isPositive() {
    return value >= 0;
  }

  private boolean isNotNull() {
    return value != null;
  }

  public void increment() {
    validate();
    value++;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Counter counter = (Counter) o;
    return Objects.equals(value, counter.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
