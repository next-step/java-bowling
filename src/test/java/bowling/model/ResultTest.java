package bowling.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ResultTest {
    @ParameterizedTest
    @ValueSource(ints = { Integer.MIN_VALUE, -1, Result.MAX_PIN_COUNT + 1, Result.MAX_PIN_COUNT + 2, Integer.MAX_VALUE })
    public void of_ShouldThrow(int count) {
        assertThatIllegalArgumentException().isThrownBy(() -> Result.of(count));
    }

    @Test
    public void spare() {
        assertThatIllegalArgumentException().isThrownBy(() -> Result.spare(0));
        assertThatIllegalArgumentException().isThrownBy(() -> Result.spare(Result.MAX_PIN_COUNT));
    }

    @ParameterizedTest
    @CsvSource(value = { "0:-", "1:1", "2:2", "3:3", "4:4", "5:5", "6:6", "7:7", "8:8", "9:9", "10:X" }, delimiter = ':')
    public void toString(int count, String excepted) {
        assertThat(Result.of(count).toString()).isEqualTo(excepted);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9 })
    public void toString_Spare(int count) {
        assertThat(Result.spare(count).toString()).isEqualTo(Result.SPARE_STRING);
    }
}
