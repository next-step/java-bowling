package bowling.controller;

import bowling.controller.dto.BowlingGameRequest;
import bowling.controller.dto.BowlingGameResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameControllerTest {

    private BowlingGameController controller;

    @BeforeEach
    void setUp() {
        controller = new BowlingGameController();
    }

    @Test
    @DisplayName("볼링게임 시작 테스트")
    void startGame() {
        // given
        BowlingGameRequest request = new BowlingGameRequest("LDS");

        // when
        BowlingGameResponse bowlingGameResponse = controller.startGame(request);

        // then
        assertThat(bowlingGameResponse.getParticipantName()).isEqualTo("LDS");
        assertThat(bowlingGameResponse.getNextFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("투구 처리")
    void pitchBall() {
        // given
        BowlingGameRequest request = new BowlingGameRequest("LDS");
        BowlingGameRequest request10 = new BowlingGameRequest("LDS", 10);
        BowlingGameRequest request9 = new BowlingGameRequest("LDS", 9);
        BowlingGameRequest request1 = new BowlingGameRequest("LDS", 1);

        // when
        controller.startGame(request);
        controller.pitchBall(request10);
        controller.pitchBall(request9);
        BowlingGameResponse response = controller.pitchBall(request1);

        // then
        assertThat(response.getParticipantName()).isEqualTo("LDS");
        assertThat(response.getNextFrameNumber()).isEqualTo(3);
        assertThat(response.isFinished()).isFalse();
        assertThat(response.getPinDownResults()).isEqualTo(Arrays.asList("X", "9|/"));
    }

    @Test
    @DisplayName("게임 종료")
    void finishGame() {
        // given
        BowlingGameRequest request = new BowlingGameRequest("LDS");
        BowlingGameRequest request10 = new BowlingGameRequest("LDS", 10);

        // when
        BowlingGameResponse response = null;
        controller.startGame(request);
        controller.pitchBall(new BowlingGameRequest("LDS", 3));
        controller.pitchBall(new BowlingGameRequest("LDS", 0));
        controller.pitchBall(new BowlingGameRequest("LDS", 7));
        controller.pitchBall(new BowlingGameRequest("LDS", 3));
        controller.pitchBall(new BowlingGameRequest("LDS", 0));
        controller.pitchBall(new BowlingGameRequest("LDS", 0));
        for (int i = 3; i < 11; i++) {
            response = controller.pitchBall(request10);
        }

        // then
        assertThat(response.getParticipantName()).isEqualTo("LDS");
        assertThat(response.getNextFrameNumber()).isEqualTo(10);
        assertThat(response.isFinished()).isTrue();
        assertThat(response.getPinDownResults()).isEqualTo(Arrays.asList("3|-", "7|/", "-|-", "X", "X", "X", "X", "X", "X", "X|X"));
    }

}