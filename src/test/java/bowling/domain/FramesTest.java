package bowling.domain;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
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

        assertThat(frame.getFrameNumber()).isEqualTo(FrameNumber.of(1));
    }

    @Test
    @DisplayName("해당 index의 프레임이 더 핀을 쓰러트릴 수 있는 지 확인한다.")
    void isFallDownAble() {
        Frame fallDownNotAbleFrame = new Frame(2, new Strike());
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

        assertThat(frameList.get(index).getScore(0)).isEqualTo((new FirstBowl(5)).getScore(0));
    }

    @ParameterizedTest
    @MethodSource(value = "provideFrameAndScore")
    @DisplayName("해당 index의 프레임의 score을 구한다.")
    void getScore(List<Frame> frameList, int index, int expectScore) {
        Frames frames = new Frames(frameList);
        assertThat(frames.getScore(index).getScore()).isEqualTo(expectScore);
    }

    static Stream<Arguments> provideFrameAndScore() {
        Frame strikeFrame = new Frame(1, new Strike());
        Frame spareFrame = new Frame(1, new Spare(5, 5));
        Frame firstFallDown = new Frame(2, new FirstBowl(5));
        Frame secondFallDown = new Frame(2, new Miss(5, 4));

        return Stream.of(
                Arguments.of(Collections.singletonList(firstFallDown), 0, -1),
                Arguments.of(Arrays.asList(secondFallDown, firstFallDown), 0, 9),
                Arguments.of(Arrays.asList(strikeFrame, firstFallDown), 0, -1),
                Arguments.of(Arrays.asList(strikeFrame, secondFallDown), 0, 19),
                Arguments.of(Arrays.asList(spareFrame, firstFallDown), 0, 15)
        );
    }
}
