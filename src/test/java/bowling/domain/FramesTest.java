package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames(2);
    }


    @DisplayName("프레임 갯수는 최소 2 이상이여야 한다")
    @Test
    void minError() {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> new Frames(1));
    }
}
