package bowling;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BallingRoundTest {

    @Test
    void shouldValidateBallingRound(){
        assertThatThrownBy(()->new BallingRound(-1))
                .isInstanceOf(IllegalArgumentException.class);

     }

    @Test
    void isSameRound() {
        assertThat(new BallingRound(5).isSameRound(new BallingRound(5))).isTrue();
        assertThat(new BallingRound(5).isSameRound(new BallingRound(4))).isFalse();
      }

    @Test
    void addKnockDownPins() {
        BallingRound round = new BallingRound(1);
        BallingRound roundA = round.addKnockDownPins(10);
        assertThat(roundA.isSameRound(new BallingRound(2))).isTrue();

        BallingRound roundB = roundA.addKnockDownPins(10);
        assertThat(roundB.isSameRound(new BallingRound(3))).isTrue();
    }


}
