package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    @DisplayName("index로 해당 Frame찾아 오는지 테스트")
    void frameByIndex() {
        Frames frames = new Frames();
        Frame frame = frames.frameByIndex(0);

        assertThat(frame.getFrameNumber()).isEqualTo(new FrameNumber(1));
    }

    @Test
    @DisplayName("해당 index의 프레임이 더 핀을 쓰러트릴 수 있는 지 확인한다.")
    void isFallDownAble() {
        Pins pins = new Pins(Collections.singletonList(new Pin(10)));
        Frame fallDownNotAbleFrame = new Frame(2, pins);
        List<Frame> frameList = Arrays.asList(new Frame(1), fallDownNotAbleFrame);
        Frames frames = new Frames(frameList);

        assertThat(frames.isFallDownAble(0)).isTrue();
        assertThat(frames.isFallDownAble(1)).isFalse();
    }

    @Test
    @DisplayName("해당 index의 프레임의 핀을 쓰러 트린다.")
    void fallDown() {
        List<Frame> frameList = Arrays.asList(new Frame(1), new Frame(2));
        Frames frames = new Frames(frameList);
        int index = 0;
        int pin = 5;
        frames.fallDown(index, pin);

        assertThat(frameList.get(index).getScore()).isEqualTo(pin);
    }

    @ParameterizedTest
    @MethodSource(value = "provideFrameAndScore")
    @DisplayName("해당 index의 프레임의 score을 구한다.")
    void getScore(List<Frame> frameList, int index, int expectScore) {
        Frames frames = new Frames(frameList);
        assertThat(frames.getScore(index)).isEqualTo(expectScore);
    }

    static Stream<Arguments> provideFrameAndScore() {
        Frame strikeFrame = new Frame(1, new Pins(Collections.singletonList(new Pin(10))));
        Frame spareFrame = new Frame(1, new Pins(Arrays.asList(new Pin(5), new Pin(5))));
        Frame firstFallDown = new Frame(2, new Pins(Arrays.asList(new Pin(5), new Pin())));
        Frame secondFallDown = new Frame(2, new Pins(Arrays.asList(new Pin(5), new Pin(4))));

        return Stream.of(
                Arguments.of(Collections.singletonList(firstFallDown), 0, -1),
                Arguments.of(Arrays.asList(secondFallDown, firstFallDown), 0, 9),
                Arguments.of(Arrays.asList(strikeFrame, firstFallDown), 0, -1),
                Arguments.of(Arrays.asList(strikeFrame, secondFallDown), 0, 19),
                Arguments.of(Arrays.asList(spareFrame, firstFallDown), 0, 15)
        );
    }
}
