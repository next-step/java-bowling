package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void create() {
        // when
        final Frames frames = Frames.of(); 
        
        // then
        assertThat(frames).isNotNull();
    }
}