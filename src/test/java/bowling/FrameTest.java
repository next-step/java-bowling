package bowling;

import bowling.domain.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameTest {
    @DisplayName("0이상 10 이하의 숫자 두 개로 Frame 객체 생성")
    @ParameterizedTest
    @CsvSource(value = {"0:10", "1:5", "8:2"}, delimiter = ':')
    void createTest(int firstPoint, int secondPoint) {
        assertThatCode(() -> {
            new Frame(firstPoint, secondPoint);
        }).doesNotThrowAnyException();
    }

    @DisplayName("첫번째 포인트가 10일 때, 두 번째 포인트가 0이 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void throwExceptionWhenFirstScoreTenAndSecondNotZero(int secondPoint){
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Frame(10, secondPoint);
        });
    }
}