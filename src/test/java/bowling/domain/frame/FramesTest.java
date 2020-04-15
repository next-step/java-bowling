package bowling.domain.frame;

import bowling.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FramesTest {
    private static final String NOT_ADDABLE_FRAME = "";
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = frames.of(new Player("LDC"));
    }

    @DisplayName("프레임 리스트 생성")
    @Test
    void create() {
        assertThatCode(() -> Frames.of(new Player("LDC")));
    }

    @DisplayName("프레임 추가")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void add(int size) {
        for (int i = 0; i < size; i++) {
            frames.add(DefaultFrame.first());
        }

        assertThat(frames.size()).isEqualTo(size);
    }


    @DisplayName("10 프레임일 경우 추가 불가능")
    @Test
    void addFailByFrameCount() {
        for (int i = 0; i < 10; i++) {
            frames.add(DefaultFrame.first());
        }

        assertThat(frames.isOver()).isTrue();
    }

    @DisplayName("프레임 점수 계산")
    @ParameterizedTest
    @MethodSource("defaultPoint")
    void framePoint(List<Integer> values) {
        DefaultFrame first = DefaultFrame.first();
        frames.add(first);
        first.addScore(values.get(0));
        assertThat(frames.calculateFramePoint(0)).isEqualTo("");

        first.addScore(values.get(1));
        assertThat(frames.calculateFramePoint(0)).isEqualTo(values.get(2).toString());
    }

    static Stream<Arguments> defaultPoint() {
        return Stream.of(
                arguments(Arrays.asList(1, 2, 3)),
                arguments(Arrays.asList(5, 4, 9)),
                arguments(Arrays.asList(2, 6, 8))
        );
    }

    @DisplayName("스트라이크 프레임 점수 계산")
    @ParameterizedTest
    @MethodSource("strikePoint")
    void strikeFramePoint(List<Integer> points, List<String> results) {
        DefaultFrame first = DefaultFrame.first();
        frames.add(first);
        first.addScore(points.get(0));
        assertThat(frames.calculateFramePoint(0)).isEqualTo(NOT_ADDABLE_FRAME);

        DefaultFrame nextFrame = first.createNextFrame(0);
        frames.add(nextFrame);
        nextFrame.addScore(points.get(1));
        assertThat(frames.calculateFramePoint(0)).isEqualTo(NOT_ADDABLE_FRAME);
        assertThat(frames.calculateFramePoint(1)).isEqualTo(NOT_ADDABLE_FRAME);

        nextFrame.addScore(points.get(2));
        assertThat(frames.calculateFramePoint(0)).isEqualTo(results.get(0));
        assertThat(frames.calculateFramePoint(1)).isEqualTo(results.get(1));
    }

    static Stream<Arguments> strikePoint() {
        return Stream.of(
                arguments(
                        Arrays.asList(10, 2, 3),
                        Arrays.asList("15", "20")
                ),
                arguments(
                        Arrays.asList(10, 5, 4),
                        Arrays.asList("19", "28")
                ),
                arguments(
                        Arrays.asList(10, 6, 1),
                        Arrays.asList("17", "24")
                )
        );
    }

    @DisplayName("스페어 프레임 점수 계산")
    @ParameterizedTest
    @MethodSource("sparePoint")
    void spareFramePoint(List<Integer> points, List<String> results) {
        DefaultFrame first = DefaultFrame.first();
        frames.add(first);
        first.addScore(points.get(0));
        assertThat(frames.calculateFramePoint(0)).isEqualTo(NOT_ADDABLE_FRAME);

        first.addScore(points.get(1));
        assertThat(frames.calculateFramePoint(0)).isEqualTo(NOT_ADDABLE_FRAME);

        DefaultFrame nextFrame = first.createNextFrame(0);
        frames.add(nextFrame);
        nextFrame.addScore(points.get(2));
        assertThat(frames.calculateFramePoint(0)).isEqualTo(results.get(0));
        assertThat(frames.calculateFramePoint(1)).isEqualTo(NOT_ADDABLE_FRAME);

        nextFrame.addScore(points.get(3));
        assertThat(frames.calculateFramePoint(1)).isEqualTo(results.get(1));
    }

    static Stream<Arguments> sparePoint() {
        return Stream.of(
                arguments(
                        Arrays.asList(1, 9, 3, 4),
                        Arrays.asList("13", "20")
                ),
                arguments(
                        Arrays.asList(2, 8, 4, 5),
                        Arrays.asList("14", "23")
                ),
                arguments(
                        Arrays.asList(3, 7, 1, 5),
                        Arrays.asList("11", "17")
                )
        );
    }
}