package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void create(){
        assertThat(new Score(new Pins(Arrays.asList(1,2))).getType()).isEqualTo(ScoreType.MISS);
    }

}