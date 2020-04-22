package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.finished.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class FinalReadyTest {
    private FinalReady finalReady;

    @BeforeEach
    void setUp() {
        finalReady = FinalReady.of();
    }

    @Test
    @DisplayName("생성 테스트")
    void createReadyTest() {
        assertThatCode(
                () -> FinalReady.of()
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("공던졌을때 스트라이크 테스트")
    void throwBallStrikeTest() {
        assertThat(
                finalReady.throwBall(Point.of(10))
        ).isInstanceOf(FinalCover.class);
    }
}
