package bowling.domain.result;

import bowling.domain.user.UserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    public static GameResult DEFAULT_GAME_RESULT = new GameResult(UserTest.MIZ, FrameResultsTest.STRIKE_AND_FIVE);

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(new GameResult(UserTest.MIZ, FrameResultsTest.STRIKE_AND_FIVE))
                .isEqualTo(new GameResult(UserTest.MIZ, FrameResultsTest.STRIKE_AND_FIVE));
    }
}
