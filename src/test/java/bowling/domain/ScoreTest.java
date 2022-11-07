package bowling.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    void create() {
        assertThat(Score.of(1) == Score.of(1)).isTrue();
    }

    @DisplayName("0 ~ 10 이외의 숫자를 입력하면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void validate_range() {
        Assertions.assertAll(
                () -> assertThatThrownBy(() -> Score.of(-1)).isInstanceOf(IllegalArgumentException.class),
                () -> assertThat(Score.of(0)).isEqualTo(Score.of(0)),
                () -> assertThat(Score.of(10)).isEqualTo(Score.of(10)),
                () -> assertThatThrownBy(() -> Score.of(11)).isInstanceOf(IllegalArgumentException.class)
        );
    }

    @DisplayName("10점이면 true를 반환한다.")
    @Test
    void is_strike() {
        Assertions.assertAll(
                () -> assertThat(Score.of(9).isStrike()).isFalse(),
                () -> assertThat(Score.of(10).isStrike()).isTrue()
        );
    }
}
