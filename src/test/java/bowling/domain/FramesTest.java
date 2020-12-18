package bowling.domain;

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
        frames = new Frames();
    }

    @DisplayName("생성")
    @Test
    void construct() {
        assertThat(frames).isNotNull().isInstanceOf(Frames.class);
    }

    @DisplayName("사이즈 구하기")
    @Test
    void size() {
        assertThat(frames.size()).isEqualTo(10);
    }

    @Test
    void name() {
        frames.getFrames().forEach(System.out::println);
    }
}
