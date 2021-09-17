package bowling.bowlingscore.domain.frame.score;

import bowling.bowlingscore.domain.pitching.Pitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SpareScoreTest {

    @Test
    @DisplayName("score 테스트")
    void score() {
        // given
        Pitching pitching = Pitching.first(8);
        Pitching nextPitching = pitching.next(2);
        nextPitching.next(10);

        // when
        int score =  new SpareScore().score(pitching);

        // then
        assertThat(score).isEqualTo(20);
    }
}