package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculateTest {
    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번의 투구가 스트라이크인 경우)")
    public void strikeScoreTest() {
        Frame frame1 = NormalFrame.getFirstFrame();
        Frame frame2 = frame1.initNextFrame();
        Frame frame3 = frame2.initNextFrame();

        frame1.setKnockDownPins(KnockDownPins.valueOf(10));
        frame2.setKnockDownPins(KnockDownPins.valueOf(10));
        frame3.setKnockDownPins(KnockDownPins.valueOf(10));

        assertThat(frame1.getScore()).isEqualTo(30);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다.")
    public void strikeScoreWithScoreTest() {
        Frame frame1 = NormalFrame.getFirstFrame();
        Frame frame2 = frame1.initNextFrame();
        Frame frame3 = frame2.initNextFrame();

        frame1.setKnockDownPins(KnockDownPins.valueOf(10));
        frame2.setKnockDownPins(KnockDownPins.valueOf(3));
        frame2.setKnockDownPins(KnockDownPins.valueOf(3));

        assertThat(frame1.getScore()).isEqualTo(16);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번째 투구가 스페어인 경우)")
    public void strikeScoreWithSpareTest() {
        Frame frame1 = NormalFrame.getFirstFrame();
        Frame frame2 = frame1.initNextFrame();
        Frame frame3 = frame2.initNextFrame();

        frame1.setKnockDownPins(KnockDownPins.valueOf(10));
        frame2.setKnockDownPins(KnockDownPins.valueOf(5));
        frame2.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame1.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구가 존재하지 않으면 null")
    public void strikeScoreNullTest() {
        Frame frame1 = NormalFrame.getFirstFrame();
        Frame frame2 = frame1.initNextFrame();
        Frame frame3 = frame2.initNextFrame();

        frame1.setKnockDownPins(KnockDownPins.valueOf(10));
        frame2.setKnockDownPins(KnockDownPins.valueOf(10));

        assertThat(frame1.getScore()).isEqualTo(null);
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구의 점수를 합한다.")
    public void spareScoreTest() {
        Frame frame1 = NormalFrame.getFirstFrame();
        Frame frame2 = frame1.initNextFrame();

        frame1.setKnockDownPins(KnockDownPins.valueOf(5));
        frame1.setKnockDownPins(KnockDownPins.valueOf(5));
        frame2.setKnockDownPins(KnockDownPins.valueOf(10));

        assertThat(frame1.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구가 존재하지 않으면 null")
    public void spareScoreNullTest() {
        Frame frame1 = NormalFrame.getFirstFrame();
        Frame frame2 = frame1.initNextFrame();

        frame1.setKnockDownPins(KnockDownPins.valueOf(5));
        frame1.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame1.getScore()).isEqualTo(null);
    }

    @Test
    @DisplayName("Strike, Spare가 아닌 경우 현재 frame의 점수만 합산")
    public void noneStrikeAndSpareTest() {
        Frame frame1 = NormalFrame.getFirstFrame();

        frame1.setKnockDownPins(KnockDownPins.valueOf(3));
        frame1.setKnockDownPins(KnockDownPins.valueOf(3));

        assertThat(frame1.getScore()).isEqualTo(6);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번의 투구가 스트라이크인 경우)")
    public void strikeScoreTest_lastFrame() {
        Frame frame9 = NormalFrame.getFrame(9);
        Frame lastFrame = frame9.initNextFrame();

        frame9.setKnockDownPins(KnockDownPins.valueOf(10));
        lastFrame.setKnockDownPins(KnockDownPins.valueOf(10));
        lastFrame.setKnockDownPins(KnockDownPins.valueOf(10));

        assertThat(frame9.getScore()).isEqualTo(30);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다.")
    public void strikeScoreWithScoreTest_lastFrame() {
        Frame frame9 = NormalFrame.getFrame(9);
        Frame lastFrame = frame9.initNextFrame();

        frame9.setKnockDownPins(KnockDownPins.valueOf(10));
        lastFrame.setKnockDownPins(KnockDownPins.valueOf(3));
        lastFrame.setKnockDownPins(KnockDownPins.valueOf(3));

        assertThat(frame9.getScore()).isEqualTo(16);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번째 투구가 스페어인 경우)")
    public void strikeScoreWithSpareTest_lastFrame() {
        Frame frame9 = NormalFrame.getFrame(9);
        Frame lastFrame = frame9.initNextFrame();

        frame9.setKnockDownPins(KnockDownPins.valueOf(10));
        lastFrame.setKnockDownPins(KnockDownPins.valueOf(5));
        lastFrame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame9.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구가 존재하지 않으면 null")
    public void strikeScoreNullTest_lastFrame() {
        Frame frame9 = NormalFrame.getFrame(9);
        Frame lastFrame = frame9.initNextFrame();

        frame9.setKnockDownPins(KnockDownPins.valueOf(10));
        lastFrame.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame9.getScore()).isEqualTo(null);
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구의 점수를 합한다.")
    public void spareScoreTest_lastFrame() {
        Frame frame9 = NormalFrame.getFrame(9);
        Frame lastFrame = frame9.initNextFrame();

        frame9.setKnockDownPins(KnockDownPins.valueOf(5));
        frame9.setKnockDownPins(KnockDownPins.valueOf(5));
        lastFrame.setKnockDownPins(KnockDownPins.valueOf(10));

        assertThat(frame9.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구가 존재하지 않으면 null")
    public void spareScoreNullTest_lastFrame() {
        Frame frame9 = NormalFrame.getFrame(9);
        Frame lastFrame = frame9.initNextFrame();

        frame9.setKnockDownPins(KnockDownPins.valueOf(5));
        frame9.setKnockDownPins(KnockDownPins.valueOf(5));

        assertThat(frame9.getScore()).isEqualTo(null);
    }
}
