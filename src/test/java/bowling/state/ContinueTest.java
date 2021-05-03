package bowling.state;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.domain.exception.PinsCountException;
import bowling.domain.state.Continue;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ContinueTest {
    private State continueState;

    @BeforeEach
    void setUp() {
        Pins pins = Pins.of(7);
        continueState = Continue.of(pins);
    }

    @Test
    @DisplayName("턴이 끝났는지 확인 테스트")
    void isFinishedTest() {
        assertThat(continueState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Miss 상태로 변경 확인 테스트")
    void bowlToMissTest() {
        Pins firstPins = Pins.of(7);
        Pins secondPins = Pins.of(2);
        assertThat(continueState.stateAfterPitch(Pins.of(2))).isEqualTo(Miss.of(firstPins, secondPins));
    }

    @Test
    @DisplayName("Spare 상태로 변경 확인 테스트")
    void bowlToSpareTest() {
        Pins firstPins = Pins.of(7);
        Pins secondPins = Pins.of(3);
        assertThat(continueState.stateAfterPitch(Pins.of(3))).isEqualTo(Spare.of(firstPins, secondPins));
    }

    @Test
    @DisplayName("추가 점수가 1개 일 때 보너스 점수 테스트")
    void OneBonusFrameScoreTest() {
        FrameScore prevFrameScore = FrameScore.of(10,1);
        assertThat(continueState.frameScoreWithBonus(prevFrameScore)).isEqualTo(FrameScore.of(17,0));
    }

    @Test
    @DisplayName("추가 점수가 2개 일 때 보너스 점수 테스트")
    void TwoBonusFrameScoreTest() {
        FrameScore prevFrameScore = FrameScore.of(10,2);
        assertThat(continueState.frameScoreWithBonus(prevFrameScore)).isEqualTo(FrameScore.of(17,-1));
    }

    @ParameterizedTest(name = "넘어뜨린 핀 개수 예외 테스트")
    @ValueSource(ints = {5,7,-1,11})
    void pinsCountExceptionTest(int secondPitch) {
        assertThatThrownBy(() -> continueState.stateAfterPitch(Pins.of(secondPitch)))
                .isInstanceOf(PinsCountException.class)
                .hasMessage("넘어뜨린 핀수는 0에서 10 사이의 정수여야 합니다.");
    }
}
