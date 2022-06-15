package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalTest {
    private NormalFrame frame;

    @BeforeEach
    void setUp() {
        frame = new NormalFrame(1);
    }

    @Test
    void scoreGiven_addScore() {
        frame.addScore(3);
        assertThat(frame.scores().first()).isEqualTo(3);
    }

    @Test
    void getScore() {
        assertThat(frame.attempt()).isLessThanOrEqualTo(10);
    }

    // TODO(jack.comeback) : 작성 필요
//    @Test
//    void validateMoveToNext() {
//        frame = new NormalFrame(1, 0);
//    }
}
