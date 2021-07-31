package bowling.domain.pin;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링 핀에 대한 클래스 테스트")
public class PinTest {

    @DisplayName("쓰러진 볼링핀을 표현하는 Pin 클래스는 쓰러진 핀의 수를 가지고 초기화 한다")
    @Test
    public void init() {
        assertThat(Pin.from(0)).isInstanceOf(Pin.class);
    }

}
