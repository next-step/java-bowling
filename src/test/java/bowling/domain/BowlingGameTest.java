package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.frame.BowlingFrames;
import bowling.domain.frame.FrameResult;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class BowlingGameTest {

    @Test
    void invalid_player_count() {
        assertThatThrownBy(() -> BowlingGame.newInstance(0))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void game_ready_results() {
        BowlingGame bowlingGame = BowlingGame.newInstance(2);
        BowlingGameResult bowlingGameResult = bowlingGame.getResult();

        bowlingGameResult.put(0,  BowlingFrames.newInstance().getFrameResults());
        bowlingGameResult.put(1,  BowlingFrames.newInstance().getFrameResults());
    }

    @Test
    void get_current_player_by_frame(){
        BowlingGame bowlingGame = BowlingGame.newInstance(4);
        bowlingGame.play(0, 10);

        assertThat(bowlingGame.getCurrentPlayers(0)).isEqualTo(Arrays.asList(1,2,3));
    }
}
