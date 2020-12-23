package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-16 오전 8:59
 * Developer : Seo
 */
class FinalFrameTest {
    @DisplayName("생성")
    @Test
    void construct() {
//        assertThat(new FinalFrame(new Pins(10))).isNotNull().isInstanceOf(FinalFrame.class);
    }

    @DisplayName("10프레임 보너스 없음")
    @Test
    void bowl_score() {
//        FinalFrame frame = new FinalFrame(new Pins(8));
//        frame.bowl(new Pins(1));
//        assertThat(frame.getScore().get()).isEqualTo(9);
    }

    @DisplayName("10프레임 보너스 - 첫 구 스트라이크")
    @Test
    void bowl_bonus_first_strike() {
//        FinalFrame frame = new FinalFrame(new Pins(10));
//        frame.bowl(new Pins(1));
//        assertThat(frame.getScore().get()).isGreaterThan(11);
    }

    @DisplayName("10프레임 보너스 - 두번째 구 스트라이크")
    @Test
    void bowl_bonus_second_strike() {
//        FinalFrame frame = new FinalFrame(new Pins(1));
//        frame.bowl(new Pins(10));
//        assertThat(frame.getScore().get()).isGreaterThan(11);
    }

    @DisplayName("10프레임 보너스 - 스페어")
    @Test
    void bowl_bonus_spare() {
//        FinalFrame frame = new FinalFrame(new Pins(9));
//        frame.bowl(new Pins(1));
//        assertThat(frame.getScore().get()).isGreaterThan(10);
    }
}
