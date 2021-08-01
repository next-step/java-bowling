package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.Fixture.DOWNED_PINS_2;
import static bowling.domain.Fixture.DOWNED_PINS_5;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링게임에서 마지막 프레임을 표현하는 클래스 테스트")
class LastFrameTest {

    @DisplayName("초기화에 필요한 정보는 없다")
    @Test
    void init() {
        assertThat(LastFrame.init()).isInstanceOf(LastFrame.class);
    }

    @DisplayName("프레임은 기본적으로 볼링 게임이 끝난 상태가 아니다")
    @Test
    void isBowlingEnd() {
        Frame lastFrame = LastFrame.init();

        assertThat(lastFrame.isBowlingEnd()).isFalse();

        lastFrame.downPins(DOWNED_PINS_5);
        lastFrame.downPins(DOWNED_PINS_2);

        assertThat(lastFrame.isBowlingEnd()).isTrue();
    }

}
