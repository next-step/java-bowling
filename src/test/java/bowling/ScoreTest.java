package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    @DisplayName("10 점 이상 입력시 예외 발생")
    void scoreTest() {
        assertThatThrownBy(() -> new Score(11)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두개의 스코어를 합한 결과를 반환")
    void sumTest() {
        Score score1 = new Score(1);
        Score score2 = new Score(1);

        assertThat(score1.sum(score2)).isEqualTo(2);
    }

    @Test
    @DisplayName("점수를 문자열로 변환")
    void getScoreStringTest() {
        IntStream.rangeClosed(0, 9)
                .forEach(i -> assertThat(new Score(i).getScoreString()).isEqualTo(String.valueOf(i)));
    }
}
