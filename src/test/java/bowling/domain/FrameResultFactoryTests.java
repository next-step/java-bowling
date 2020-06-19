package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultFactoryTests {
    @DisplayName("첫번째 투구로 맞춘 핀의 수에 따라 알맞는 FrameResult를 반환한다.")
    @ParameterizedTest
    @MethodSource("factoryArguments")
    void createTest(int numberOfHitPin, boolean completeResult, boolean strikeResult) {
        FrameResult frameResult = FrameResultFactory.create(numberOfHitPin);

        assertThat(frameResult.isCompleted()).isEqualTo(completeResult);
        assertThat(frameResult.isCompleted()).isEqualTo(strikeResult);
    }
    public static Stream<Arguments> factoryArguments() {
        return Stream.of(
                Arguments.of(10, true, true),
                Arguments.of(5, false, false),
                Arguments.of(0, false, false)
        );
    }
}
