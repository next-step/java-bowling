package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class FrameTest {
    @DisplayName("10 프레임이면 FinalFrame 을 리턴하고, 아니라면 NormalFrame 을 리턴한다.")
    @ParameterizedTest
    @MethodSource("create")
    void create(int frameNumber, Class expected) {
        assertThat(Frame.create(frameNumber)).isInstanceOf(expected);
    }

    @DisplayName("투구가 종료될 때까지 투구를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void start(int pitchCount) {
        Frame frame = new Frame(3) {
            @Override
            protected boolean isEnd(int pitchNumber, int remainPinCount) {
                return pitchNumber > pitchCount;
            }

            @Override
            protected int getRemainPinCount(int remainPinCount, Pitch pitch) {
                return 0;
            }
        };
        
        assertThat(frame.start().getPitches()).hasSize(pitchCount);
    }

    private static Stream create() {
        return Stream.of(
                Arguments.arguments(1, NormalFrame.class),
                Arguments.arguments(2, NormalFrame.class),
                Arguments.arguments(3, NormalFrame.class),
                Arguments.arguments(4, NormalFrame.class),
                Arguments.arguments(5, NormalFrame.class),
                Arguments.arguments(6, NormalFrame.class),
                Arguments.arguments(7, NormalFrame.class),
                Arguments.arguments(8, NormalFrame.class),
                Arguments.arguments(9, NormalFrame.class),
                Arguments.arguments(10, FinalFrame.class)
        );
    }
}
