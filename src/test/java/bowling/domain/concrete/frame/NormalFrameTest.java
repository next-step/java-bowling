package bowling.domain.concrete.frame;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = NormalFrame.init();
    }

    @Test
    @DisplayName("첫 투구에서 모든 핀을 쓰러트리면 프레임을 종료한다.")
    void strike() {
        normalFrame.roll(RollResult.of(10));

        assertThat(normalFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("두 번째 투구까지 마쳤다면 프레임을 종료한다.")
    void missedOrSpare() {
        NormalFrame missedFrame = NormalFrame.init();
        missedFrame.roll(RollResult.of(7));
        missedFrame.roll(RollResult.of(2));

        NormalFrame spareFrame = NormalFrame.init();
        spareFrame.roll(RollResult.of(8));
        spareFrame.roll(RollResult.of(2));

        assertAll(
            () -> assertThat(missedFrame.isEnded()).isTrue(),
            () -> assertThat(spareFrame.isEnded()).isTrue()
        );
    }

    @Test
    @DisplayName("이미 종료된 프레임에서 공을 더 던질 수 없다.")
    void cannotThrowBallIfFrameAlreadyEnded() {
        NormalFrame strikeFrame = NormalFrame.init();
        strikeFrame.roll(RollResult.of(10));

        NormalFrame missedFrame = NormalFrame.init();
        missedFrame.roll(RollResult.of(7));
        missedFrame.roll(RollResult.of(2));

        NormalFrame spareFrame = NormalFrame.init();
        spareFrame.roll(RollResult.of(8));
        spareFrame.roll(RollResult.of(2));

        assertAll(
            () -> assertThatThrownBy(() -> strikeFrame.roll(RollResult.of(0)))
                .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> missedFrame.roll(RollResult.of(0)))
                .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> spareFrame.roll(RollResult.of(0)))
                .isInstanceOf(IllegalStateException.class)
        );
    }

    @Test
    @DisplayName("스트라이크를 쳤다면 다음 두 번의 투구에서 점수를 획득했을 때 점수를 얻을 수 있다.")
    void strikeScore() {
        NormalFrame nextFrame = NormalFrame.init();
        NormalFrame strikeFrame = NormalFrame.init(nextFrame);

        strikeFrame.roll(RollResult.of(10));
        nextFrame.roll(RollResult.of(5));
        nextFrame.roll(RollResult.of(3));

        assertThat(strikeFrame.getScore().getValue()).isEqualTo(18);
    }

    @Test
    @DisplayName("스페어라면 투구를 한 번 더 한 뒤에 점수를 계산할 수 있다.")
    void spareScore() {
        NormalFrame nextFrame = NormalFrame.init();
        NormalFrame spareFrame = NormalFrame.init(nextFrame);

        spareFrame.roll(RollResult.of(8));
        spareFrame.roll(RollResult.of(2));
        nextFrame.roll(RollResult.of(5));

        assertThat(spareFrame.getScore().getValue()).isEqualTo(15);
    }

    @Test
    @DisplayName("스트라이크, 스페어 상태가 아니면서 투구를 완료한 프레임에서는 바로 점수를 계산할 수 있다.")
    void getScore() {
        normalFrame.roll(RollResult.of(8));
        normalFrame.roll(RollResult.of(1));

        assertThat(normalFrame.getScore().getValue()).isEqualTo(9);
    }

    @Test
    @DisplayName("점수 계산이 완료되지 않았어도 조회할 수는 있다.")
    void canGetScoreEvenIfCalculationIsNotCompleted() {
        NormalFrame nextFrame = NormalFrame.init();
        NormalFrame strikeFrame = NormalFrame.init(nextFrame);

        strikeFrame.roll(RollResult.of(10));
        nextFrame.roll(RollResult.of(5));

        Score unavailableScore = strikeFrame.getScore();

        assertAll(
            () -> assertThat(unavailableScore.isUnavailable()).isTrue(),
            () -> assertThat(unavailableScore.getValue()).isEqualTo(15)
        );
    }

}
