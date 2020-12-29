package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LastFramePinMarksTest {

    @DisplayName("2번의 투구로 마크된 핀수가 10개 미만이면 보너스 마크를 할 수 없다")
    @Test
    void extraMark1(){
        PinMarks pinMarks = new LastFramePinMarks();
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(1));

        assertThatThrownBy( () -> pinMarks.mark(PinMark.pin(9)))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("1번째,2번째 투구모두 10개를 mark 하면 보너스 마크를 할 수 있다")
    @Test
    void extraMark2(){
        PinMarks pinMarks = new LastFramePinMarks();
        pinMarks.mark(PinMark.pin(10));
        pinMarks.mark(PinMark.pin(10));
        pinMarks.mark(PinMark.pin(10));
    }

    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하면 보너스 마크를 할 수 있다")
    @Test
    void extraMark3(){
        PinMarks pinMarks = new LastFramePinMarks();
        pinMarks.mark(PinMark.pin(5));
        pinMarks.mark(PinMark.pin(5));
        pinMarks.mark(PinMark.pin(10));
    }

    @DisplayName("보너스 마크를 포함해서 최대 3번까지 마크 할 수 있다. 그 이상 마크하면 exception 을 던진다")
    @Test
    void tooManyMarks(){
        PinMarks pinMarks = new LastFramePinMarks();
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(2));
        pinMarks.mark(PinMark.pin(1));

        assertThatThrownBy( () -> pinMarks.mark(PinMark.pin(9)))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하지 못하면 보너스마크는 empty 로 마크되어 allmarked 된다")
    @Test
    void allMarked1(){
        PinMarks pinMarks = new LastFramePinMarks();
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(1));

        assertThat(pinMarks.isAllMarked()).isTrue();
    }

    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하고 보너스까지 마그하면 allmarked 된다")
    @Test
    void allMarked2(){
        PinMarks pinMarks = new LastFramePinMarks();
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(2));
        pinMarks.mark(PinMark.pin(5));

        assertThat(pinMarks.isAllMarked()).isTrue();
    }


    @DisplayName("1번째,2번째 투구의 합이 10개를 mark 하면 보너스 마크가 남기때문에 allmarked 되지 않는다")
    @Test
    void notAllMarked(){
        PinMarks pinMarks = new LastFramePinMarks();
        pinMarks.mark(PinMark.pin(8));
        pinMarks.mark(PinMark.pin(2));

        assertThat(pinMarks.isAllMarked()).isFalse();
    }

    @DisplayName("1번째,2번째 모두 10개씩 mark 하면 보너스 마크가 남기때문에 allmarked 되지 않는다")
    @Test
    void notAllMarked2(){
        PinMarks pinMarks = new LastFramePinMarks();
        pinMarks.mark(PinMark.pin(10));
        pinMarks.mark(PinMark.pin(10));

        assertThat(pinMarks.isAllMarked()).isFalse();
    }


}