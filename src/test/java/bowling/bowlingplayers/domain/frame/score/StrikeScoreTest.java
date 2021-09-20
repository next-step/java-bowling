package bowling.bowlingplayers.domain.frame.score;

import bowling.bowlingplayers.domain.pitching.Pitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeScoreTest {

    @Test
    @DisplayName("score 테스트")
    void score() {
        // given
        Pitching pitching = Pitching.first(10);
        Pitching nextPitching = pitching.next(10);
        nextPitching.next(10);

        // when
        int score =  new StrikeScore().score(pitching);

        // then
        assertThat(score).isEqualTo(30);
    }
}