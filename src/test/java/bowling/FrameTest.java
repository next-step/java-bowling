package bowling;

import bowling.domain.Frame;
import bowling.domain.RandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

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

    @DisplayName("첫번째 포인트와 두번째 포인트의 합이 10 초과이면 예외 발생")
    @ParameterizedTest
    @CsvSource(value = {"8:3", "9:2", "10:9"}, delimiter = ':')
    void throwExceptionWhenSumIsGreaterThanTen(int firstPoint, int secondPoint){
        assertThatIllegalArgumentException().isThrownBy(() -> {
           new Frame(firstPoint, secondPoint);
        });
    }

    @DisplayName("RandomGenerator를 이용해 Frame 객체 생성")
    @Test
    void createWithRandomGenerator(){
        RandomGenerator randomGenerator = new RandomGenerator();

        IntStream.range(0, 1000)
                .forEach(it -> assertThatCode(() -> {
                    Frame.create(randomGenerator);
                }).doesNotThrowAnyException());
    }
}