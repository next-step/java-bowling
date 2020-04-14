package bowling.domain.turn;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnTest {

    @DisplayName("턴 생성시 공을 던진 상태로 생성 한다")
    @Test
    public void from_success() throws Exception {
        //given
        Turn init = Turn.from(3);
        Turn expect = new Turn(new Score(3), new Pins(7));

        //then
        assertTrue(init.equals(expect));
    }

    @DisplayName("공을 던져 전달받은 수 만큼의 핀을 넘어뜨리면 "
            + "점수 할당, 상태 변경, 남은 핀의 상태를 변경 한다")
    @Test
    public void bowl_success() throws Exception {
        //given
        Turn init = Turn.from(3);
        Turn expect = new Turn(new Score(6), new Pins(4));

        //when
        init = init.bowl(3);

        //then
        assertTrue(init.equals(expect));
    }

    @DisplayName("남은 핀이 없으면 완료(true)로 응답")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Turn max = new Turn(new Score(1), new Pins(10));
        Turn min = new Turn(new Score(1), new Pins(0));

        //when

        //then
        assertFalse(max.isFinish());
        assertTrue(min.isFinish());
    }
}
