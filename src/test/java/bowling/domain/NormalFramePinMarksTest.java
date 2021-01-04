package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFramePinMarksTest {

    NormalFramePinMarks pinMarks;

    @BeforeEach
    void setUp(){
        pinMarks = new NormalFramePinMarks();
    }

    @DisplayName("1번째 투구가 10개 핀을 쓰러뜨리면 Strike 다")
    @Test
    void isStrike(){
        pinMarks.mark(PinMark.pin(10));
        assertThat(pinMarks.isFirstStrike()).isTrue();
    }

    @DisplayName("1,2번째 투구로 10개 핀을 쓰러뜨리면 Strike 가 아니다")
    @Test
    void isNotStrike(){
        pinMarks.mark(PinMark.pin(7));
        pinMarks.mark(PinMark.pin(3));
        assertThat(pinMarks.isFirstStrike()).isFalse();
        assertThat(pinMarks.getCountOfAllFallDownPins()).isEqualTo(PinMark.MAX_PINS);
    }

    @DisplayName("mark 된 pin 수의 합을 구할 수 있다")
    @Test
    void countOfFallDownPins(){
        pinMarks.mark(PinMark.pin(9));
        pinMarks.mark(PinMark.pin(1));
        assertThat(pinMarks.getCountOfAllFallDownPins()).isEqualTo(10);
    }

    @DisplayName("수정불가능한 List<PinMark> 로 변환 할 수 있다")
    @Test
    void immutableList(){
        pinMarks.mark(PinMark.pin(3));

        List<PinMark> immutableList = pinMarks.toList();
        assertThatThrownBy( () -> immutableList.add(PinMark.pin(10)) )
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("1번 핀마크가 10이면 Strike(X) 로 표기된다")
    @Test
    void strikeSign(){
        pinMarks.mark(10);
        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.Strike);
    }

    @DisplayName("1,2번 핀마크 합이 10이면 Spare(/) 로 표기된다")
    @Test
    void spareSign(){
        pinMarks.mark(9);
        pinMarks.mark(1);

        assertThat(pinMarks.toSigns())
                .containsExactly(PinMarkSign.number(9), PinMarkSign.Spare);
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