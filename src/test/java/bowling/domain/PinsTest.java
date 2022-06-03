package bowling.domain;

import bowling.domain.pin.FinalFramePins;
import bowling.domain.pin.NormalFramePins;
import bowling.domain.pin.Pins;
import bowling.exception.InvalidHitSizeException;
import bowling.exception.OverflowPinException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    private Pins normalFramePins;
    private Pins finalFramePins;

    @BeforeEach
    void setUp() {
        normalFramePins = new NormalFramePins();
        finalFramePins = new FinalFramePins();
    }

    @Test
    void fallDown_normal() {
        normalFramePins.fallDown(5, false);
        assertThat(normalFramePins).isEqualTo(new NormalFramePins(5));
    }

    @Test
    void fallDown_final() {
        finalFramePins.fallDown(0, false);
        assertThat(finalFramePins).isEqualTo(new FinalFramePins(0));
    }

    @Test
    @DisplayName("두번의 투구의 총 합이 10을 초과하면 예외를 반환한다.")
    void invalidSecondFallDown() {
        assertThatThrownBy(() -> {
            normalFramePins.fallDown(5,false);
            normalFramePins.fallDown(6, false);
        }).isInstanceOf(OverflowPinException.class).hasMessage("10 개를 넘길 수 없습니다. 입력한 Hit 수: 5, 6");

        assertThatThrownBy(() -> {
            finalFramePins.fallDown(5,false);
            finalFramePins.fallDown(6, false);
        }).isInstanceOf(OverflowPinException.class).hasMessage("10 개를 넘길 수 없습니다. 입력한 Hit 수: 5, 6");
    }

    @Test
    @DisplayName("투구는 2번만 던질 수 있다.")
    void invalidThirdFallDown() {
        assertThatThrownBy(() -> {
            normalFramePins.fallDown(5,false);
            normalFramePins.fallDown(1, false);
            normalFramePins.fallDown(0, false);
        }).isInstanceOf(InvalidHitSizeException.class);

        assertThatThrownBy(() -> {
            finalFramePins.fallDown(5,false);
            finalFramePins.fallDown(1, false);
            finalFramePins.fallDown(0, false);
        }).isInstanceOf(InvalidHitSizeException.class);
    }

    @Test
    @DisplayName("일반 프레임의 경우 보너스히트 시 예외를 반환한다.")
    void normalFrameBonusHit() {
        assertThatThrownBy(() -> normalFramePins.fallDown(5,true)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("스트라이크를 치면 한번만 투구가 가능하다.")
    void strike_secondFallDown_normal() {
        assertThatThrownBy(() -> {
            normalFramePins.fallDown(10,false);
            normalFramePins.fallDown(3,false);
        }).isInstanceOf(OverflowPinException.class);
    }

    @Test
    @DisplayName("Strike 일 경우 두번의 보너스 투구를 할 수 있다.")
    void strike_secondFallDown_final() {
        finalFramePins.fallDown(10, false);
        finalFramePins.fallDown(10, true);
        finalFramePins.fallDown(10, true);

        assertThat(finalFramePins).isEqualTo(new FinalFramePins(10, 10, 10));
    }

    @Test
    @DisplayName("spare 일 경우 한번의 보너스 투구를 할 수 있다.")
    void spare() {
        finalFramePins.fallDown(7, false);
        finalFramePins.fallDown(3, false);
        finalFramePins.fallDown(5, true);

        assertThat(finalFramePins).isEqualTo(new FinalFramePins(7, 3, 5));
    }
}