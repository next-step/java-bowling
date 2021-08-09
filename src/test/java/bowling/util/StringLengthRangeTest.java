package bowling.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringLengthRangeTest {

    @CsvSource({"1,3,,false", "1,3,가,true", "1,3,가나다,true", "1,3,가나다라,false,"})
    @ParameterizedTest
    void test(int minLength, int maxLength, String str, boolean correct) {
        assertThat(
                new StringLengthRange(minLength, maxLength).test(str)
        ).isEqualTo(correct);
    }
}