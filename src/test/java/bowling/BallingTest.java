package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BallingTest {

    @Test
    void shouldReturnNextOrCurrentRound(){
        Balling balling = new Balling();

        BallingRound roundA = balling.play(3);
        assertThat(roundA.isSameRound(new BallingRound(1))).isTrue();

        BallingRound roundB = balling.play(7);
        assertThat(roundB.isSameRound(new BallingRound(2))).isTrue();

        BallingRound roundC = balling.play(10);
        assertThat(roundC.isSameRound(new BallingRound(3))).isTrue();

        BallingRound roundD = balling.play(0);
        assertThat(roundD.isSameRound(new BallingRound(3))).isTrue();

    }

}
