package bowling.refactoring.domain;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class ScoreTest {

    @Test
    void strike()  {
        Score score = new Score();
        score.bowl(10);

        assertSoftly(softly -> {
            assertThat(score.isStrike()).isTrue();
            assertThat(score.isSpare()).isFalse();
            assertThat(score.firstScore().count()).isEqualTo(10);
        });
    }

    @Test
    void spare()  {
        Score score = new Score();
        score.bowl(2);
        score.bowl(8);

        assertSoftly(softly -> {
            assertThat(score.isStrike()).isFalse();
            assertThat(score.isSpare()).isTrue();
            assertThat(score.firstScore().count()).isEqualTo(2);
            assertThat(score.secondScore().count()).isEqualTo(8);
        });
    }

    @Test
    void bonus()  {
        Score score = new Score();
        score.bowl(10);
        score.bowl(1);
        score.bowl(9);

        assertSoftly(softly -> {
            assertThat(score.isStrike()).isFalse();
            assertThat(score.isSpare()).isFalse();
            assertThat(score.firstScore().count()).isEqualTo(10);
            assertThat(score.secondScore().count()).isEqualTo(1);
            assertThat(score.bonusScore().count()).isEqualTo(9);
        });
    }
}
