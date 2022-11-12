package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChanceTest {
    @Test
    @DisplayName("Status가 NONE이면 숫자를")
    void test1() {
        Chance chance = new Chance(Pin.of(1), Status.NONE);
        assertThat(chance.chance()).isEqualTo("1");
    }

    @Test
    @DisplayName("Status가 NONE이 아니라면 Status를")
    void test2() {
        Chance chance = new Chance(Pin.of(1), Status.STRIKE);
        assertThat(chance.chance()).isEqualTo("X");
    }

}
