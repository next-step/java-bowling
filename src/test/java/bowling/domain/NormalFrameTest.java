package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    @DisplayName("첫 번째 Frame 생성")
    @Test
    void create_first() {
        // when
        final NormalFrame normalFrame = NormalFrame.first();
        
        // then
        assertThat(normalFrame).isNotNull();
    }

    @DisplayName("다음 Frame 생성")
    @Test
    void create_next() {
        // given
        final NormalFrame firstFrame = NormalFrame.first();
        
        // when
        final NormalFrame nextFrame = firstFrame.next();
        
        // then
        assertThat(nextFrame).isNotNull();
    }
}
