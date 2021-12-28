package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TotalHittingPinsTest {

    @Test
    @DisplayName("토탈핀 일급 컬랙션 생성")
    void create() {
        TotalHittingPins total = new TotalHittingPins();
        assertThat(total).isEqualTo(new TotalHittingPins());
    }

    @Test
    @DisplayName("4번 이상 투구할 경우 -> IllegalArgumentException 반환")
    void invalid() {
        List<HittingPins> hittingPins = Arrays.asList(new HittingPins(3), new HittingPins(7), new HittingPins(3));
        TotalHittingPins total = new TotalHittingPins(hittingPins);

        assertThatThrownBy(() -> total.addPins(new HittingPins(3)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("첫번째투구 두번쨰투구가 스페어인지 테스트")
    void isSpare() {
        TotalHittingPins spare = new TotalHittingPins(Arrays.asList(new HittingPins(3), new HittingPins(7)));
        TotalHittingPins noneSpare = new TotalHittingPins(Arrays.asList(new HittingPins(3), new HittingPins(6)));
        assertThat(spare.isSpare()).isTrue();
        assertThat(noneSpare.isSpare()).isFalse();
    }

    @Test
    @DisplayName("연속해서 스트라이크 두번인지 테스트")
    void isTwoStrike() {
        TotalHittingPins strike = new TotalHittingPins(Arrays.asList(new HittingPins(10), new HittingPins(10)));
        TotalHittingPins noneStrike = new TotalHittingPins(Arrays.asList(new HittingPins(7), new HittingPins(10)));
        assertThat(strike.isTwoStrike()).isTrue();
        assertThat(noneStrike.isTwoStrike()).isFalse();
    }

}