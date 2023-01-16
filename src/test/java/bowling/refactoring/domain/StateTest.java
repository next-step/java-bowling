package bowling.refactoring.domain;

import bowling.refactoring.domain.frame.*;
import bowling.refactoring.domain.state.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class StateTest {

    @Test
    void running_state()  {
        State running = new Running();
        Frame frame = new NormalFrame();
        assertSoftly(softly -> {
            assertThat(running.isEnd()).isFalse();
            assertThat(running.isEndedCalculateScore()).isFalse();
            assertThatThrownBy(() -> running.calculateBonusScore(frame))
                    .isInstanceOf(UnsupportedOperationException.class);
            assertThatThrownBy(running::totalScore)
                    .isInstanceOf(UnsupportedOperationException.class);
        });
    }

    @Test
    void normal_state()  {
        Score score = new Score();
        score.bowl(6);
        score.bowl(2);

        State normal = new Normal(score);
        assertSoftly(softly -> {
            assertThat(normal.isEnd()).isTrue();
            assertThat(normal.isEndedCalculateScore()).isTrue();
            assertThat(normal.totalScore()).isEqualTo(8);
        });
    }

    @Test
    void bonus_state()  {
        Score score = new Score();
        score.bowl(10);
        score.bowl(2);
        score.bowl(6);

        State bonus = new Bonus(score);
        assertSoftly(softly -> {
            assertThat(bonus.isEnd()).isTrue();
            assertThat(bonus.isEndedCalculateScore()).isTrue();
            assertThat(bonus.totalScore()).isEqualTo(18);
        });
    }

    @Test
    void spare_state()  {
        State spare = new Spare();
        assertSoftly(softly -> {
            assertThat(spare.isEnd()).isTrue();
            assertThat(spare.isEndedCalculateScore()).isFalse();
            assertThat(spare.totalScore()).isEqualTo(10);
        });
    }

    @Test
    void strike_state()  {
        State strike = new Strike();
        assertSoftly(softly -> {
            assertThat(strike.isEnd()).isTrue();
            assertThat(strike.isEndedCalculateScore()).isFalse();
            assertThat(strike.totalScore()).isEqualTo(10);
        });
    }


}
