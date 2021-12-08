package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MissTest {

    @DisplayName("bowl() 메서드는 UnsupportOpeationException 반환한다.")
    @Test
    void bowlFailTest() {
        Miss miss = new Miss(Pin.from(1), Pin.from(2));
        assertThatThrownBy(() ->  miss.bowl(Pin.TEN)).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest(){
        Miss miss = new Miss(Pin.from(1), Pin.from(2));
        assertThat(miss).isEqualTo(new Miss(Pin.from(1), Pin.from(2)));
    }

    @DisplayName("쓰러트린 갯수가 10개이면 illegal Exception")
    @Test
    void createFailTest(){
        assertThatIllegalArgumentException().isThrownBy(() -> new Miss(Pin.from(5), Pin.from(5)));
    }
}
