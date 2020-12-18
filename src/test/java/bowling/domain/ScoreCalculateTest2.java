package bowling.domain;

import bowling.domain.frames.Frames2;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculateTest2 {
    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번의 투구가 스트라이크인 경우)")
    public void strikeScoreTest() {
        Frames2 frames = Frames2.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));

        int frame1Score = frames.getScoreIndexOf(1);
        assertThat(frame1Score).isEqualTo(30);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다.")
    public void strikeScoreWithScoreTest() {
        Frames2 frames = Frames2.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));

        int frame1Score = frames.getScoreIndexOf(1);
        assertThat(frame1Score).isEqualTo(16);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번째 투구가 스페어인 경우)")
    public void strikeScoreWithSpareTest() {
        Frames2 frames = Frames2.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));

        int frame1Score = frames.getScoreIndexOf(1);
        assertThat(frame1Score).isEqualTo(20);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구가 존재하지 않으면 null")
    public void strikeScoreNullTest() {
        Frames2 frames = Frames2.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));

        Integer frame1Score = frames.getScoreIndexOf(1);
        assertThat(frame1Score).isNull();
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구의 점수를 합한다.")
    public void spareScoreTest() {
        Frames2 frames = Frames2.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));

        int frame1Score = frames.getScoreIndexOf(1);
        assertThat(frame1Score).isEqualTo(20);
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구가 존재하지 않으면 null")
    public void spareScoreNullTest() {
        Frames2 frames = Frames2.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));

        Integer frame1Score = frames.getScoreIndexOf(1);
        assertThat(frame1Score).isNull();
    }

    @Test
    @DisplayName("Strike, Spare가 아닌 경우 현재 frame의 점수만 합산")
    public void noneStrikeAndSpareTest() {
        Frames2 frames = Frames2.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(3));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));

        int frame1Score = frames.getScoreIndexOf(1);
        assertThat(frame1Score).isEqualTo(6);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번의 투구가 스트라이크인 경우)")
    public void strikeScoreTest_lastFrame() {
        Frames2 frames = Frames2.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));

        int frame9Score = frames.getScoreIndexOf(9);
        assertThat(frame9Score).isEqualTo(30);
    }

    private void setUpToFrame8(Frames2 frames) {
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다.")
    public void strikeScoreWithScoreTest_lastFrame() {
        Frames2 frames = Frames2.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));

        int frame9Score = frames.getScoreIndexOf(9);
        assertThat(frame9Score).isEqualTo(16);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번째 투구가 스페어인 경우)")
    public void strikeScoreWithSpareTest_lastFrame() {
        Frames2 frames = Frames2.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));

        int frame9Score = frames.getScoreIndexOf(9);
        assertThat(frame9Score).isEqualTo(20);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구가 존재하지 않으면 null")
    public void strikeScoreNullTest_lastFrame() {
        Frames2 frames = Frames2.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));

        Integer frame9Score = frames.getScoreIndexOf(9);
        assertThat(frame9Score).isNull();
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구의 점수를 합한다.")
    public void spareScoreTest_lastFrame() {
        Frames2 frames = Frames2.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));

        int frame9Score = frames.getScoreIndexOf(9);
        assertThat(frame9Score).isEqualTo(20);
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구가 존재하지 않으면 null")
    public void spareScoreNullTest_lastFrame() {
        Frames2 frames = Frames2.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));

        Integer frame9Score = frames.getScoreIndexOf(9);
        assertThat(frame9Score).isNull();
    }
}
