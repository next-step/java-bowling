package bowling.domain.frame;

import bowling.domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrame2Test {
    private Frame2 frame;

    @BeforeEach
    void setUp() {
        frame = FinalFrame2.of();
    }

    @DisplayName("스페이일 경우 테스트")
    @Test
    void spare() {
        Point point = Point.of(5);

        boolean lastRoll = frame.roll(point)
                .roll(point)
                .roll(point)
                .isLastRoll();

        assertThat(lastRoll).isTrue();
    }

    @DisplayName("스트라이크일 경우 테스트")
    @Test
    void strike() {
        Point point = Point.of(10);

        boolean lastRoll = frame.roll(point)
                .roll(point)
                .roll(point)
                .isLastRoll();

        assertThat(lastRoll).isTrue();
    }
}
