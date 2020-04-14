package bowling.domain.turn;

import bowling.domain.Pins;
import bowling.domain.Result;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnTest {

    @DisplayName("공을 던져 전달받은 수 만큼의 핀을 넘어뜨리면 "
            + "점수 할당, 상태 변경, 남은 핀의 상태를 변경 한다")
    @Test
    public void bowl_success() throws Exception {
        //given
        Turn init = Turn.from();
        Turn expect = new Turn(new Score(3), Result.MISS, new Pins(7), TurnState.SECOND);

        //when
        init = init.bowl(3);

        //then
        assertTrue(init.equals(expect));
    }
}
