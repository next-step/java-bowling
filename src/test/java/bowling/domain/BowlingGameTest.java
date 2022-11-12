package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.pin.FallenPin;
import bowling.domain.player.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @DisplayName("이름 문자열 리스트로 볼링게임을 생성한다")
    @Test
    void of() {
        assertThat(players()).isEqualTo(new BowlingGame(List.of(lane("PJS"), lane("KJP"))));
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
        assertThat(players().isAllFinished()).isFalse();
    }

    private BowlingGame players() {
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
