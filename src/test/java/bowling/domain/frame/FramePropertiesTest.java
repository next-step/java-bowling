package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramePropertiesTest {

    @Test
    void index() {
        assertThat(new FrameProperties(1).index()).isEqualTo(1);
    }
}
