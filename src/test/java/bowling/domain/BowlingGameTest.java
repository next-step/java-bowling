package bowling.domain;

import bowling.infra.BowlingGameDatabase;
import bowling.service.BowlingGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    private final BowlingGameService service = new BowlingGameService();

    @BeforeEach
    void setUp() {
        BowlingGameDatabase.bowlingGameData.clear();
    }

    @Test
    @DisplayName("게임 미종료 - 모든 참가자의 게임이 종료되어야 함.")
    void isNotEnd() {
        // given
        service.startGame(new Participant("AAA"));
        service.startGame(new Participant("BBB"));

        // when
        Frames frameAAA = service.findFrames(new Participant("AAA"));
        Frames frameBBB = service.findFrames(new Participant("BBB"));

        for (int i = 0; i < 12; i++) {
            frameAAA.pitch(10);
        }

        BowlingGame bowlingGame = service.findBowlingGame();

        // then
        assertThat(frameAAA.isFinished()).isTrue();
        assertThat(frameBBB.isFinished()).isFalse();
        assertThat(bowlingGame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("게임 종료 - 모든 참가자의 게임이 종료되어야 함.")
    void isEnd() {
        // given
        service.startGame(new Participant("AAA"));
        service.startGame(new Participant("BBB"));

        // when
        Frames frameAAA = service.findFrames(new Participant("AAA"));
        Frames frameBBB = service.findFrames(new Participant("BBB"));

        for (int i = 0; i < 12; i++) {
            frameAAA.pitch(10);
            frameBBB.pitch(10);
        }

        BowlingGame bowlingGame = service.findBowlingGame();

        // then
        assertThat(frameAAA.isFinished()).isTrue();
        assertThat(frameBBB.isFinished()).isTrue();
        assertThat(bowlingGame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("다음 턴 참가자 - 서로 진행된 프레임번호가 다를 경우, 프레임이 덜 진행된 참가자")
    void nextTurn_lessTurn() {
        // given
        service.startGame(new Participant("AAA"));
        service.startGame(new Participant("BBB"));

        // when
        Frames frameAAA = service.findFrames(new Participant("AAA"));
        Frames frameBBB = service.findFrames(new Participant("BBB"));

        frameAAA.pitch(2);
        frameAAA.pitch(8);
        frameAAA.pitch(10);
        frameAAA.pitch(3);
        frameAAA.pitch(5);

        frameBBB.pitch(5);
        frameBBB.pitch(5);
        frameBBB.pitch(6);
        frameBBB.pitch(4);

        BowlingGame bowlingGame = service.findBowlingGame();

        // then
        assertThat(bowlingGame.nextTurnParticipantName()).isEqualTo("BBB");
    }

    @Test
    @DisplayName("다음 턴 참가자 - 서로 진행된 프레임번호가 같을 경우, 투구를 진행중인 참가자")
    void nextTurn_sameFrame_otherPitch() {
        // given
        service.startGame(new Participant("AAA"));
        service.startGame(new Participant("BBB"));

        // when
        Frames frameAAA = service.findFrames(new Participant("AAA"));
        Frames frameBBB = service.findFrames(new Participant("BBB"));

        frameAAA.pitch(2);
        frameAAA.pitch(8);
        frameAAA.pitch(10);
        frameAAA.pitch(3);

        frameBBB.pitch(5);
        frameBBB.pitch(5);
        frameBBB.pitch(6);
        frameBBB.pitch(4);

        BowlingGame bowlingGame = service.findBowlingGame();

        // then
        assertThat(bowlingGame.nextTurnParticipantName()).isEqualTo("AAA");
    }

    @Test
    @DisplayName("다음 턴 참가자 - 서로 진행된 프레임번호가 같고, 투구도 진행하지 않은 경우 - 첫 번째 참가자")
    void nextTurn_sameFrame_samePitch() {
        // given
        service.startGame(new Participant("AAA"));
        service.startGame(new Participant("BBB"));
        service.startGame(new Participant("CCC"));

        // when
        Frames frameAAA = service.findFrames(new Participant("AAA"));
        Frames frameBBB = service.findFrames(new Participant("BBB"));
        Frames frameCCC = service.findFrames(new Participant("CCC"));

        frameAAA.pitch(2);
        frameAAA.pitch(8);
        frameAAA.pitch(10);

        frameBBB.pitch(5);
        frameBBB.pitch(5);
        frameBBB.pitch(6);
        frameBBB.pitch(4);

        frameCCC.pitch(0);
        frameCCC.pitch(0);
        frameCCC.pitch(2);
        frameCCC.pitch(3);

        BowlingGame bowlingGame = service.findBowlingGame();

        // then
        assertThat(bowlingGame.nextTurnParticipantName()).isEqualTo("AAA");
    }

}