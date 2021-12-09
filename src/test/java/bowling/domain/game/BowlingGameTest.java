package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @DisplayName("생성 테스트")
    @Test
    void createTest() {
        assertThat(BowlingGame.readyGame(User.from("miz")))
                .isEqualTo(BowlingGame.of(User.from("miz"), Frames.readyFrames()));
    }
}
