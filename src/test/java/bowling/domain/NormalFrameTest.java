package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @DisplayName("1~10번 프레임 까지만 등록 가능 하다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void validate_success(int number) throws Exception {
        //then
        new NormalFrame(number);
    }

    @DisplayName("1~10번 외의 프레임 까지만 등록시 exception")
    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 11, 15, 100})
    public void validate_fail(int number) throws Exception {
        //then
        assertThatThrownBy(
                () -> new NormalFrame(number)
        ).isInstanceOf(BowlingException.class);
    }
}
