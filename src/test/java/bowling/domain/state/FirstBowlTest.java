package bowling.domain.state;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FirstBowlTest {
    @DisplayName("FirstBowl 생성한다.")
    @Test
    void FirstBowl_생성() {
        assertThat(new FirstBowl(new Pins(3))).isNotNull().isInstanceOf(FirstBowl.class);
    }

    @DisplayName("FirstBowl 생성 시 첫번째 투구에서 10개의 핀을 쓰러트릴 경우 예외가 발생한다.")
    @Test
    void FirstBowl_생성_예외() {
        Pins pins = new Pins(10);
        assertThatThrownBy(() -> new FirstBowl(pins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫번째 투구와 두번째 투구에서 쓰러트린 핀이 10을 넘어서는 경우 예외가 발생한다.")
    @Test
    void bowl_투구_예외() {
        FirstBowl firstBowl = new FirstBowl(new Pins(8));
        Pins pins = new Pins(3);
        assertThatThrownBy(() -> firstBowl.bowl(pins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두번째 투구에서 스페어 처리 한 경우 다음 상태는 'Spare' 이다.")
    @Test
    void bowl_투구_다음상태_Spare() {
        FirstBowl firstBowl = new FirstBowl(new Pins(8));
        FrameState bowl = firstBowl.bowl(new Pins(2));
        assertThat(bowl).isInstanceOf(Spare.class);
    }

    @DisplayName("두번째 투구에서 스페어 처리 못 한 경우 다음 상태는 'Miss' 이다.")
    @Test
    void bowl_투구_다음상태_Miss() {
        FirstBowl firstBowl = new FirstBowl(new Pins(8));
        FrameState bowl = firstBowl.bowl(new Pins(1));
        assertThat(bowl).isInstanceOf(Miss.class);
    }

    @DisplayName("FirstBowl 상태는 프레임 종료가 아니므로 false 를 반환한다.")
    @Test
    void isFrameEnd_종료_상태_체크() {
        FirstBowl firstBowl = new FirstBowl(new Pins(8));
        assertThat(firstBowl.isEnd()).isFalse();
    }

    @DisplayName("FirstBowl 상태는 쓰러트린 핀이 0일 경우 '-' 기호를 반환하고, 1 ~ 9개를 쓰러트릴 경우 쓰러트린 숫자를 기호로 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0, -",
            "1, 1",
            "8, 8",
            "9, 9"
    })
    void symbol_기호_체크(int hitPins, String symbol) {
        assertThat(new FirstBowl(new Pins(hitPins)).symbol()).isEqualTo(symbol);
    }
}