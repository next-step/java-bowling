package bowling.domain.turn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnsTest {

    @DisplayName("공을 굴려 새로운 턴을 추가하고 반환한다")
    @Test
    public void bowl_success() throws Exception {
        //given
        Turns turns = new Turns();
        Turns expect = new Turns(Arrays.asList(
                Turn.from(3)));

        //when
        turns = turns.bowl(3);

        //then
        assertTrue(turns.equals(expect));
    }

    @DisplayName("턴이 2회보다 많을 경우 true, 아니면 false")
    @Test
    public void isMoreTwice() throws Exception {
        //given
        Turns less = new Turns(Arrays.asList(Turn.from(3), Turn.from(3)));
        Turns more = new Turns(Arrays.asList(Turn.from(3), Turn.from(3), Turn.from(3)));

        //then
        assertFalse(less.isMoreTwice());
        assertTrue(more.isMoreTwice());
    }
}
