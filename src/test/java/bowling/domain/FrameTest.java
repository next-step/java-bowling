package bowling.domain;

import bowling.exception.FrameTryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @DisplayName("해당 프레임이 마지막 라운드인지 판별한다")
    @Test
    void frameIsFinalRoundTest() {
        Frame firstFrame = NormalFrame.from(Round.firstRound());
        Frame finalFrame = FinalFrame.from(Round.finalRound());
        assertThat(finalFrame.isLastRound()).isEqualTo(Boolean.TRUE);
        assertThat(firstFrame.isLastRound()).isEqualTo(Boolean.FALSE);
    }

    @DisplayName("스트라이크는 한번만 투구하면 종료된다")
    @Test
    void throwBallStrikeTest() {
        Frame normalFrame = NormalFrame.from(Round.firstRound());
        normalFrame.throwBall(10);
        assertThat(normalFrame.roundEnded()).isTrue();
    }

    @DisplayName("1~9프레임은 공을 두번 던질 수 있다")
    @Test
    void throwBallNotStrikeTest() {
        Frame normalFrame = NormalFrame.from(Round.firstRound());
        normalFrame.throwBall(8);
        normalFrame.throwBall(2);
        assertThat(normalFrame.roundEnded()).isTrue();
    }

    @DisplayName("종료된 프레임에서 공을 던질 수 없다")
    @Test
    void throwBallExceptionTest() {
        Frame normalFrame = NormalFrame.from(Round.firstRound());
        normalFrame.throwBall(7);
        normalFrame.throwBall(1);
        assertThatThrownBy(() -> normalFrame.throwBall(1))
                .isInstanceOf(FrameTryException.class);
    }

    @DisplayName("마지막 프레임에서 첫투구에서 스트이크를 하면 총 3번 던질 수 있다")
    @Test
    void throwBallStrikeInFinalTest() {
        Frame finalFrame = FinalFrame.from(Round.finalRound());
        finalFrame.throwBall(10);
        finalFrame.throwBall(10);
        finalFrame.throwBall(10);
        assertThat(finalFrame.roundEnded()).isTrue();
    }

    @DisplayName("마지막 프레임에서 스페어 처리를 하면 총 3번 던질 수 있다")
    @Test
    void throwBallSpareInFinalTest() {
        Frame finalFrame = FinalFrame.from(Round.finalRound());
        finalFrame.throwBall(8);
        finalFrame.throwBall(2);
        finalFrame.throwBall(9);
        assertThat(finalFrame.roundEnded()).isTrue();
    }

    @DisplayName("프레임이 아직 시작 안한지 확인한다")
    @Test
    void frameNotYetTest() {
        Frame frame = NormalFrame.from(Round.firstRound());
        assertThat(frame.isNotYetStart()).isTrue();
    }
}
