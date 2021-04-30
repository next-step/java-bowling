package step4.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step4.domain.PinCount;

import static org.assertj.core.api.BDDAssertions.then;

class FirstBowlTest {

    @Test
    @DisplayName("두 번째 쓰러뜨린 핀 개수를 합한 값이 10일 경우, 스페어 객체를 반환한다.")
    public void bowl_spare() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new PinCount(1));

        //when
        State state = firstBowl.bowl(9);

        then(state instanceof Spare).isTrue();
    }

    @Test
    @DisplayName("두 번째 쓰러뜨린 핀 개수를 합한 값이 10아 아닐 경우,미스 객체를 반환한다.")
    public void bowl_miss() throws Exception {
        //given
        FirstBowl firstBowl = new FirstBowl(new PinCount(1));

        //when
        State state = firstBowl.bowl(8);

        then(state instanceof Miss).isTrue();
    }
}