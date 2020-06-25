package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static bowling.domain.FakeDataForTest.NORMAL_STRIKE_FRAME_RESULT;
import static bowling.domain.FakeDataForTest.PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTests {
    @DisplayName("볼링게임 참여자 이름을 입력받아서 객체를 생성 할 수 있다.")
    @Test
    void createTest() {
        BowlingGame bowlingGame = BowlingGame.start(PLAYER_NAME);

        assertThat(bowlingGame.checkWhereFrame()).isEqualTo(0);
    }

    @DisplayName("초구를 굴려서 게임을 진행하고 결과를 확인 할 수 있다.")
    @Test
    void bowlFirstTest() {
        int numberOfHitPin = 10;
        BowlingGame bowlingGame = BowlingGame.start(PLAYER_NAME);

        List<BowlingGameResult> bowlingGameResults = bowlingGame.bowlFirst(numberOfHitPin);

        assertThat(bowlingGameResults.size()).isEqualTo(1);
        assertThat(bowlingGameResults.get(0)).isEqualTo(new BowlingGameResult(NORMAL_STRIKE_FRAME_RESULT));
    }
}
