package bowling.domain.frame.state;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstBowlTest {


    @DisplayName("나머지 공을 모두 처리하면 spare 상태를 리턴 한다.")
    @Test
    public void blow__success_spare() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new Pins(4));

        //when
        State bowl = firstBowl.bowl(4);

        //then
        assertTrue(bowl instanceof Spare);
    }

    @DisplayName("첫 투구 상태는 완료 상태가 아니다")
    @Test
    public void isFinish_success() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new Pins(3));

        //then
        assertFalse(firstBowl.isFinish());
    }
}
