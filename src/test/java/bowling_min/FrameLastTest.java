package bowling_min;

import bowling_step3.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameLastTest {

    private Frames frames;
    private Frame lastFrame;

    @BeforeEach
    public void setup() {
        frames = Frames.create();
        lastFrame = frames.last();
    }

    @Test
    public void miss() {
        lastFrame = lastFrame.playManual(8);
        lastFrame = lastFrame.playManual(1);
        assertThat(lastFrame.done()).isTrue();
    }

    @Test
    public void spare_isnot_endgame() {
        lastFrame = lastFrame.playManual(8);
        lastFrame = lastFrame.playManual(2);
        assertThat(lastFrame.done()).isFalse();
    }

    @Test
    public void spare_is_endgame() {
        lastFrame = lastFrame.playManual(8);
        lastFrame = lastFrame.playManual(2);
        lastFrame = lastFrame.playManual(7);
        assertThat(lastFrame.done()).isTrue();
    }

    @Test
    public void game_over() {
        lastFrame = lastFrame.playManual(8);
        lastFrame = lastFrame.playManual(2);
        lastFrame = lastFrame.playManual(7);
        assertThatThrownBy(() -> {
            lastFrame = lastFrame.playManual(3);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void getScore_miss() {
        lastFrame = lastFrame.playManual(8);
        lastFrame = lastFrame.playManual(1);
        assertThat(lastFrame.getScore()).isEqualTo(9);
    }

    @Test
    public void getScore_cannot_calculate() {
        assertThatThrownBy(() -> {
            lastFrame = lastFrame.playManual(8);
            lastFrame.getScore();
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void getScore_spare() {
        lastFrame = lastFrame.playManual(8);
        lastFrame = lastFrame.playManual(2);
        lastFrame = lastFrame.playManual(9);
        assertThat(lastFrame.getScore()).isEqualTo(19);
    }

    @Test
    public void getScore_twoStrike() {
        lastFrame = lastFrame.playManual(10);
        lastFrame = lastFrame.playManual(10);
        lastFrame = lastFrame.playManual(8);
        assertThat(lastFrame.getScore()).isEqualTo(28);
    }

    @Test
    public void getScore_threeStrike() {
        lastFrame = lastFrame.playManual(10);
        lastFrame = lastFrame.playManual(10);
        lastFrame = lastFrame.playManual(10);
        assertThat(lastFrame.getScore()).isEqualTo(30);
    }

    @Test
    public void getScore_9프레임_Strike() {
        Scores scores = new Scores(List.of(10), 0);
        lastFrame = lastFrame.playManual(10);
        lastFrame = lastFrame.playManual(10);
        assertThat(lastFrame.calculateAdditionalScore(scores)).isEqualTo(30);
    }

    @Test
    public void getScore_9프레임_Spare() {
        Scores scores = new Scores(List.of(9, 1), 0);
        lastFrame = lastFrame.playManual(9);
        lastFrame = lastFrame.playManual(1);
        assertThat(lastFrame.calculateAdditionalScore(scores)).isEqualTo(19);
    }

    @Test
    public void getScore_9프레임_Strike_notReady() {
        assertThatThrownBy(() -> {
            Scores scores = new Scores(List.of(10), 0);
            lastFrame = lastFrame.playManual(10);
            lastFrame.calculateAdditionalScore(scores);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

//    @Test
//    public void getDesc_3Strike() {
//        lastFrame.bowl(10).bowl(10).bowl(10);
//        assertThat(lastFrame.getDesc()).isEqualTo("X | X | X");
//    }
//
//    @Test
//    public void getDesc_Spare() {
//        lastFrame.bowl(8).bowl(2).bowl(10);
//        assertThat(lastFrame.getDesc()).isEqualTo("8 | / | X");
//    }
//
//    @Test
//    public void getDesc_Miss() {
//        lastFrame.bowl(8).bowl(1);
//        assertThat(lastFrame.getDesc()).isEqualTo("8 | 1");
//
//    }
}