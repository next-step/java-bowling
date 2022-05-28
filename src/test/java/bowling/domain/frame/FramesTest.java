package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    Frames frames;

    @BeforeEach
    void beforeEach() {
        this.frames = Frames.init();
    }

    @Test
    @DisplayName("다음 라운드 시작 가능")
    void isNext() {
        this.frames.bowling(10);
        assertThat(this.frames.isNext()).isTrue();
    }

    @Test
    @DisplayName("다음 라운드 시작 불가능")
    void isNextFail() {
        IntStream.range(0, 10)
                .peek(i -> this.frames.bowling(1))
                .forEach(i -> this.frames.bowling(2));

        assertThat(frames.isNext()).isFalse();
    }
}