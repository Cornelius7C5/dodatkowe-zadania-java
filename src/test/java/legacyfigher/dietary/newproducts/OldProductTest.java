package legacyfigher.dietary.newproducts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OldProductTest {

  private static final String SHORT_DESC = "Pear Juice";
  private static final String LONG_DESC =
    " Corporis enim reprehenderit. Qui iusto officiis minus nemo cumque esse consectetur. Facilis quia earum distinctio qui illo laboriosam deleniti. Ab quaerat veritatis optio esse.";

  private static void assertEqualOldProducts(OldProduct oldProduct, OldProduct other) {
    assertThat(oldProduct).isEqualToComparingOnlyGivenFields(other, "price", "description", "counter");
  }


  @Nested
  class DecrementCounter {
    @Test
    void decrementCounter() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 1);
      uut.decrementCounter();
      assertEqualOldProducts(uut, (new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 0)));
    }

    @Test
    void shouldThrowIllegalStateOnNegativePrice() {
      OldProduct uut = new OldProduct(BigDecimal.valueOf(-10L), SHORT_DESC, LONG_DESC, 1);
      assertThatIllegalStateException().isThrownBy(uut::decrementCounter).withMessage("Invalid price");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.valueOf(-10L), SHORT_DESC, LONG_DESC, 1));
    }

    @Test
    void shouldThrowIllegalStateOnNullPrice() {
      OldProduct uut = new OldProduct(null, SHORT_DESC, LONG_DESC, 1);
      assertThatIllegalStateException().isThrownBy(uut::decrementCounter).withMessage("Invalid price");
      assertEqualOldProducts(uut, new OldProduct(null, SHORT_DESC, LONG_DESC, 1));
    }

    @Test
    void shouldThrowIllegalStateOnNegativeCounter() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, -1);
      assertThatIllegalStateException().isThrownBy(uut::decrementCounter).withMessage("Negative counter");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, -1));
    }

    @Test
    void shouldThrowIllegalStateOnNullCounter() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, null);
      assertThatIllegalStateException().isThrownBy(uut::decrementCounter).withMessage("null counter");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, null));
    }
  }


  @Nested
  class IncrementCounter {
    @Test
    void incrementCounter() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 1);
      uut.incrementCounter();
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 2));
    }

    @Test
    void shouldThrowIllegalStateOnNegativePrice() {
      OldProduct uut = new OldProduct(BigDecimal.valueOf(-10L), SHORT_DESC, LONG_DESC, 1);
      assertThatIllegalStateException().isThrownBy(uut::incrementCounter).withMessage("Invalid price");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.valueOf(-10L), SHORT_DESC, LONG_DESC, 1));
    }

    @Test
    void shouldThrowIllegalStateOnNullPrice() {
      OldProduct uut = new OldProduct(null, SHORT_DESC, LONG_DESC, 1);
      assertThatIllegalStateException().isThrownBy(uut::incrementCounter).withMessage("Invalid price");
      assertEqualOldProducts(uut, new OldProduct(null, SHORT_DESC, LONG_DESC, 1));
    }

    @Test
    void shouldThrowIllegalStateOnNegativeCounter() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, -10);
      assertThatIllegalStateException().isThrownBy(uut::incrementCounter).withMessage("Negative counter");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, -10));
    }

    @Test
    void shouldThrowIllegalStateOnNullCounter() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, null);
      assertThatIllegalStateException().isThrownBy(uut::incrementCounter).withMessage("null counter");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, null));
    }
  }


  @Nested
  class ChangePriceTo {
    @Test
    void changePriceTo() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 1);
      uut.changePriceTo(BigDecimal.TEN);
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.TEN, SHORT_DESC, LONG_DESC, 1));
    }

    @Test
    void shouldThrowExceptionOnNullNewPrice() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 1);
      assertThatIllegalStateException().isThrownBy(() -> uut.changePriceTo(null))
                                       .withMessage("new price null");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 1));

    }

    @Test
    void shouldThrowIllegalStateOnNegativeCounter() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, -10);
      assertThatIllegalStateException().isThrownBy(() -> uut.changePriceTo(BigDecimal.TEN))
                                       .withMessage("Negative counter");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, -10));
    }

    @Test
    void shouldThrowIllegalStateOnNullCounter() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, null);
      assertThatIllegalStateException().isThrownBy(() -> uut.changePriceTo(BigDecimal.TEN)).withMessage("null counter");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, null));
    }
  }

  @Nested
  class ReplaceCharFromDesc {
    @Test
    void replaceCharFromDesc() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 1);
      uut.replaceCharFromDesc("Pear", "Apple");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, "Apple Juice", LONG_DESC, 1));
    }

    @Test
    void replaceCharFromAllDescWithCaseSensitive() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, "Test", "Longtest", 1);
      uut.replaceCharFromDesc("Test", "Apple");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, "Apple", "Longtest", 1));
    }

    @Test
    void shouldThrowIllegalStateOnEmptyDesc() {
      OldProduct uut = new OldProduct(BigDecimal.ONE, "", "", null);
      assertThatIllegalStateException().isThrownBy(() -> uut.replaceCharFromDesc("Pear", "Apple")).withMessage("null or empty desc");
      assertEqualOldProducts(uut, new OldProduct(BigDecimal.ONE, "", "", null));
    }
  }


  @Nested
  class FormatDesc {
    @Test
    void formatDesc() {
      OldProduct oldProduct = new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 1);
      String uut = oldProduct.formatDesc();
      assertEqualOldProducts(oldProduct, new OldProduct(BigDecimal.ONE, SHORT_DESC, LONG_DESC, 1));
      assertThat(uut).isEqualTo(SHORT_DESC + " *** " + LONG_DESC);
    }

    @Test
    void formatDescWhenShortEmpty() {
      OldProduct oldProduct = new OldProduct(BigDecimal.ONE, "", LONG_DESC, 1);
      String uut = oldProduct.formatDesc();
      assertEqualOldProducts(oldProduct, new OldProduct(BigDecimal.ONE, "", LONG_DESC, 1));
      assertThat(uut).isEqualTo("");
    }

    @Test
    void formatDescWhenLongEmpty() {
      OldProduct oldProduct = new OldProduct(BigDecimal.ONE, SHORT_DESC, "", 1);
      String uut = oldProduct.formatDesc();
      assertEqualOldProducts(oldProduct, new OldProduct(BigDecimal.ONE, SHORT_DESC, "", 1));
      assertThat(uut).isEqualTo("");
    }
  }
}