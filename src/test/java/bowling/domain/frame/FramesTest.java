package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    public void framesInit() {
        Frames frames = Frames.init();

        assertThat(frames.size()).isEqualTo(10);
    }

    @Test
    public void playNextFramePossibleWhenFirstFrame() {
        Frames frames = Frames.init();

        assertThat(frames.canPlay()).isTrue();
    }

    @Test
    public void playNextFrameImpossibleWhenLastFrameStrike() {
        Frames frames = Frames.init();
        for (int i = 0; i < 11; i++) {
            frames.play(10);
        }

        assertThat(frames.canPlay()).isFalse();
    }

    @Test
    public void playNextFrameImpossibleWhenLastFrameMiss() {
        Frames frames = Frames.init();
        for (int i = 0; i < 10; i++) {
            frames.play(10);
        }
        frames.play(8);

        assertThat(frames.canPlay()).isFalse();
    }

    @Test
    @DisplayName("Strike-Miss 시 정상적으로 점수 계산 확인 및 계산 미완료 상태 확인")
    public void calculateSuccess_whenStrikeMiss() {
        Frames frames = Frames.init();
        frames.play(10);
        frames.play(7);

        assertThat(frames.getFrames().get(0).getScore()).isEqualTo(17);
        assertThat(frames.getFrames().get(0).isScoreCalculateDone()).isFalse();

        assertThat(frames.getFrames().get(1).getScore()).isEqualTo(17);
        assertThat(frames.getFrames().get(1).isScoreCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("Strike-Miss-Spare 시 정상적으로 점수 계산 확인 및 계산 미완료 상태 확인")
    public void calculateSuccess_whenStrikeMissSpare() {
        Frames frames = Frames.init();
        frames.play(10);
        frames.play(7);
        frames.play(3);

        assertThat(frames.getFrames().get(0).getScore()).isEqualTo(20);
        assertThat(frames.getFrames().get(0).isScoreCalculateDone()).isTrue();

        assertThat(frames.getFrames().get(1).getScore()).isEqualTo(30);
        assertThat(frames.getFrames().get(1).isScoreCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("Strike-Strike-Miss 시 정상적으로 점수 계산 및 계산 완료 상태 확인")
    public void calculateSuccess_whenStrikeStrikeMiss() {
        Frames frames = Frames.init();
        frames.play(10);
        frames.play(10);
        frames.play(7);

        assertThat(frames.getFrames().get(0).getScore()).isEqualTo(27);
        assertThat(frames.getFrames().get(0).isScoreCalculateDone()).isTrue();

        assertThat(frames.getFrames().get(1).getScore()).isEqualTo(37);
        assertThat(frames.getFrames().get(1).isScoreCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("Miss-Spare 시 정상적으로 점수 계산 및 계산 미완료 상태 확인")
    public void calculateSuccess_whenMissSpare() {
        Frames frames = Frames.init();

        frames.play(3);
        frames.play(7);

        assertThat(frames.getFrames().get(0).getScore()).isEqualTo(10);
        assertThat(frames.getFrames().get(0).isScoreCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("Miss-Spare-Miss 시 정상적으로 점수 계산 및 계산 완료 상태 확인")
    public void calculateSuccess_whenMissSpareMiss() {
        Frames frames = Frames.init();

        frames.play(3);
        frames.play(7);
        frames.play(7);

        assertThat(frames.getFrames().get(0).getScore()).isEqualTo(17);
        assertThat(frames.getFrames().get(0).isScoreCalculateDone()).isTrue();

        assertThat(frames.getFrames().get(1).getScore()).isEqualTo(24);
        assertThat(frames.getFrames().get(1).isScoreCalculateDone()).isTrue();
    }

    @Test
    @DisplayName("Miss 시 정상적으로 점수 계산 확인 및 계산 완료 상태 확인")
    public void calculateSuccess_whenMiss() {
        Frames frames = Frames.init();
        frames.play(10);
        frames.play(7);

        assertThat(frames.getFrames().get(0).getScore()).isEqualTo(17);
        assertThat(frames.getFrames().get(0).isScoreCalculateDone()).isTrue();
    }

    @Test
    @DisplayName("Gutter 시 정상적으로 점수 계산 확인 및 계산 완료 상태 확인")
    public void calculateSuccess_whenGutter() {
        Frames frames = Frames.init();
        frames.play(0);

        assertThat(frames.getFrames().get(0).getScore()).isEqualTo(0);
        assertThat(frames.getFrames().get(0).isScoreCalculateDone()).isTrue();
    }
}
