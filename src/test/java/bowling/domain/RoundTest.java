package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RoundTest {

    @Test
    void create() {
        Round actual = new Round(10);

        assertThat(actual).isEqualTo(new Round(10));
    }

    @DisplayName("이전 라운드를 생성해 반환한다.")
    @Test
    void before_round() {
        Round round = new Round(10);

        assertThat(round.beforeRound()).isEqualTo(new Round(9));
    }

    @Test
    void validate_range() {
        Assertions.assertAll(
                () -> assertThatThrownBy(() -> new Round(0)).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Round(12)).isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void value() {
        Round round = new Round(5);

        assertThat(round.value()).isEqualTo(5);
    }

    @Test
    void index() {
        Round round = new Round(4);

        assertThat(round.index()).isEqualTo(3);
    }
}
