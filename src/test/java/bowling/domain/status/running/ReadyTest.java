package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.finished.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class ReadyTest {
    private Ready ready;

    @BeforeEach
    void setUp() {
        ready = Ready.of();
    }

    @Test
    @DisplayName("생성 테스트")
    void createReadyTest() {
        assertThatCode(
                () -> Ready.of()
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("공던졌을때 스트라이크 테스트")
    void throwBallStrikeTest() {
        assertThat(
                ready.throwBall(Point.of(10))
        ).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("공던졌을때 커버 테스트")
    void throwBallCoverTest() {
        assertThat(
                ready.throwBall(Point.of(8))
        ).isInstanceOf(Cover.class);
    }
}
