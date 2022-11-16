package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FramesTest {

    @Test
    @DisplayName("Miss시에는 프레임번호가 변하지 않음")
    void pitchingMiss() {
        int number = 5;
        Frames frames = new Frames(10);
        FrameNumber frameNumber = frames.pitching(FrameNumber.number(number), 5);
        assertThat(frameNumber).isEqualTo(FrameNumber.number(number));
    }

    @Test
    @DisplayName("Spare시에는 프레임번호가 변함")
    void pitchingSpare() {
        int number = 5;
        Frames frames = new Frames(10);
        frames.pitching(FrameNumber.number(number), 5);
        FrameNumber frameNumber = frames.pitching(FrameNumber.number(number), 5);
        assertThat(frameNumber).isEqualTo(FrameNumber.number(number + 1));
    }

    @Test
    @DisplayName("Strike시에는 프레임번호가 변함")
    void pitchingStrike() {
        int number = 5;
        Frames frames = new Frames(10);
        FrameNumber frameNumber = frames.pitching(FrameNumber.number(number), BowlingPin.MAX_PIN_NUMBER);
        assertThat(frameNumber).isEqualTo(FrameNumber.number(number).next());
    }

    @Test
    @DisplayName("마지막 프레임의 스트라이크의 첫번째 스트라이는 프레임이 변하지 않음")
    void lastPitching() {
        int lastNumber = 10;
        Frames frames = new Frames(lastNumber);
        FrameNumber frameNumber = frames.pitching(FrameNumber.number(lastNumber), BowlingPin.MAX_PIN_NUMBER);
        assertThat(frameNumber).isEqualTo(FrameNumber.number(lastNumber));
    }

    @Test
    @DisplayName("마지막 프레임의 스페어는 프레임이 변하지 않음")
    void lastPitchingSpare() {
        int lastNumber = 10;
        Frames frames = new Frames(lastNumber);
        frames.pitching(FrameNumber.number(lastNumber), 5);
        FrameNumber frameNumber = frames.pitching(FrameNumber.number(lastNumber), 5);
        assertThat(frameNumber).isEqualTo(FrameNumber.number(lastNumber));
    }

    @Test
    @DisplayName("마지막 프레임에서 두 번 다 미스한 경우는 프레임 증가(스페어아님)")
    void lastFrameMiss() {
        int lastNumber = 10;
        Frames frames = new Frames(lastNumber);
        frames.pitching(FrameNumber.number(lastNumber), 5);
        FrameNumber frameNumber = frames.pitching(FrameNumber.number(lastNumber), 4);
        assertThat(frameNumber).isEqualTo(FrameNumber.number(lastNumber).next());
    }

    @Test
    @DisplayName("마지막 프레임에서 세 번 투구한 경우는 프레임 증가")
    void lastFramePitchingThreeTimes() {
        int lastNumber = 10;
        Frames frames = new Frames(lastNumber);
        frames.pitching(FrameNumber.number(lastNumber), 5);
        frames.pitching(FrameNumber.number(lastNumber), 5);
        FrameNumber frameNumber = frames.pitching(FrameNumber.number(lastNumber), 10);
        assertThat(frameNumber).isEqualTo(FrameNumber.number(lastNumber).next());
    }
}