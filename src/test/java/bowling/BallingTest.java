package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BallingTest {

    @Test
    void shouldAddRound(){
        Balling balling = new Balling();
        BallingRound roundA = balling.play(3);

        assertThat(roundA).isEqualTo(new BallingRound(1));

        BallingRound roundB = balling.play(7);
        assertThat(roundB).isEqualTo(new BallingRound(2));

    }

}
