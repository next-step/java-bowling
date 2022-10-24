package bowling;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BallingRoundTest {

    @Test
    void shouldValidateBallingRound(){

        assertThatThrownBy(()->new BallingRound(-1))
                .isInstanceOf(IllegalArgumentException.class);

     }

}
