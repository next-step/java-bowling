package bowling.state;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstBowlTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2", "2,3", "4,5"})
    @DisplayName("Miss")
    void missTest(int first, int second) {
        FirstBowl firstBowl = new FirstBowl(first);
        State state = firstBowl.bowl(second);
        assertThat(state).isInstanceOf(Miss.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"5,5", "6,4", "1,9"})
    @DisplayName("Spare")
    void spareTest(int first, int second) {
        FirstBowl firstBowl = new FirstBowl(first);
        State state = firstBowl.bowl(second);
        assertThat(state).isInstanceOf(Spare.class);
    }

}
