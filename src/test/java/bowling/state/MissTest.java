package bowling.state;

import bowling.domain.Pins;
import bowling.domain.exception.CannotBowlException;
import bowling.domain.state.Miss;
import bowling.domain.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {
    private State miss;

    @BeforeEach
    void setUp() {
        Pins firstPins = Pins.ofFirstPitch(7);
        Pins secondPins = Pins.ofFirstPitch(2);
        miss = (Miss.of(firstPins, secondPins));
    }

    @Test
    @DisplayName("턴이 끝났는지 확인 테스트")
    void isFinishedTest() {
        assertThat(miss.isFinished()).isTrue();
    }

    @Test
    @DisplayName("bowl 예외 테스트")
    void cannotBowlExceptionTest() {
        assertThatThrownBy(() -> miss.stateAfterBowling(3))
                .isInstanceOf(CannotBowlException.class)
                .hasMessage("더 이상 투구할 수 없습니다.");
    }
}
