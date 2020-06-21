package bowling.domain;

import bowling.domain.exceptions.TooManyFrameStatusesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameStatusesTests {
    @DisplayName("FrameStatus 리스트를 전달받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        List<FrameStatus> frameStatusList = Arrays.asList(FrameStatus.ONE, FrameStatus.TWO);
        assertThat(new FrameStatuses(frameStatusList)).isEqualTo(new FrameStatuses(frameStatusList));
    }

    @DisplayName("size가 4 이상인 리스트로는 생성할 수 없다.")
    @ParameterizedTest
    @MethodSource("biggerThanFourResource")
    void createValidationTest(List<FrameStatus> biggerThanFour) {
        assertThatThrownBy(() -> new FrameStatuses(biggerThanFour))
                .isInstanceOf(TooManyFrameStatusesException.class);
    }
    public static Stream<List<FrameStatus>> biggerThanFourResource() {
        return Stream.of(
                Arrays.asList(FrameStatus.ONE, FrameStatus.ONE, FrameStatus.ONE, FrameStatus.ONE),
                Arrays.asList(FrameStatus.ONE, FrameStatus.ONE, FrameStatus.ONE, FrameStatus.ONE, FrameStatus.ONE)
        );
    }
}
