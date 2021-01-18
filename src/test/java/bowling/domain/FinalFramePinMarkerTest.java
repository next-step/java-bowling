package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalPinMarkerTest {

    FinalPinMarker pinMarker;

    @BeforeEach
    void setUp(){
        pinMarker = new FinalPinMarker();
    }

    @DisplayName("2번의 투구로 마크된 핀수가 10개 미만이면 보너스 마크를 할 수 없다")
    @Test
    void extraMark1(){
        pinMarker.mark(8);
        pinMarker.mark(1);

        assertThatThrownBy( () -> pinMarker.mark(9))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("보너스 마크를 포함해서 최대 3번까지 마크 할 수 있다. 그 이상 마크하면 exception 을 던진다")
    @Test
    void tooManyMarks(){
        pinMarker.mark(8);
        pinMarker.mark(2);
        pinMarker.mark(1);

        assertThatThrownBy( () -> pinMarker.mark(PinMark.pin(9)))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하지 못하면 complete 된다")
    @Test
    void allMarked1(){
        pinMarker.mark(PinMark.pin(8));
        pinMarker.mark(PinMark.pin(1));

        assertThat(pinMarker.isCompleted()).isTrue();
    }

    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하고 보너스까지 마크하면 complete 된다")
    @Test
    void allMarked2(){
        pinMarker.mark(PinMark.pin(8));
        pinMarker.mark(PinMark.pin(2));
        pinMarker.mark(PinMark.pin(5));

        assertThat(pinMarker.isCompleted()).isTrue();
    }


    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하면 보너스 마크가 남기때문에 complete 되지 않는다")
    @Test
    void notAllMarked(){
        pinMarker.mark(PinMark.pin(8));
        pinMarker.mark(PinMark.pin(2));

        assertThat(pinMarker.isCompleted()).isFalse();
    }

    @DisplayName("1번째,2번째 모두 10개씩 mark 하면 보너스 마크가 남기때문에 complete 되지 않는다")
    @Test
    void notAllMarked2(){
        pinMarker.mark(PinMark.pin(10));
        pinMarker.mark(PinMark.pin(10));

        assertThat(pinMarker.isCompleted()).isFalse();
    }

    @DisplayName("1번 핀마크가 8이면 8 로 표기된다")
    @Test
    void second(){
        pinMarker.mark(8);

        assertThat(pinMarker.getState()).isInstanceOf(FinalPinMarker.Second.class);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.from(8));
    }

    @DisplayName("1번,2번 핀마크가 8, 1이면 8, 1 로 표기된다")
    @Test
    void miss(){
        pinMarker.mark(8);
        pinMarker.mark(1);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.from(8), PinMarkSymbol.from(1));
    }

    @DisplayName("1번 핀마크가 10이면 Strike(X) 로 표기된다")
    @Test
    void bonusOne(){
        pinMarker.mark(10);
        assertThat(pinMarker.getState()).isInstanceOf(FinalPinMarker.BonusOne.class);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Strike);
    }

    @DisplayName("1번,2번 핀마크가 10이면 Strike,Strike 로 표기된다")
    @Test
    void doubleStrike(){
        pinMarker.mark(10);
        pinMarker.mark(10);

        assertThat(pinMarker.getState()).isInstanceOf(FinalPinMarker.BonusTwo.class);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Strike, PinMarkSymbol.Strike);
    }

    @DisplayName("1,2,3번 핀마크가 10이면 Strike,Strike,Strike 로 표기된다")
    @Test
    void turkey(){
        pinMarker.mark(10);
        pinMarker.mark(10);
        pinMarker.mark(10);

        assertThat(pinMarker.getState()).isInstanceOf(FinalPinMarker.Turkey.class);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Strike, PinMarkSymbol.Strike, PinMarkSymbol.Strike);
    }

    @DisplayName("1번 핀마크가 10이고 2,3번 핀마크 합이 10이면 Strike, Spare 로 표기된다")
    @Test
    void strikeSpare(){
        pinMarker.mark(10);
        pinMarker.mark(9);
        pinMarker.mark(1);

        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Strike, PinMarkSymbol.from(9), PinMarkSymbol.Spare);
    }

    @DisplayName("1번 핀마크가 10이고 2,3번 핀마크 합이 10 미만이면 Strike, Miss 로 표기된다")
    @Test
    void strikeMiss(){
        pinMarker.mark(10);
        pinMarker.mark(8);
        pinMarker.mark(1);

        assertThat(pinMarker.getState()).isInstanceOf(FinalPinMarker.StrikeMiss.class);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Strike, PinMarkSymbol.from(8), PinMarkSymbol.from(1));
    }

    @DisplayName("1,2번 핀마크 합이 10이면 Spare(/) 로 표기된다")
    @Test
    void spare(){
        pinMarker.mark(9);
        pinMarker.mark(1);

        assertThat(pinMarker.getState()).isInstanceOf(FinalPinMarker.Bonus.class);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.from(9), PinMarkSymbol.Spare);
    }

    @DisplayName("1,2번 핀마크 합이 10이고 3번 Strike 면 Strike, Spare 로 표기된다")
    @Test
    void spareStrike(){
        pinMarker.mark(9);
        pinMarker.mark(1);
        pinMarker.mark(10);

        assertThat(pinMarker.getState()).isInstanceOf(FinalPinMarker.SpareStrike.class);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.from(9), PinMarkSymbol.Spare, PinMarkSymbol.Strike);
    }

    @DisplayName("1,2번 핀마크 합이 10이고 3번이 Miss 면 Strike, Miss 로 표기된다")
    @Test
    void spareMiss(){
        pinMarker.mark(9);
        pinMarker.mark(1);
        pinMarker.mark(8);

        assertThat(pinMarker.getState()).isInstanceOf(FinalPinMarker.SpareMiss.class);
        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.from(9), PinMarkSymbol.Spare, PinMarkSymbol.from(8));
    }

    @DisplayName("1번 핀마크가 0 이면 Gutter 로 표기된다")
    @Test
    void gutterSymbol(){
        pinMarker.mark(0);

        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Gutter);
    }

    @DisplayName("1,2번 핀마크가 모두 0 이면 모두 Gutter 로 표기된다")
    @Test
    void gutterSymbol2(){
        pinMarker.mark(0);
        pinMarker.mark(0);

        assertThat(pinMarker.toSymbols())
                .containsExactly(PinMarkSymbol.Gutter, PinMarkSymbol.Gutter);
    }

    @DisplayName("1번 핀마크가 10이 아니면 1,2번 핀마크 합이 10을 넘으면 안된다")
    @Test
    void invalidMark1(){
        pinMarker.mark(8);
        assertThatThrownBy( () -> pinMarker.mark(3) )
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1번 핀마크가 10이면 2,3번 핀마크 합이 10을 넘으면 안된다")
    @Test
    void invalidMark2(){
        pinMarker.mark(10);
        pinMarker.mark(8);

        assertThatThrownBy( () -> pinMarker.mark(3) )
                .isInstanceOf(IllegalArgumentException.class);
    }
}