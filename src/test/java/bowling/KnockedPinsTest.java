package bowling;

import bowling.domain.KnockedPins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnockedPinsTest {

    @Test
    void convertStrike() {
        KnockedPins knockedPins = new KnockedPins(10);
        assertThat(knockedPins.convert()).isEqualTo("X");
    }

    @Test
    void convertZero() {
        KnockedPins knockedPins = new KnockedPins(0);
        assertThat(knockedPins.convert()).isEqualTo("-");
    }

    @Test
    void convert() {
        KnockedPins knockedPins = new KnockedPins(5);
        assertThat(knockedPins.convert()).isEqualTo("5");
    }

    @Test
    void add() {
        KnockedPins knockedPinsA = new KnockedPins(5);
        KnockedPins knockedPinsB = new KnockedPins(3);
        assertThat(knockedPinsA.add(knockedPinsB)).isEqualTo(new KnockedPins(8));
    }

    @Test
    void validationCheck() {
        assertThatThrownBy(() -> {
            KnockedPins knockedPins = new KnockedPins(11);
        }).hasMessageContaining("score must be between 0 and 10").isInstanceOf(IllegalArgumentException.class);
    }
}
