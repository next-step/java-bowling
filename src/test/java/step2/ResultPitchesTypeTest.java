package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import step2.type.ResultPitchesType;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ResultPitchesTypeTest {

    @DisplayName("getType 테스트")
    @Test
    void getType() {
        assertThat(ResultPitchesType.getType(1,5)).isEqualByComparingTo(ResultPitchesType.MISS);
        assertThat(ResultPitchesType.getType(10,0)).isEqualByComparingTo(ResultPitchesType.STRIKE);
        assertThat(ResultPitchesType.getType(5,5)).isEqualByComparingTo(ResultPitchesType.SPARE);
        assertThat(ResultPitchesType.getType(0,10)).isEqualByComparingTo(ResultPitchesType.SPARE);
        assertThat(ResultPitchesType.getType(10,10)).isEqualByComparingTo(ResultPitchesType.DOUBLE);
    }

    @DisplayName("getType 예외 테스트")
    @ParameterizedTest
    @CsvSource(value = {"11,5", "5,11"})
    void getTypeWithException(int first, int second) {
        assertThatThrownBy(()-> ResultPitchesType.getType(first, second)).isInstanceOf(NoSuchElementException.class);
    }
}
