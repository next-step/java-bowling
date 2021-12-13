package bowling.domain;

import bowling.domain.frame.Round;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RoundTest {

    @DisplayName("정상 생성 Test")
    @Test
    void createTest() {
        assertThat(Round.from(5)).isEqualTo(Round.from(5));
    }

    @DisplayName("생성시 범위 밖일 경우 illegal exception")
    @Test
    void createFailTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> Round.from(0));
        assertThatIllegalArgumentException().isThrownBy(() -> Round.from(11));
    }

    @DisplayName("nextRound는 다음 라운드를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1:2", "2:3", "4:5", "5:6", "6:7", "7:8", "8:9", "9:10"}, delimiter = ':')
    void nextTest(int before, int after) {
        assertThat(Round.from(before).nextRound()).isEqualTo(Round.from(after));
    }

    @DisplayName("isLastRound round 가 10일 경우 true")
    @Test
    void isLastTest() {
        assertThat(Round.from(10).isLast()).isTrue();
        assertThat(Round.from(9).isLast()).isFalse();
    }

    @DisplayName("allRounds() 모든 라운드를 List 로 반환한다.")
    @Test
    void allRounds() {
        assertThat(Round.allRounds()).isEqualTo(
                Arrays.asList(
                        Round.from(1),
                        Round.from(2),
                        Round.from(3),
                        Round.from(4),
                        Round.from(5),
                        Round.from(6),
                        Round.from(7),
                        Round.from(8),
                        Round.from(9),
                        Round.from(10)
                )
        );
    }
}
