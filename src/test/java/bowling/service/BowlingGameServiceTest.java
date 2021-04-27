package bowling.service;

import bowling.domain.Frames;
import bowling.domain.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingGameServiceTest {

    private BowlingGameService service;

    @BeforeEach
    void setUp () {
        service = new BowlingGameService();
    }

    @Test
    @DisplayName("볼링게임 시작 테스트")
    void startGame() {
        // given
        Participant participant = new Participant("LDS");

        // when
        service.startGame(participant);
        Frames frames = service.findFrames(participant);

        // then
        assertThat(frames).isNotNull();
    }

    @Test
    @DisplayName("볼링게임 실행 테스트")
    void pitchBall() {
        // given
        Participant participant = new Participant("LDS");

        // when
        service.startGame(participant);
        service.pitchBall(participant, 2);
        Frames frames = service.findFrames(participant);

        // then
        assertThat(1).isEqualTo(frames.lastFrameNumber());
        assertThat(frames.isFinished()).isFalse();
    }

    @Test
    @DisplayName("볼링게임 실행 - 게임종료")
    void finishGame() {
        // given
        Participant participant = new Participant("LDS");

        // when
        service.startGame(participant);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);
        service.pitchBall(participant,10);

        Frames frames = service.findFrames(participant);

        // then
        assertThat(10).isEqualTo(frames.lastFrameNumber());
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 참가자")
    void participantIsNotExist() {
        // given
        Participant participant = new Participant("LDS");

        // when
        service.startGame(participant);

        // then
        assertThrows(NoSuchElementException.class, () -> service.findFrames(new Participant("ABC")), "존재하지 않는 참가자입니다.");
    }
}