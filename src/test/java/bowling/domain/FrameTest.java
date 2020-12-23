package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

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
        normalFrame.start(2);
        // when
        normalFrame.start(3);

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

    @DisplayName("남은 볼링핀보다 많은 값을 투구 결과로 입력 시 Exception Test")
    @Test
    void overLeftBowlingPinsCountException(){

        assertThatIllegalArgumentException().isThrownBy(() -> {
            NormalFrame normalFrame =  NormalFrame.from(0);

            normalFrame.start(2);

            normalFrame.start(9);

        }).withMessageContaining("입력한 투구 결과가 남은 핀의 갯수보다 많습니다.");
    }

    @DisplayName("마지막 프레임에서 보너스 pitch 를 가지는 경우 테스트")
    @ParameterizedTest
    @CsvSource(value = {"2:8:true", "10:1:true", "0:0:false", "4:3:false"}, delimiter = ':')
    void has_bonus_pitch_when_in_final_frame(int firstPitch, int secondPitch, boolean expect){

        FinalFrame formalFrame =  FinalFrame.from(10);

        formalFrame.start(firstPitch);
        formalFrame.start(secondPitch);

        assertThat(formalFrame.hasBonusPitch()).isEqualTo(expect);
    }
    
}
