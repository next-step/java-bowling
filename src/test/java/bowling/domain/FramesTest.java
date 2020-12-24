package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-17 오전 8:06
 * Developer : Seo
 */
class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("생성")
    @Test
    void construct() {
        assertThat(frames).isNotNull().isInstanceOf(Frames.class);
    }

    @DisplayName("초기화")
    @Test
    void init() {
    }
}
