package bowling.strategy;

import bowling.domain.Pin;
import com.sun.nio.zipfs.ZipInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TestlInputBowlingStrategyTest {

    @DisplayName("이미 스트라이크인경우, 두번째 비어있는 핀정보로 세팅")
    @Test
    void drawBowl() {
        Pin pin = new Pin(10, 10);
        Pin result = new TestInputBowlingStrategy().drawBowl(pin, 0);
        assertThat(result.leftPins()).isEqualTo(0);
        assertThat(result.isAllClear()).isTrue();
    }

    @DisplayName("쓰러진 핀 없는 경우 거터")
    @Test
    void gutter() {
        Pin pin = new Pin(10, 0);
        assertThat(pin.isGutter()).isTrue();
    }

    @DisplayName("쓰러진 핀 1 ~ 9 ")
    @Test
    void miss() {
        Pin pin = new Pin(9, 3);
        Pin result = new TestInputBowlingStrategy().drawBowl(pin, 0);
        assertThat(result.leftPins() > 0).isTrue();
        assertThat(result.leftPins() < 10).isTrue();
    }

}
