package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    void frameTest_scoringText_strike() {
        Frame frame = new Frame(1, 10, 0);

        assertThat(frame.scoringText()).isEqualTo("X");
    }

    @Test
    void frameTest_scoringText_spare_1_9() {
        Frame frame = new Frame(1, 1, 9);

        assertThat(frame.scoringText()).isEqualTo("1|/");
    }

    @Test
    void frameTest_scoringText_spare_2_8() {
        Frame frame = new Frame(1, 2, 8);

        assertThat(frame.scoringText()).isEqualTo("2|/");
    }

    @Test
    void frameTest_scoringText_Miss_1_2() {
        Frame frame = new Frame(1, 1, 2);

        assertThat(frame.scoringText()).isEqualTo("1|2");
    }

    @Test
    void frameTest_scoringText_Miss_4_5() {
        Frame frame = new Frame(1, 4, 5);

        assertThat(frame.scoringText()).isEqualTo("4|5");
    }

    @Test
    void frameTest_scoringText_gutter() {
        Frame frame = new Frame(1, 0, 0);

        assertThat(frame.scoringText()).isEqualTo("-|-");
    }

    @Test
    void frameTest_scoringText_gutter_1_0() {
        Frame frame = new Frame(1, 1, 0);

        assertThat(frame.scoringText()).isEqualTo("1|-");
    }

    @Test
    void frameTest_scoringText_gutter_0_2() {
        Frame frame = new Frame(1, 0, 2);

        assertThat(frame.scoringText()).isEqualTo("-|2");
    }

    @Test
    void bonus_frame_scoringText_spare_0_10_strike() {
        Frame frame = new Frame(10, 0, 10, 10);

        assertThat(frame.scoringText()).isEqualTo("-|/|X");
    }

    @Test
    void bonus_frame_scoringText_spare_3_7_strike() {
        Frame frame = new Frame(10, 3, 7, 10);

        assertThat(frame.scoringText()).isEqualTo("3|/|X");
    }

    @Test
    void bonus_frame_scoringText_spare_3_7_miss() {
        Frame frame = new Frame(10, 3, 7, 1);

        assertThat(frame.scoringText()).isEqualTo("3|/|1");
    }

    @Test
    void bonus_frame_scoringText_spare_3_7_gutter() {
        Frame frame = new Frame(10, 3, 7, 0);

        assertThat(frame.scoringText()).isEqualTo("3|/|-");
    }


    @Test
    void bonus_frame_scoringText_strike_strike() {
        Frame frame = new Frame(10, 10, 0, 10);

        assertThat(frame.scoringText()).isEqualTo("X|X");
    }

    @Test
    void bonus_frame_scoringText_strike_miss() {
        Frame frame = new Frame(10, 10, 0, 2);

        assertThat(frame.scoringText()).isEqualTo("X|2");
    }

    @Test
    void bonus_frame_scoringText_strike_gutter() {
        Frame frame = new Frame(10, 10, 0, 0);

        assertThat(frame.scoringText()).isEqualTo("X|-");
    }
}
