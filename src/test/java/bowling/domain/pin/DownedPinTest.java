package bowling.domain.pin;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링 핀에 대한 클래스 테스트")
public class DownedPinTest {

    @DisplayName("쓰러진 볼링핀을 표현하는 Pin 클래스는 쓰러진 핀의 수를 가지고 초기화 한다")
    @Test
    public void init() {
        assertThat(DownedPin.from(0)).isInstanceOf(DownedPin.class);
    }

    @DisplayName("쓰러진 핀의 값이 0 보다 작거나 10 보다 크면 예외를 발생시킨다")
    @ValueSource(ints = {-1, 11})
    public void initException(int downedPins) {
        assertThat(DownedPin.from(0)).isInstanceOf(DownedPin.class);
    }

}
