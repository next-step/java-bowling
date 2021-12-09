package bowling.domain.state;

import bowling.domain.frame.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SpareTest {

    @DisplayName("bowl() 메서드는 UnsupportOpeationException 반환한다.")
    @Test
    void bowlFailTest() {
        Spare spare = new Spare(Pin.from(5), Pin.from(5));
        assertThatThrownBy(() ->  spare.bowl(Pin.TEN)).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest(){
        Spare spare = new Spare(Pin.from(5), Pin.from(5));
        assertThat(spare).isEqualTo(new Spare(Pin.from(5), Pin.from(5)));
    }

    @DisplayName("쓰러트린 갯수가 10보다 작으면 illegal Exception")
    @Test
    void createFailTest(){
        assertThatIllegalArgumentException().isThrownBy(() -> new Spare(Pin.from(5), Pin.from(4)));
    }
}
