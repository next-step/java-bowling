package bowling.step4;

import bowling.step4.domain.frame.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    Frames frames;

    @BeforeEach
    void setup() {
        frames = new Frames();
    }

    @Test
    @DisplayName("frames_create_기본_1개_프레임외_2번투구시마다_1개씩_증가")
    void frames_create_normal() {
        frames.progress(3);
        frames.progress(3);

        frames.progress(3);
        frames.progress(3);
        System.out.println("frame.getScore()>>"+frames.getFramesSize());
        assertThat(frames.getFramesSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("frames_create_기본_1개_프레임외_1번투구시마다_1개씩_증가_스트라이크")
    void frames_create_strike() {
        frames.progress(10);
        frames.progress(10);

        assertThat(frames.getFramesSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("frames_progress_진행후_프레임별_점수리스트_리턴")
    void frames_progress_normal() {
        frames.progress(5);
        frames.progress(4);
        frames.progress(3);
        frames.progress(2);
        List<Integer> scores = frames.calculateScores();

        assertThat(scores.get(0)).isEqualTo(9);
        assertThat(scores.get(1)).isEqualTo(5);
    }

    @Test
    @DisplayName("frames_progress_진행후_프레임별_점수리스트_리턴_스트라이크의경우")
    void frames_progress_strike() {
        frames.progress(10);
        frames.progress(3);
        frames.progress(2);
        List<Integer> scores = frames.calculateScores();

        assertThat(scores.get(0)).isEqualTo(15);
        assertThat(scores.get(1)).isEqualTo(5);
    }
}
