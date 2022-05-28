package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class NormalFrameTest { ;
    private final int firstCount = 7;
    private final int secondCount = 3;
    private final int nextCount = 10;

    @Test
    @DisplayName("1/2차 투구 후 다음 프레임 생성")
    void nextFrame() {
        Frame first = NormalFrame.bowling(8, firstCount).bowling(secondCount);

        assertThat(first.next(nextCount)).isNotEqualTo(first);
    }

    @Test
    @DisplayName("9번째 프레임 종료 후, FinalFrame 생성")
    void finalNext() {
        Frame semiFinal = NormalFrame.bowling(9, firstCount).bowling(secondCount);

        assertThat(semiFinal.next(nextCount)).isInstanceOf(FinalFrame.class);
    }

    @ParameterizedTest(name = "프레임 1/2차 투구 점수 - {index}")
    @MethodSource("createFrameCondition")
    void createFrame(int round, int firstCount, int secondCount, int expected) {
        Frame frame = NormalFrame.bowling(round, firstCount);

        assertAll(() -> assertThat(frame.totalCount()).isEqualTo(firstCount),
                () -> assertThat(frame.bowling(secondCount).totalCount()).isEqualTo(expected));
    }

    private static Stream<Arguments> createFrameCondition() {
        return Stream.of(
                Arguments.of(0, 0, 0, 0),
                Arguments.of(1, 0, 1, 1),
                Arguments.of(2, 1, 2, 3),
                Arguments.of(3, 3, 4, 7),
                Arguments.of(4, 4, 5, 9),
                Arguments.of(5, 5, 5, 10),
                Arguments.of(6, 6, 4, 10),
                Arguments.of(7, 7, 3, 10),
                Arguments.of(8, 0, 10, 10)
        );
    }
}
