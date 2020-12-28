package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GeneralFramePinMarksTest {

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