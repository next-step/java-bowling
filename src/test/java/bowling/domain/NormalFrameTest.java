package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.state.Miss;
import bowling.domain.frame.state.Spare;
import bowling.domain.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NormalFrameTest {
    @Test
    @DisplayName("노멀 프레임에는 2번 투구만 가능하다.")
    void bowlTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(5);
        normalFrame.bowl(5);

        assertThatThrownBy(() -> normalFrame.bowl(3))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("노멀 프레임 MISS 테스트")
    void missTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(5);
        normalFrame.bowl(3);

        assertThat(normalFrame.getLatestState()).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("노멀 프레임 STRIKE 테스트")
    void strikeTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(10);

        assertThat(normalFrame.getLatestState()).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("노멀 프레임 SPARE 테스트")
    void spareTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(7);
        normalFrame.bowl(3);

        assertThat(normalFrame.getLatestState()).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("노멀 프레임 STRIKE면 1번만 투구 가능.")
    void onlyOneBowlIfStrikeTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.bowl(10);

        assertThatThrownBy(() -> normalFrame.bowl(3))
                .isInstanceOf(IllegalStateException.class);
    }

//    @Test
//    @DisplayName("노멀 프레임 점수 계산 테스트")
//    void calculateScore2Test() {
//        Frame frame1 = new NormalFrame();
//        frame1.bowl(10);
//        Frame frame2 = frame1.createFrame(false);
//        frame2.bowl(10);
//        Frame frame3 = frame2.createFrame(false);
//        frame3.bowl(7);
//        frame3.bowl(3);
//        Frame frame4 = frame3.createFrame(false);
//        frame4.bowl(5);
//        frame4.bowl(0);
//
//        assertThat(frame1.getScore()).isEqualTo(10 + 10 + 7);
//        assertThat(frame2.getScore()).isEqualTo(10 + 7 + 3);
//        assertThat(frame3.getScore()).isEqualTo(10 + 5);
//        assertThat(frame4.getScore()).isEqualTo(5);
//    }
}
