package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    @DisplayName("Frames 생성")
    void createNormalFrames() {
        assertThat(Frames.init()).isInstanceOf(Frames.class);
    }

    @Test
    @DisplayName("NormalFrame 추가 기능")
    void addNormalFrame() {
        Pitch pitch1 = Pitch.from(10);
        NormalFrame normalFrame1 = NormalFrame.init();
        normalFrame1.add(pitch1);

        Pitch pitch2 = Pitch.from(8);
        Pitch pitch3 = Pitch.from(2);
        NormalFrame normalFrame2 = NormalFrame.init();
        normalFrame2.add(pitch2);
        normalFrame2.add(pitch3);

        Frames frames = Frames.init();
        frames.add(normalFrame1);
        frames.add(normalFrame2);

        assertThat(frames.getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("NormalFrame에 FinalFrame 추가 기능")
    void addAllFrame() {
        Pitch pitch1 = Pitch.from(10);
        Frame normalFrame1 = NormalFrame.init();
        normalFrame1.add(pitch1);

        Pitch pitch2 = Pitch.from(8);
        Pitch pitch3 = Pitch.from(2);
        Frame normalFrame2 = NormalFrame.init();
        normalFrame2.add(pitch2);
        normalFrame2.add(pitch3);

        Pitch pitch4 = Pitch.from(10);
        Pitch pitch5 = Pitch.from(10);
        Pitch pitch6 = Pitch.from(10);
        Frame finalFrame = FinalFrame.init();
        finalFrame.add(pitch4);
        finalFrame.add(pitch5);
        finalFrame.add(pitch6);

        Frames frames = Frames.init();
        frames.add(normalFrame1);
        frames.add(normalFrame2);
        frames.add(finalFrame);

        assertThat(frames.getSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("볼링 게임 종료 조건")
    void finishBowling() {
        Pitch pitch = Pitch.from(10);
        Frame frame1 = NormalFrame.init();
        frame1.add(pitch);
        Frame frame2 = NormalFrame.init();
        frame2.add(pitch);
        Frame frame3 = NormalFrame.init();
        frame3.add(pitch);
        Frame frame4 = NormalFrame.init();
        frame4.add(pitch);
        Frame frame5 = NormalFrame.init();
        frame5.add(pitch);
        Frame frame6 = NormalFrame.init();
        frame6.add(pitch);
        Frame frame7 = NormalFrame.init();
        frame7.add(pitch);
        Frame frame8 = NormalFrame.init();
        frame8.add(pitch);
        Frame frame9 = NormalFrame.init();
        frame9.add(pitch);
        Frame frame10 = FinalFrame.init();
        frame10.add(pitch);
        frame10.add(pitch);
        frame10.add(pitch);


        List<Frame> frameList = new ArrayList<>();
        frameList.add(frame1);
        frameList.add(frame2);
        frameList.add(frame3);
        frameList.add(frame4);
        frameList.add(frame5);
        frameList.add(frame6);
        frameList.add(frame7);
        frameList.add(frame8);
        frameList.add(frame9);
        frameList.add(frame10);

        Frames frames = Frames.from(frameList);

        Assertions.assertThat(frames.isFinished()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임인지 확인")
    void finalFrame() {
        Pitch pitch = Pitch.from(10);
        Frame frame1 = NormalFrame.init();
        frame1.add(pitch);
        Frame frame2 = NormalFrame.init();
        frame2.add(pitch);
        Frame frame3 = NormalFrame.init();
        frame3.add(pitch);
        Frame frame4 = NormalFrame.init();
        frame4.add(pitch);
        Frame frame5 = NormalFrame.init();
        frame5.add(pitch);
        Frame frame6 = NormalFrame.init();
        frame6.add(pitch);
        Frame frame7 = NormalFrame.init();
        frame7.add(pitch);
        Frame frame8 = NormalFrame.init();
        frame8.add(pitch);
        Frame frame9 = NormalFrame.init();
        frame9.add(pitch);

        List<Frame> frameList = new ArrayList<>();
        frameList.add(frame1);
        frameList.add(frame2);
        frameList.add(frame3);
        frameList.add(frame4);
        frameList.add(frame5);
        frameList.add(frame6);
        frameList.add(frame7);
        frameList.add(frame8);
        frameList.add(frame9);

        Frames frames = Frames.from(frameList);

        Assertions.assertThat(frames.isFinalFrame()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임이 아닌지 확인")
    void notFinalFrame() {
        Pitch pitch = Pitch.from(10);
        Frame frame1 = NormalFrame.init();
        frame1.add(pitch);
        Frame frame2 = NormalFrame.init();
        frame2.add(pitch);
        Frame frame3 = NormalFrame.init();
        frame3.add(pitch);
        Frame frame4 = NormalFrame.init();
        frame4.add(pitch);
        Frame frame5 = NormalFrame.init();
        frame5.add(pitch);
        Frame frame6 = NormalFrame.init();
        frame6.add(pitch);
        Frame frame7 = NormalFrame.init();
        frame7.add(pitch);
        Frame frame8 = NormalFrame.init();
        frame8.add(pitch);

        List<Frame> frameList = new ArrayList<>();
        frameList.add(frame1);
        frameList.add(frame2);
        frameList.add(frame3);
        frameList.add(frame4);
        frameList.add(frame5);
        frameList.add(frame6);
        frameList.add(frame7);
        frameList.add(frame8);

        Frames frames = Frames.from(frameList);

        Assertions.assertThat(frames.isFinalFrame()).isFalse();
    }

    @Test
    @DisplayName("NormalFrame 생성")
    void createNormalFrame() {
        Frames frames = Frames.init();

        Frame normalFrame = frames.createFrame();
        assertThat(normalFrame).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("FinalFrame 생성")
    void createFinalFrame() {
        Pitch pitch = Pitch.from(10);
        Frame frame1 = NormalFrame.init();
        frame1.add(pitch);
        Frame frame2 = NormalFrame.init();
        frame2.add(pitch);
        Frame frame3 = NormalFrame.init();
        frame3.add(pitch);
        Frame frame4 = NormalFrame.init();
        frame4.add(pitch);
        Frame frame5 = NormalFrame.init();
        frame5.add(pitch);
        Frame frame6 = NormalFrame.init();
        frame6.add(pitch);
        Frame frame7 = NormalFrame.init();
        frame7.add(pitch);
        Frame frame8 = NormalFrame.init();
        frame8.add(pitch);
        Frame frame9 = NormalFrame.init();
        frame9.add(pitch);

        List<Frame> frameList = new ArrayList<>();
        frameList.add(frame1);
        frameList.add(frame2);
        frameList.add(frame3);
        frameList.add(frame4);
        frameList.add(frame5);
        frameList.add(frame6);
        frameList.add(frame7);
        frameList.add(frame8);
        frameList.add(frame9);

        Frames frames = Frames.from(frameList);

        Frame normalFrame = frames.createFrame();
        assertThat(normalFrame).isInstanceOf(FinalFrame.class);
    }

}