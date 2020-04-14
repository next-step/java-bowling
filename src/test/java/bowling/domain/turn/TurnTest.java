package bowling.domain.turn;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnTest {

    @DisplayName("턴 생성시 공을 던진 상태로 생성 한다")
    @Test
    public void from_success() throws Exception {
        //given
        Turn init = Turn.from(3);
        Turn expect = new Turn(new Score(3), new Pins(7), TurnState.FIRST);

        //then
        assertTrue(init.equals(expect));
    }

    @DisplayName("공을 던져 전달받은 수 만큼의 핀을 넘어뜨리면 "
            + "점수 할당, 상태 변경, 남은 핀의 상태를 변경 한다")
    @Test
    public void bowl_success() throws Exception {
        //given
        Turn init = Turn.from(3);
        Turn expect = new Turn(new Score(6), new Pins(4), TurnState.SECOND);

        //when
        init = init.bowl(3);

        //then
        assertTrue(init.equals(expect));
    }
}
