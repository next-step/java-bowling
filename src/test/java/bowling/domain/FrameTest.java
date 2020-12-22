package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @DisplayName("Frame 생성 테스트")
    @Test
    void frameConstructorTest(){

        // given
        int index = 0;

        // when
        Frame frame = FinalFrame.from(index);

        // then
        assertThat(frame).isEqualTo(FinalFrame.from(index));
    }


    @DisplayName("Pitching 결과 합계 테스트")
    @Test
    void sumCurrentPitchResultsTest(){

        NormalFrame normalFrame =  NormalFrame.from(0);

        // when
        normalFrame.start(leftBowlingPins -> 2);
        // when
        normalFrame.start(leftBowlingPins -> 3);

        // then
        assertThat(normalFrame.sumCurrentPitchResults()).isEqualTo(2 + 3);
    }

    @DisplayName("FinalFrame 에서 다음 프레임 생성 요청 시 Exception 테스트")
    @Test
    void finalFrameMakeNextFrameException(){

        Frame frame = FinalFrame.from(10);

        assertThatThrownBy(() -> frame.makeNextFrame(10))
                .isInstanceOf(RuntimeException.class).withFailMessage("마지막 프레임입니다.");


    }
}
