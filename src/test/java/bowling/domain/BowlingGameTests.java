package bowling.domain;

import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.exceptions.InvalidTryNextFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static bowling.domain.FakeDataForTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        BowlingGame bowlingGame = BowlingGame.start(PLAYER_NAME);

        List<BowlingGameResult> bowlingGameResults = bowlingGame.bowlFirst(TEN);

        assertThat(bowlingGameResults.size()).isEqualTo(1);
        assertThat(bowlingGameResults.get(0)).isEqualTo(new BowlingGameResult(NORMAL_STRIKE_FRAME_RESULT));
    }

    @DisplayName("현재 프레임을 진행하고 전체 결과를 확인할 수 있다.")
    @Test
    void bowlCurrentFrameTest() {
        BowlingGame bowlingGame = BowlingGame.start(PLAYER_NAME);
        bowlingGame.bowlFirst(FIVE);

        List<BowlingGameResult> bowlingGameResults = bowlingGame.bowlCurrentFrame(FIVE);

        assertThat(bowlingGameResults.size()).isEqualTo(1);
        assertThat(bowlingGameResults.get(0)).isEqualTo(new BowlingGameResult(NORMAL_FIVE_SPARE_FRAME_RESULT));
    }

    @DisplayName("이미 완료된 현재 프레임을 진행할 수 없다.")
    @Test
    void bowlCurrentFrameValidationTest() {
        BowlingGame bowlingGame = BowlingGame.start(PLAYER_NAME);
        bowlingGame.bowlFirst(TEN);

        assertThatThrownBy(() -> bowlingGame.bowlCurrentFrame(FIVE))
                .isInstanceOf(InvalidTryBowlException.class);
    }

    @DisplayName("현재 프레임이 완료됐다면 다음 프레임으로 진행할 수 있다.")
    @Test
    void bowlToNextFrameTest() {
        BowlingGame bowlingGame = BowlingGame.start(PLAYER_NAME);
        bowlingGame.bowlFirst(TEN);

        List<BowlingGameResult> bowlingGameResults = bowlingGame.toNextFrame(FIVE);

        assertThat(bowlingGameResults.size()).isEqualTo(2);
        assertThat(bowlingGameResults.get(0)).isEqualTo(new BowlingGameResult(NORMAL_STRIKE_FRAME_RESULT));
        assertThat(bowlingGameResults.get(1)).isEqualTo(new BowlingGameResult(NORMAL_FIVE_IN_PROGRESS_FRAME_RESULT));
    }

    @DisplayName("현재 프레임이 완료되지 않은 상태에서 다음 프레임을 진행할 수 없다.")
    @Test
    void bowlToNextFrameValidationTest() {
        BowlingGame bowlingGame = BowlingGame.start(PLAYER_NAME);
        bowlingGame.bowlFirst(FIVE);

        assertThatThrownBy(() -> bowlingGame.toNextFrame(FIVE)).isInstanceOf(InvalidTryNextFrameException.class);
    }
}
