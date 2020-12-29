package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GeneralFramePinMarksTest {

    @DisplayName("1번째 투구가 10개 핀을 쓰러뜨리면 Strike 다")
    @Test
    void isStrike(){
        PinMarks pinMarks = new GeneralFramePinMarks();
        pinMarks.mark(PinMark.pin(10));
        assertThat(pinMarks.isStrike()).isTrue();
    }

    @DisplayName("1,2번째 투구로 10개 핀을 쓰러뜨리면 Strike 가 아니다")
    @Test
    void isNotStrike(){
        PinMarks pinMarks = new GeneralFramePinMarks();
        pinMarks.mark(PinMark.pin(7));
        pinMarks.mark(PinMark.pin(3));
        assertThat(pinMarks.isStrike()).isFalse();
        assertThat(pinMarks.getCountOfFallDownPins()).isEqualTo(PinMark.MAX_PINS);
    }

    @DisplayName("mark 된 pin 수의 합을 구할 수 있다")
    @Test
    void countOfFallDownPins(){
        PinMarks pinMarks = new GeneralFramePinMarks();
        pinMarks.mark(PinMark.pin(9));
        pinMarks.mark(PinMark.pin(1));
        assertThat(pinMarks.getCountOfFallDownPins()).isEqualTo(10);
    }

    @DisplayName("수정불가능한 List<PinMark> 로 변환 할 수 있다")
    @Test
    void immutableList(){
        PinMarks pinMarks = new GeneralFramePinMarks();
        pinMarks.mark(PinMark.pin(3));

        List<PinMark> immutableList = pinMarks.toImmutableList();
        assertThatThrownBy( () -> immutableList.add(PinMark.pin(10)) )
                .isInstanceOf(UnsupportedOperationException.class);
    }

}