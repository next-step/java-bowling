package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChanceTest {

    @Test
    void create() {
        Chance actual = new Chance(2);

        assertThat(actual).isEqualTo(new Chance(2));
    }

    @DisplayName("Chance 는 음수일 수 없습니다.")
    @Test
    void valid_chance() {
        assertThatThrownBy(() -> new Chance(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void minus_one() {
        Chance chance = new Chance(2);

        assertThat(chance.minusOne()).isEqualTo(new Chance(1));
    }

    @Test
    void minus_one_negative() {
        Chance chance = new Chance(0);

        assertThatThrownBy(chance::minusOne).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void minus_two() {
        Chance chance = new Chance(2);

        assertThat(chance.minusTwo()).isEqualTo(new Chance(0));
    }

    @Test
    void minus_two_negative() {
        Chance chance = new Chance(1);

        assertThatThrownBy(chance::minusTwo).isInstanceOf(IllegalArgumentException.class);
    }
}
