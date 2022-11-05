package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @DisplayName("SPARE")
    @ParameterizedTest(name = "첫번째 넘어진 개수는 {0}이고, 두번째 넘어진 개수는 {1}이다.")
    @CsvSource(value = {"0,10", "5,5", "2,8"})
    void pin_spare(final int one, final int two) {

        final Score score = new Score();
        score.add(new Pin(one));
        score.add(new Pin(two));

        assertThat(score.status()).isEqualTo(ScoreType.SPARE);
    }

    @DisplayName("STRIKE")
    @Test
    void pin_strike() {

        final Score score = new Score();
        score.add(new Pin(10));

        assertThat(score.status()).isEqualTo(ScoreType.STRIKE);
    }

    @DisplayName("MISS")
    @ParameterizedTest(name = "첫번째 넘어진 개수는 {0}이고, 두번째 넘어진 개수는 {1}이다.")
    @CsvSource(value = {"5,3", "0,0", "1,8"})
    void pin_miss(final int one, final int two) {

        final Score score = new Score();
        score.add(new Pin(one));
        score.add(new Pin(two));

        assertThat(score.status()).isEqualTo(ScoreType.MISS);
    }

    @DisplayName("볼링 핀의 개수가 10개를 초과한다.")
    @ParameterizedTest(name = "첫번째 넘어진 개수는 {0}이고, 두번째 넘어진 개수는 {1}이다.")
    @CsvSource(value = {"5,10", "2,9", "3,9"})
    void pin_validate(final int one, final int two) {

        final Score score = new Score();
        score.add(new Pin(one));

        assertThatThrownBy(() -> score.add(new Pin(two)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("쓰러뜨릴 핀 갯수가 올바르지 않습니다.");
    }

    @DisplayName("SPARE 일 경우")
    @ParameterizedTest(name = "첫번째 넘어진 개수는 {0}이고, 두번째 넘어진 개수는 {1}이다.")
    @CsvSource(value = {"0,10", "5,5", "2,8"})
    void exist_spare(final int one, final int two) {

        final Score score = new Score();
        score.add(new Pin(one));
        score.add(new Pin(two));

        assertThat(score.status().strikeOrSpare()).isTrue();
    }
}