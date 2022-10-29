package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스코어 타입 테스트")
class ScoreTypeTest {

    @DisplayName("아무것도 하지 않은 경우 미스다.")
    @Test
    void none() {
        Score score = getScoreFixture();

        assertThat(score.status()).isEqualTo(ScoreType.MISS);
    }

    @ParameterizedTest(name = "{0} 개를 쓰러뜨린 경우 미스다.")
    @ValueSource(ints = {0, 9})
    void miss1(int pinCount) {
        Score score = getScoreFixture(pinCount);

        assertThat(score.status()).isEqualTo(ScoreType.MISS);
    }

    @ParameterizedTest(name = "{0}|{1} 인 경우 미스다.")
    @CsvSource({
            "0,1",
            "2,3",
            "10,2"
    })
    void miss2(int pinCount1, int pinCount2) {
        Score score = getScoreFixture(pinCount1, pinCount2);

        assertThat(score.status()).isEqualTo(ScoreType.MISS);
    }

    @ParameterizedTest(name = "{0}|{1}|{2} 인 경우 미스다.")
    @CsvSource({
            "10,10,0",
            "5,5,5"
    })
    void miss3(int pinCount1, int pinCount2, int pinCount3) {
        Score score = getScoreFixture(pinCount1, pinCount2, pinCount3);

        assertThat(score.status()).isEqualTo(ScoreType.MISS);
    }

    @DisplayName("10 개를 쓰러뜨린 경우 스트라이크다.")
    @Test
    void strike1() {
        Score score = getScoreFixture(10);

        assertThat(score.status()).isEqualTo(ScoreType.STRIKE);
    }

    @ParameterizedTest(name = "{0}|{1} 인 경우 스트라이크다.")
    @CsvSource({
            "10,10",
    })
    void strike2(int pinCount1, int pinCount2) {
        Score score = getScoreFixture(pinCount1, pinCount2);

        assertThat(score.status()).isEqualTo(ScoreType.STRIKE);
    }

    @ParameterizedTest(name = "{0}|{1}|{2} 인 경우 스트라이크다.")
    @CsvSource({
            "10,10,10",
            "5,5,10"
    })
    void strike3(int pinCount1, int pinCount2, int pinCount3) {
        Score score = getScoreFixture(pinCount1, pinCount2, pinCount3);

        assertThat(score.status()).isEqualTo(ScoreType.STRIKE);
    }

    @ParameterizedTest(name = "{0}|{1} 인 경우 스패어다.")
    @CsvSource({
            "0,10",
            "1,9",
            "9,1"
    })
    void spare1(int pinCount1, int pinCount2) {
        Score score = getScoreFixture(pinCount1, pinCount2);

        assertThat(score.status()).isEqualTo(ScoreType.SPARE);
    }

    @ParameterizedTest(name = "{0}|{1}|{2} 인 경우 스패어다.")
    @CsvSource({
            "10,0,10",
            "10,1,9",
            "10,9,1"
    })
    void spare2(int pinCount1, int pinCount2, int pinCount3) {
        Score score = getScoreFixture(pinCount1, pinCount2, pinCount3);

        assertThat(score.status()).isEqualTo(ScoreType.SPARE);
    }

    Score getScoreFixture(Integer ... pinCounts) {
        Score score = new Score();

        for (Integer pinCount : pinCounts) {
            score.addPin(Pin.of(pinCount));
        }

        return score;
    }
}
