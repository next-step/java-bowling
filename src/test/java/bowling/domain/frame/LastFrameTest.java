package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LastFrameTest {
    private LastFrame lastFrame;
    @BeforeEach
    void setUp() {
        lastFrame = new LastFrame(new ArrayList<>());
    }

    @DisplayName("마지막 프레임 생성")
    @Test
    void create() {
        assertThatCode(() -> new LastFrame(new ArrayList<>()));
    }

    @DisplayName("2구 이내 스트라이크 또는 스페어 인 경우 3구 실행")
    @ParameterizedTest
    @MethodSource("points")
    void addPoint(List<Integer> values) {
        lastFrame = new LastFrame(new ArrayList<>());

        assertThatCode(
                () -> {
                    for (Integer value : values) {
                        lastFrame.addScore(value);
                    }
                }
        );
    }

    static Stream<Arguments> points() {
        return Stream.of(
                arguments(Arrays.asList(10, 2, 1)),
                arguments(Arrays.asList(1, 9, 10)),
                arguments(Arrays.asList(4, 6, 10))
        );
    }

    @DisplayName("1구가 스트라이크가 아닌경우 1구와 2구의 합은 10점을 넘을 수 없다.")
    @ParameterizedTest
    @MethodSource("overPoints")
    void addPointFailByPointOver(List<Integer> values) {
        lastFrame = new LastFrame(new ArrayList<>());

        assertThatIllegalArgumentException().isThrownBy(
                () -> {
                    for (Integer value : values) {
                        lastFrame.addScore(value);
                    }
                }
        );
    }

    static Stream<Arguments> overPoints() {
        return Stream.of(
                arguments(Arrays.asList(2, 9)),
                arguments(Arrays.asList(1, 10))
        );
    }

    @DisplayName("볼링공을 던질수 있는 상태")
    @Test
    void isPlayable() {
        lastFrame = new LastFrame(new ArrayList<>());
        lastFrame.addScore(1);

        assertThat(lastFrame.isPlayable()).isTrue();
    }

    @DisplayName("3구째 볼링공을 던질수 있는 상태 - 2구 이내 스트라이크 또는 스페어 인 경우")
    @ParameterizedTest
    @MethodSource("strikeOrSparePoints")
    void isPlayableByStrikteOrSpare(List<Integer> values) {
        lastFrame = new LastFrame(new ArrayList<>());
        for (Integer value : values) {
            lastFrame.addScore(value);
        }

        assertThat(lastFrame.isPlayable()).isTrue();
    }

    static Stream<Arguments> strikeOrSparePoints() {
        return Stream.of(
                arguments(Arrays.asList(10, 2)),
                arguments(Arrays.asList(1, 9)),
                arguments(Arrays.asList(4, 6))
        );
    }
}
