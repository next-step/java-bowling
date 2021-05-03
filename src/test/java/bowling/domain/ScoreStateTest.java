package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreStateTest {

    @DisplayName("점수의 상태를 생성한다")
    @Test
    void initTest() {
        ScoreState noneState = ScoreState.ofNone();
        ScoreState spareState = ScoreState.ofSpare();
        ScoreState strikeState = ScoreState.ofStrike();
        assertThat(noneState.canCalculate()).isTrue();
        assertThat(spareState.canCalculate()).isFalse();
        assertThat(strikeState.canCalculate()).isFalse();
    }
}
