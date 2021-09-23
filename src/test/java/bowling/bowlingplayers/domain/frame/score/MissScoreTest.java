package bowling.bowlingplayers.domain.frame.score;

import bowling.bowlingplayers.domain.pitching.Pitching;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissScoreTest {

    @Test
    @DisplayName("score 테스트")
    void score() {
        // given
        Pitching pitching = Pitching.first(3);
        pitching.next(6);

        // when
        int score =  new MissScore().score(pitching);

        // then
        Assertions.assertThat(score).isEqualTo(9);
    }
}