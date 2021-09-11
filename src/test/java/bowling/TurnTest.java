package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnTest {

    @Test
    @DisplayName("마지막 더블 투구 턴인지 체크")
    void isLastNormalTurnTest() {
        assertThat(new Turn(1).isLastNormalTurn()).isFalse();

        assertThat(new Turn(9).isLastNormalTurn()).isTrue();
    }

    @Test
    @DisplayName("값 증가 테스트")
    void getNextTurn() {
        assertThat(new Turn(1).getNextTurn()).isEqualTo(new Turn(2));
    }
}
