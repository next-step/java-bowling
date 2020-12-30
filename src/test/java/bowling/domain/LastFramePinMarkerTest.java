package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LastFramePinMarksTest  {

    LastFramePinMarks pinMarks;

    @BeforeEach
    void setUp(){
        pinMarks = new LastFramePinMarks();
    }

    @DisplayName("2번의 투구로 마크된 핀수가 10개 미만이면 보너스 마크를 할 수 없다")
    @Test
    void extraMark1(){
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(1));

        assertThatThrownBy( () -> pinMarks.mark(PinMark.pin(9)))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("1번째,2번째 투구모두 10개를 mark 하면 보너스 마크를 할 수 있다")
    @Test
    void extraMark2(){
        pinMarks.mark(PinMark.pin(10));
        pinMarks.mark(PinMark.pin(10));
        pinMarks.mark(PinMark.pin(10));
    }

    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하면 보너스 마크를 할 수 있다")
    @Test
    void extraMark3(){
        pinMarks.mark(PinMark.pin(5));
        pinMarks.mark(PinMark.pin(5));
        pinMarks.mark(PinMark.pin(10));
    }

    @DisplayName("보너스 마크를 포함해서 최대 3번까지 마크 할 수 있다. 그 이상 마크하면 exception 을 던진다")
    @Test
    void tooManyMarks(){
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(2));
        pinMarks.mark(PinMark.pin(1));

        assertThatThrownBy( () -> pinMarks.mark(PinMark.pin(9)))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하지 못하면 보너스마크는 empty 로 마크되어 allmarked 된다")
    @Test
    void allMarked1(){
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(1));

        assertThat(pinMarks.isAllMarked()).isTrue();
    }

    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하고 보너스까지 마그하면 allmarked 된다")
    @Test
    void allMarked2(){
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(2));
        pinMarks.mark(PinMark.pin(5));

        assertThat(pinMarks.isAllMarked()).isTrue();
    }


    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하면 보너스 마크가 남기때문에 allmarked 되지 않는다")
    @Test
    void notAllMarked(){
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(2));

        assertThat(pinMarks.isAllMarked()).isFalse();
    }

    @DisplayName("1번째,2번째 모두 10개씩 mark 하면 보너스 마크가 남기때문에 allmarked 되지 않는다")
    @Test
    void notAllMarked2(){
        pinMarks.mark(PinMark.pin(10));
        pinMarks.mark(PinMark.pin(10));

        assertThat(pinMarks.isAllMarked()).isFalse();
    }

    @DisplayName("1번 핀마크가 8이면 8 로 표기된다")
    @Test
    void numberSign(){
        pinMarks.mark(8);
        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.number(8));
    }

    @DisplayName("1번,2번 핀마크가 8, 1이면 8, 1 로 표기된다")
    @Test
    void numberSign2(){
        pinMarks.mark(8);
        pinMarks.mark(1);
        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.number(8), PinMarkSign.number(1));
    }

    @DisplayName("1번 핀마크가 10이면 Strike(X) 로 표기된다")
    @Test
    void strikeSign1(){
        pinMarks.mark(10);
        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.Strike);
    }

    @DisplayName("1번,2번 핀마크가 10이면 Strike,Strike 로 표기된다")
    @Test
    void strikeSign2(){
        pinMarks.mark(10);
        pinMarks.mark(10);

        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.Strike, PinMarkSign.Strike);
    }

    @DisplayName("1번,2번,3번 핀마크가 10이면 Strike,Strike,Strike 로 표기된다")
    @Test
    void strikeSign3(){
        pinMarks.mark(10);
        pinMarks.mark(10);
        pinMarks.mark(10);

        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.Strike, PinMarkSign.Strike, PinMarkSign.Strike);
    }


    @DisplayName("1,2번 핀마크 합이 10이면 Spare(/) 로 표기된다")
    @Test
    void spareSign1(){
        pinMarks.mark(9);
        pinMarks.mark(1);

        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.number(9), PinMarkSign.Spare);
    }

    @DisplayName("1번 핀마크가 10이고 2,3번 핀마크 합이 10이면 Strike, Number, Spare 로 표기된다")
    @Test
    void spareSign2(){
        pinMarks.mark(10);
        pinMarks.mark(9);
        pinMarks.mark(1);

        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.Strike, PinMarkSign.number(9), PinMarkSign.Spare);
    }

    @DisplayName("1번 핀마크가 0 이면 Gutter 로 표기된다")
    @Test
    void gutterSign(){
        pinMarks.mark(0);

        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.Gutter);
    }

    @DisplayName("1,2번 핀마크가 모두 0 이면 모두 Gutter 로 표기된다")
    @Test
    void gutterSign2(){
        pinMarks.mark(0);
        pinMarks.mark(0);

        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.Gutter, PinMarkSign.Gutter);
    }

}