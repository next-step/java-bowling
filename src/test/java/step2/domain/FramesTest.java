package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.frame.FinalFrame;
import step2.domain.frame.Frame;
import step2.domain.frame.Frames;
import step2.domain.frame.NormalFrame;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

//    @Test
//    @DisplayName("Frames 객체 생성")
//    void createFrames() {
//        assertThat(Frames.init()).isInstanceOf(Frames.class);
//    }
//
//    @Test
//    @DisplayName("볼링 진행")
//    void bowl() {
//        Frames frames = Frames.init();
//
//        Pitch pitch = Pitch.from(10);
//        Frame normalFrame = NormalFrame.init();
//        normalFrame.bowl(pitch);
//
//        frames.bowl(normalFrame);
//
//        assertThat(frames.getSize()).isEqualTo(1);
//    }
//
//    @Test
//    @DisplayName("볼링 게임 진행 종료")
//    void isFinish() {
//        Frames frames = Frames.init();
//        Frame frame1 = NormalFrame.init();
//        Frame frame2 = NormalFrame.init();
//        Frame frame3 = NormalFrame.init();
//        Frame frame4 = NormalFrame.init();
//        Frame frame5 = NormalFrame.init();
//        Frame frame6 = NormalFrame.init();
//        Frame frame7 = NormalFrame.init();
//        Frame frame8 = NormalFrame.init();
//        Frame frame9 = NormalFrame.init();
//        Frame frame10 = NormalFrame.init();
//        frames.bowl(frame1);
//        frames.bowl(frame2);
//        frames.bowl(frame3);
//        frames.bowl(frame4);
//        frames.bowl(frame5);
//        frames.bowl(frame6);
//        frames.bowl(frame7);
//        frames.bowl(frame8);
//        frames.bowl(frame9);
//        frames.bowl(frame10);
//
//        assertThat(frames.isFinish()).isTrue();
//    }
//
//    @Test
//    @DisplayName("볼링 게임 진행 종료 안함")
//    void isNotFinish() {
//        Frames frames = Frames.init();
//        Frame frame1 = NormalFrame.init();
//        Frame frame2 = NormalFrame.init();
//        Frame frame3 = NormalFrame.init();
//        Frame frame4 = NormalFrame.init();
//        Frame frame5 = NormalFrame.init();
//        Frame frame6 = NormalFrame.init();
//        Frame frame7 = NormalFrame.init();
//        Frame frame8 = NormalFrame.init();
//        Frame frame9 = NormalFrame.init();
//        frames.bowl(frame1);
//        frames.bowl(frame2);
//        frames.bowl(frame3);
//        frames.bowl(frame4);
//        frames.bowl(frame5);
//        frames.bowl(frame6);
//        frames.bowl(frame7);
//        frames.bowl(frame8);
//        frames.bowl(frame9);
//
//        assertThat(frames.isFinish()).isFalse();
//    }
//
//    @Test
//    @DisplayName("다음 프레임 생성")
//    void createNextFrame() {
//        Frames frames = Frames.init();
//        assertThat(frames.nextFrame()).isInstanceOf(Frame.class);
//    }
//
//    @Test
//    @DisplayName("마지막 프레임 생성")
//    void createFinalFrame() {
//        Frames frames = Frames.init();
//        Frame frame1 = NormalFrame.init();
//        Frame frame2 = NormalFrame.init();
//        Frame frame3 = NormalFrame.init();
//        Frame frame4 = NormalFrame.init();
//        Frame frame5 = NormalFrame.init();
//        Frame frame6 = NormalFrame.init();
//        Frame frame7 = NormalFrame.init();
//        Frame frame8 = NormalFrame.init();
//        Frame frame9 = NormalFrame.init();
//        frames.bowl(frame1);
//        frames.bowl(frame2);
//        frames.bowl(frame3);
//        frames.bowl(frame4);
//        frames.bowl(frame5);
//        frames.bowl(frame6);
//        frames.bowl(frame7);
//        frames.bowl(frame8);
//        frames.bowl(frame9);
//
//        assertThat(frames.nextFrame()).isInstanceOf(FinalFrame.class);
//    }
}