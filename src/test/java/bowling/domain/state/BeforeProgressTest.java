package bowling.domain.state;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BeforeProgressTest {
    @DisplayName("BeforeProgress 생성한다.")
    @Test
    void BeforeProgress_생성() {
        assertThat(new BeforeProgress()).isNotNull().isInstanceOf(BeforeProgress.class);
    }

    @DisplayName("BeforeProgress 상태에서 스트라이크 할 경우 다음 상태는 'Strike' 가 된다.")
    @Test
    void bowl_다음_상태_Strike() {
        assertThat(new BeforeProgress().bowl(new Pins(10))).isInstanceOf(Strike.class);
    }

    @DisplayName("BeforeProgress 상태에서 스트라이크 못 할 경우 다음 상태는 'FirstBowl' 이 된다.")
    @Test
    void bowl_다음_상태_FirstBowl() {
        assertThat(new BeforeProgress().bowl(new Pins(1))).isInstanceOf(FirstBowl.class);
    }

    @DisplayName("BeforeProgress 상태는 프레임 종료가 아니므로 false 를 반환한다.")
    @Test
    void isFrameEnd_종료_상태_체크() {
        assertThat(new BeforeProgress().isEnd()).isFalse();
    }

    @DisplayName("BeforeProgress 상태는 기호를 빈값으로 반환한다.")
    @Test
    void symbol_기호_체크() {
        assertThat(new BeforeProgress().symbol()).isEmpty();
    }
}