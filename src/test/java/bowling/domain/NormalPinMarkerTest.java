package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalPinMarkerTest {

    NormalPinMarker pinMarker;

    @BeforeEach
    void setUp(){
        pinMarker = new NormalPinMarker();
    }

    @DisplayName("1번째 투구가 10개 핀을 쓰러뜨리면 Strike 다")
    @Test
    void isStrike(){
        pinMarker.mark(PinMark.pin(10));
        assertThat(pinMarker.getState()).isInstanceOf(NormalPinMarker.Strike.class);
    }

    @DisplayName("1,2번째 투구로 10개 핀을 쓰러뜨리면 Spare 다")
    @Test
    void isSpare(){
        pinMarker.mark(PinMark.pin(7));
        pinMarker.mark(PinMark.pin(3));
        assertThat(pinMarker.getState()).isInstanceOf(NormalPinMarker.Spare.class);
        assertThat(pinMarker.getCountOfAllFallDownPins()).isEqualTo(PinMark.MAX_PINS);
    }

    @DisplayName("mark 된 pin 수의 합을 구할 수 있다")
    @Test
    void countOfFallDownPins(){
        pinMarker.mark(PinMark.pin(9));
        pinMarker.mark(PinMark.pin(1));
        assertThat(pinMarker.getCountOfAllFallDownPins()).isEqualTo(10);
    }

    @DisplayName("1번 핀마크가 10이면 Strike(X) 로 표기된다")
    @Test
    void strikeSign(){
        pinMarker.mark(10);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Strike);
    }

    @DisplayName("1,2번 핀마크 합이 10이면 Spare(/) 로 표기된다")
    @Test
    void spareSign(){
        pinMarker.mark(9);
        pinMarker.mark(1);

        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.from(9), PinMarkSymbol.Spare);
    }

    @DisplayName("1번 핀마크가 0 이면 Gutter 로 표기된다")
    @Test
    void gutterSign(){
        pinMarker.mark(0);

        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Gutter);
    }

    @DisplayName("1,2번 핀마크가 모두 0 이면 모두 Gutter 로 표기된다")
    @Test
    void gutterSign2(){
        pinMarker.mark(0);
        pinMarker.mark(0);

        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Gutter, PinMarkSymbol.Gutter);
    }

}