package bowling.model;

import bowling.model.frame.FrameNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameNumberTest {

    @ParameterizedTest
    @MethodSource("frameNumberParams")
    void 정상_값(int number, String result) {
        assertThat(FrameNumber.from(number).toString()).isEqualTo(result);
        assertThat(FrameNumber.from(number).toString()).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {11, -1})
    void 비정상_값(int number){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FrameNumber.from(number))
                .withMessage("범위를 벗어난 프레임 수 입니다.");
    }

    private static Stream<Arguments> frameNumberParams(){
        return Stream.of(
                Arguments.of(1,"01"),
                Arguments.of(10,"10")
        );
    }
}
