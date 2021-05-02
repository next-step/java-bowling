package bowling.frame;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.domain.exception.CannotBowlException;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {
    private Frame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = NormalFrame.init();
    }

    @Test
    @DisplayName("Ready, Strike 상태일 때 종료 확인 테스트")
    void readyStrikeTest() {
        assertThat(normalFrame.isFinished()).isFalse();
        normalFrame.bowl(Pins.of(10));
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Continue, Spare 상태일 때 종료 확인 테스트")
    void continueSpareTest() {
        normalFrame.bowl(Pins.of(3));
        assertThat(normalFrame.isFinished()).isFalse();
        normalFrame.bowl(Pins.of(7));
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Continue, Miss 상태일 때 종료 확인 테스트")
    void continueMissTest() {
        normalFrame.bowl(Pins.of(3));
        assertThat(normalFrame.isFinished()).isFalse();
        normalFrame.bowl(Pins.of(5));
        assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("bowl 예외 테스트")
    void cannotBowlExceptionTest() {
        normalFrame.bowl(Pins.of(10));
        assertThatThrownBy(() -> normalFrame.bowl(Pins.of(3)))
                .isInstanceOf(CannotBowlException.class)
                .hasMessage("더 이상 투구할 수 없습니다.");
    }

    @Test
    @DisplayName("STRIKE 이면 보너스 투구 점수 2개가 더해져서 계산")
    void strike_calculate_score_test() {
        // given
        Frame currentFrame = NormalFrame.init();
        currentFrame.bowl(Pins.of(10));

        Frame nextFrame = currentFrame.next();
        nextFrame.bowl(Pins.of(3));
        nextFrame.bowl(Pins.of(2));

        // when
        FrameScore frameScore = currentFrame.frameScore();

        // then
        assertThat(frameScore).isEqualTo(frameScore.of(15, 0));
    }

    @Test
    @DisplayName("SPARE 이면 보너스 투구 점수 1개가 더해져서 계산")
    void spare_calculate_score_test() {
        // given
        Frame currentFrame = NormalFrame.init();
        currentFrame.bowl(Pins.of(7));
        currentFrame.bowl(Pins.of(3));

        Frame nextFrame = currentFrame.next();
        nextFrame.bowl(Pins.of(2));

        // when
        FrameScore frameScore = currentFrame.frameScore();

        // then
        assertThat(frameScore).isEqualTo(frameScore.of(12, 0));
    }

    @Test
    @DisplayName("miss 일 때 점수 계산")
    void miss_calculate_score_test() {
        // given
        Frame currentFrame = NormalFrame.init();
        currentFrame.bowl(Pins.of(7));
        currentFrame.bowl(Pins.of(2));

        // when
        FrameScore frameScore = currentFrame.frameScore();

        // then
        assertThat(frameScore).isEqualTo(frameScore.of(9, 0));
    }

    @Test
    @DisplayName("ready 일 때 점수 계산")
    void ready_calculate_score_test() {
        // given
        Frame currentFrame = NormalFrame.init();
        // when
        FrameScore frameScore = currentFrame.frameScore();

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(frameScore.score(), FrameScore.UNSCORED_SCORE));
    }

    @Test
    @DisplayName("continue 일 때 점수 계산")
    void continue_calculate_score_test() {
        // given
        Frame currentFrame = NormalFrame.init();
        currentFrame.bowl(Pins.of(4));
        // when
        FrameScore frameScore = currentFrame.frameScore();

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(frameScore.score(), FrameScore.UNSCORED_SCORE));
    }
}
