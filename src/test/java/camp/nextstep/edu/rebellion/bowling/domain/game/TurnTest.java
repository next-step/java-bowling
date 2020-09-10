package camp.nextstep.edu.rebellion.bowling.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    @DisplayName("턴이 잘 생성되는지 확인")
    @Test
    public void turnTest() {
        // given
        // expected 0, 1
        int chances = 2;
        Turn turn = Turn.setup(chances);

        // when & then
        assertThat(turn.have()).isZero();

        // and
        turn.next();

        // then
        assertThat(turn.have()).isOne();

        // and
        turn.next();

        // then
        assertThat(turn.have()).isZero();
    }
}
