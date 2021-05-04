package bowling.controller;

import bowling.controller.dto.BowlingGameRequest;
import bowling.controller.dto.BowlingGameResponse;
import bowling.infra.BowlingGameDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingGameControllerTest {

    private BowlingGameController controller;

    @BeforeEach
    void setUp() {
        BowlingGameDatabase.bowlingGameData.clear();
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
        assertThat(bowlingGameResponse.getNextTurnParticipant()).isEqualTo("LDS");
    }

    @Test
    @DisplayName("볼링게임 시작 테스트 - n명의 참가자")
    void startGame_nParticipants() {
        // given
        BowlingGameRequest requestLDS = new BowlingGameRequest("LDS");
        BowlingGameRequest requestBBB = new BowlingGameRequest("BBB");

        // when
        controller.startGame(requestLDS);
        BowlingGameResponse bowlingGameResponse = controller.startGame(requestBBB);

        // then
        assertThat(bowlingGameResponse.getNextTurnParticipant()).isEqualTo("LDS");
        assertThat(bowlingGameResponse.getFrameBoards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("볼링게임 시작 테스트 - 기등록 참가자")
    void startGame_participant_alreadyExists() {
        // given
        BowlingGameRequest requestLDS = new BowlingGameRequest("LDS");

        // when
        controller.startGame(requestLDS);

        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> controller.startGame(requestLDS))
                .withMessageMatching("이미 등록한 참가자입니다.");
    }

    @Test
    @DisplayName("투구 처리")
    void pitchBall() {
        // given
        BowlingGameRequest request = new BowlingGameRequest("LDS");
        BowlingGameRequest request10 = new BowlingGameRequest("LDS", 10);
        BowlingGameRequest request9 = new BowlingGameRequest("LDS", 9);
        BowlingGameRequest request1 = new BowlingGameRequest("LDS", 1);

        BowlingGameRequest requestAAA = new BowlingGameRequest("AAA");
        BowlingGameRequest requestAAA2 = new BowlingGameRequest("AAA", 2);
        BowlingGameRequest requestAAA5 = new BowlingGameRequest("AAA", 5);
        BowlingGameRequest requestAAA7 = new BowlingGameRequest("AAA", 7);

        // when
        controller.startGame(request);
        controller.startGame(requestAAA);


        controller.pitchBall(request10);
        controller.pitchBall(request9);
        controller.pitchBall(request1);

        controller.pitchBall(requestAAA2);
        controller.pitchBall(requestAAA5);

        BowlingGameResponse response = controller.pitchBall(requestAAA7);

        // then
        assertThat(response.getNextTurnParticipant()).isEqualTo("AAA");
        assertThat(response.getFrameBoards().size()).isEqualTo(2);
        assertThat(response.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("투구 처리 - 미등록 참가자")
    void pitchBall_participantIsNotExists() {
        // given
        BowlingGameRequest request = new BowlingGameRequest("LDS");
        BowlingGameRequest request10 = new BowlingGameRequest("LDS", 10);

        BowlingGameRequest requestAAA = new BowlingGameRequest("AAA");
        BowlingGameRequest requestAAA2 = new BowlingGameRequest("AAA", 2);
        BowlingGameRequest requestAAA5 = new BowlingGameRequest("AAA", 5);

        // when
        controller.startGame(request);
        controller.startGame(requestAAA);

        controller.pitchBall(request10);
        controller.pitchBall(requestAAA2);
        controller.pitchBall(requestAAA5);

        // then
        assertThrows(
                NoSuchElementException.class,
                () -> controller.pitchBall(new BowlingGameRequest("BBB", 2)),
                "존재하지 않는 참가자입니다."
        );
    }

    @Test
    @DisplayName("게임 종료")
    void finishGame() {
        // given
        BowlingGameRequest request = new BowlingGameRequest("LDS");
        BowlingGameRequest request10 = new BowlingGameRequest("LDS", 10);

        BowlingGameRequest requestAAA = new BowlingGameRequest("AAA");
        BowlingGameRequest requestAAA10 = new BowlingGameRequest("AAA", 10);

        // when
        BowlingGameResponse response = null;
        controller.startGame(request);
        controller.startGame(requestAAA);

        controller.pitchBall(new BowlingGameRequest("LDS", 3));
        controller.pitchBall(new BowlingGameRequest("LDS", 0));
        controller.pitchBall(new BowlingGameRequest("LDS", 7));
        controller.pitchBall(new BowlingGameRequest("LDS", 3));
        controller.pitchBall(new BowlingGameRequest("LDS", 0));
        controller.pitchBall(new BowlingGameRequest("LDS", 0));
        for (int i = 3; i < 12; i++) {
            response = controller.pitchBall(request10);
        }

        controller.pitchBall(new BowlingGameRequest("AAA", 5));
        controller.pitchBall(new BowlingGameRequest("AAA", 5));
        controller.pitchBall(new BowlingGameRequest("AAA", 7));
        controller.pitchBall(new BowlingGameRequest("AAA", 3));
        controller.pitchBall(new BowlingGameRequest("AAA", 2));
        controller.pitchBall(new BowlingGameRequest("AAA", 0));
        for (int i = 3; i < 12; i++) {
            response = controller.pitchBall(requestAAA10);
        }

        // then
        assertThat(response.getNextTurnParticipant()).isEqualTo("");
        assertThat(response.getFrameBoards().size()).isEqualTo(2);
        assertThat(response.isGameEnd()).isTrue();
    }

}