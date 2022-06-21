package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramePropertiesTest {
    private FrameProperties frameProperties;

    @BeforeEach
    void setUp() {
        frameProperties = new FrameProperties(1);
    }

    @Test
    void index() {
        assertThat(frameProperties.index()).isEqualTo(1);
    }

    @Test
    void addPins() {
        frameProperties.addPins(4);
        assertThat(frameProperties.pins()).isEqualTo(new FallenPins(List.of(4)));
    }

    @Test
    void computeSumOfFallenPins() {
        frameProperties = new FrameProperties(1, new FallenPins(List.of(1,3)), 0);
        assertThat(frameProperties.computeSumOfFallenPins()).isEqualTo(4);
    }

    @Test
    void minusTryNo() {
        frameProperties.minusTryNo();
        assertThat(frameProperties.tryNo()).isEqualTo(1);
    }
}
