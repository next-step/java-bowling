package bowling.domain.state;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTest {
    @Test
    @DisplayName("Strike 반환 확인")
    public void getStrike() {
        State state = new InitialState(false);

        State result = state.roll(10);

        assertThat(result).isInstanceOf(Strike.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 9})
    @DisplayName("Miss 반환 확인")
    public void getMiss(int knockedDownPinCOunt) {
        State state = new InitialState(false);

        State result = state.roll(knockedDownPinCOunt);

        assertThat(result).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("Gutter 반환 확인")
    public void getGutter() {
        State state = new InitialState(false);

        State result = state.roll(0);

        assertThat(result).isInstanceOf(Gutter.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,10", "2,8", "5,5"})
    @DisplayName("Spare 반환 확인")
    public void getSpare(int firstPinCount, int secondPinCount) {
        State state = new InitialState(false);

        State first = state.roll(firstPinCount);
        State result = first.roll(secondPinCount);

        assertThat(result).isInstanceOf(Spare.class);
    }
}
