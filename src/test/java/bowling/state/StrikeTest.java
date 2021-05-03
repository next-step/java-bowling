package bowling.state;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.domain.exception.CannotBowlException;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {
    private State strike;

    @BeforeEach
    void setUp() {
        Pins pins = Pins.of(10);
        strike = Strike.of(pins);
    }

    @Test
    @DisplayName("턴이 끝났는지 확인 테스트")
    void isFinishedTest() {
        assertThat(strike.isFinished()).isTrue();
    }

    @Test
    @DisplayName("추가 점수가 1개 일 때 보너스 점수 테스트")
    void OneBonusFrameScoreTest() {
        FrameScore prevFrameScore = FrameScore.of(10,1);
        assertThat(strike.frameScoreWithBonus(prevFrameScore)).isEqualTo(FrameScore.of(20,0));
    }

    @Test
    @DisplayName("추가 점수가 2개 일 때 보너스 점수 테스트")
    void TwoBonusFrameScoreTest() {
        FrameScore prevFrameScore = FrameScore.of(10,2);
        assertThat(strike.frameScoreWithBonus(prevFrameScore)).isEqualTo(FrameScore.of(20,1));
    }

    @Test
    @DisplayName("bowl 예외 테스트")
    void cannotBowlExceptionTest() {
        assertThatThrownBy(() -> strike.stateAfterPitch(Pins.of(3)))
                .isInstanceOf(CannotBowlException.class)
                .hasMessage("더 이상 투구할 수 없습니다.");
    }
}
