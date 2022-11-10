package bowling;

import bowling.domain.Pin;
import bowling.domain.PinGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PinGenratorTest {

    @Test
    void genrate() {
        List<Pin> pins = PinGenerator.generate();
        System.out.println(pins);
        assertThat(pins).hasSize(10);
    }
}
