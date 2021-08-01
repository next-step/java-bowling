package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링게임에서 마지막 프레임을 표현하는 클래스 테스트")
class LastFrameTest {

    @DisplayName("초기화에 필요한 정보는 없다")
    @Test
    void init() {
        assertThat(LastFrame.init()).isInstanceOf(LastFrame.class);
    }

}
