package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    @DisplayName("볼링이 종료되기 전까지는 계속 볼링을 칠 수 있게 true가 나온다.")
    void test1() {
        Frames frames = new Frames();
        assertThat(frames.bowl(Point.of(10))).isTrue();
    }

    @Test
    @DisplayName("볼링이 종료되면 전까지는 볼링을 칠 수 없게 false가 나온다.")
    void test2() {
        Frames frames = new Frames();
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        assertThat(frames.bowl(Point.of(10))).isFalse();
    }

    @Test
    @DisplayName("10, 10을 입력하면 10, 10만을 포함한 리스트 결과가 나온다.")
    void test3() {
        Frames frames = new Frames();
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        assertThat(frames.getPointsOfFrames())
                .containsExactly(List.of(Point.of(10)), List.of(Point.of(10)));
    }

    @Test
    @DisplayName("10, 10, 10을 입력하면 30만을 포함한 리스트 결과가 나온다.")
    void test4() {
        Frames frames = new Frames();
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        frames.bowl(Point.of(10));
        assertThat(frames.getTotalPointsOfFrames())
                .containsExactly(Point.of(30));
    }

}
