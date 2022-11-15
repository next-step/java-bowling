package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.pin.FallenPin;
import bowling.domain.player.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BowlingGameTest {

    @DisplayName("이름 문자열 리스트로 볼링게임을 생성한다")
    @Test
    void of() {
        assertThat(bowlingGame()).isEqualTo(new BowlingGame(List.of(lane("PJS"), lane("KJP"))));
    }

    @DisplayName("이름이 중복된다면 볼링게임을 생성시 예외가 발생한다")
    @Test
    void ofFail_whenDuplicatedPlayerNames() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> BowlingGame.of(List.of("PJS", "PJS")))
                .withMessage("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("모든 볼링게임이 끝났다면 참을 반환한다")
    @Test
    void isAllFinished_true() {
        BowlingGame bowlingGame = new BowlingGame(List.of(
                lane("PJS", finishedFrames()),
                lane("KJP", finishedFrames())));

        assertThat(bowlingGame.isAllFinished()).isTrue();
    }

    @DisplayName("모든 볼링게임이 끝나지 않았다면 거짓을 반환한다")
    @Test
    void isAllFinished_false() {
        assertThat(bowlingGame().isAllFinished()).isFalse();
    }

    private BowlingGame bowlingGame() {
        return BowlingGame.of(List.of("PJS", "KJP"));
    }

    private Lane lane(String playerName) {
        return new Lane(new PlayerName(playerName));
    }

    private Lane lane(String playerName, Frames frames) {
        return new Lane(new PlayerName(playerName), frames);
    }

    private Frames finishedFrames() {
        Frames result = Frames.init();
        for (int i = 0; i < 12; i++) {
            result = result.bowl(FallenPin.of(10));
        }
        return result;
    }
}
