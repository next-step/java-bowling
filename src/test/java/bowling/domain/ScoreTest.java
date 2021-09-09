package bowling.domain;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    void test(){
        Frame frame = new NormalFrame(1);

        frame.bowl(Pins.of(8));
        frame.bowl(Pins.of(1));
        frame.next();
        assertThat(frame.getScore()).isEqualTo(Score.of(9, 0));
    }
}
