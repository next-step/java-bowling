package bowling.domain;

import bowling.domain.exceptions.TooManyFrameResultsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameResultsTests {
    @DisplayName("FrameStatus 리스트를 전달받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        List<FrameResult> frameStatusList = Arrays.asList(FrameResult.ONE, FrameResult.TWO);
        assertThat(new FrameResults(frameStatusList)).isEqualTo(new FrameResults(frameStatusList));
    }

    @DisplayName("size가 4 이상인 리스트로는 생성할 수 없다.")
    @ParameterizedTest
    @MethodSource("biggerThanFourResource")
    void createValidationTest(List<FrameResult> biggerThanFour) {
        assertThatThrownBy(() -> new FrameResults(biggerThanFour))
                .isInstanceOf(TooManyFrameResultsException.class);
    }
    public static Stream<List<FrameResult>> biggerThanFourResource() {
        return Stream.of(
                Arrays.asList(FrameResult.ONE, FrameResult.ONE, FrameResult.ONE, FrameResult.ONE),
                Arrays.asList(FrameResult.ONE, FrameResult.ONE, FrameResult.ONE, FrameResult.ONE, FrameResult.ONE)
        );
    }
}
