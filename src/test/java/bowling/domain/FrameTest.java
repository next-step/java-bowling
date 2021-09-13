package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = Frame.init();
    }

    @Test
    @DisplayName("Frame init() 으로 생성시 inning = 1인 프레임 생성")
    void initTest() {
        Inning inning = frame.inning();
        assertThat(inning.asInt()).isEqualTo(1);
    }

    @Test
    @DisplayName("첫 pitch 가 strike 일때, recode 시 다음 이닝을 가진 프레임 생성")
    void first_pitch_strike_then_next_Inning() {
        Frame firstFrame = frame.recode(new Pitch(10));
        Frame nextFrame = firstFrame.recode(new Pitch(3));

        assertThat(nextFrame.getInning().asInt()).isEqualTo(2);
    }

    @Test
    @DisplayName("첫 frame recode(), 같은 이닝 프레임 first 할당")
    void first_recode() {
        Frame recode = frame.recode(new Pitch(2));
        Frame frame = new Frame(new Inning(1), new Pitch(2));

        assertThat(recode).isEqualTo(frame);
    }

    @Test
    @DisplayName("first Pitch 가 strike 가 아닐 경우 pitch 를 입력시, 같은 이닝 프레임의 second 에 할당")
    void second_recode() {
        //given
        Frame firstRecode = frame.recode(new Pitch(2));

        //when
        Frame secondRecode = firstRecode.recode(new Pitch(3));

        //then
        Frame frame = new Frame(new Inning(1), new Pitch(2), new Pitch(3));

        assertThat(secondRecode).isEqualTo(frame);
    }

    @Test
    @DisplayName("second pitch 까지 할당이 되었다면 다음 이닝을 가진 프레임 생성")
    void next_inning() {
        //given
        Frame firstRecode = frame.recode(new Pitch(2));
        Frame secondRecode = firstRecode.recode(new Pitch(3));

        //when
        Frame nextInningFrame = secondRecode.recode(new Pitch(5));

        //then
        assertThat(secondRecode.getInning().asInt() + 1).isEqualTo(nextInningFrame.getInning().asInt());
    }
}