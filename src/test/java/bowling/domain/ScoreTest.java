package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ScoreTest {

    @DisplayName("Test of illegal argument")
    @Test
    void testIllegalArgument() {
        assertThatThrownBy(() -> new Score(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Score(31)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Test of addition")
    @ParameterizedTest
    @CsvSource(value = {
            "5:3:8",
            "10:10:20",
            "10:20:30"
    }, delimiter = ':')
    void testAddition(int first, int second, int expected) {
        assertThat(new Score(first).add(new Score(second))).isEqualTo(new Score(expected));
    }

    @DisplayName("Test of illegal addition")
    @ParameterizedTest
    @CsvSource(value = {
            "10:21",
            "1:30"
    }, delimiter = ':')
    void testIllegalAddition(int first, int second) {
        assertThatThrownBy(() -> new Score(first).add(new Score(second))).isInstanceOf(IllegalArgumentException.class);
    }
}
