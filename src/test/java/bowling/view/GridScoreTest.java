package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GridScoreTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("aaa");
    }

    @Test
    void header() {
        assertThat(Grid.header())
                .isEqualTo("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    @Test
    void blank() {
        assertThat(Grid.blank())
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
//        ResultView.init();
    }

    @Test
    void score() {
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score_strike() {
        player.stroke(0, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score_strike2() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score_strike3() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |      |      |      |      |      |      |      |");
    }

    @Test
    void score_strike4() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |      |      |      |      |      |      |");
    }

    @Test
    void score_strike5() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |      |      |      |      |      |");
    }

    @Test
    void score_strike6() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |      |      |      |      |");
    }

    @Test
    void score_strike7() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |      |      |      |");
    }

    @Test
    void score_strike8() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |      |      |");
    }

    @Test
    void score_strike9() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |      |");
    }

    @Test
    void score_strike10() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |");
    }

    @Test
    void score_strike11() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(10));
        player.spare(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X |");
    }

    @Test
    void score_strike12() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(10));
        player.spare(9, new Pins(10));
        player.spare(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|X |");
    }

    @Test
    void score_strike11_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(10));
        player.spare(9, new Pins(10));
        player.spare(9, new Pins(9));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|9 |");
    }

    @Test
    void score_strike10_miss_strike() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(10));
        player.spare(9, new Pins(9));
        player.spare(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|9|X |");
    }

    @Test
    void score_strike9_miss_strike2() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(9));
        player.spare(9, new Pins(10));
        player.spare(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  9|X|X |");
    }

    @Test
    void score_strike9_spare_strike() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(9));
        player.spare(9, new Pins(1));
        player.spare(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  9|/|X |");
    }

    @Test
    void score_strike11_gutter() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(10));
        player.spare(9, new Pins(10));
        player.spare(9, new Pins(0));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|- |");
    }

    @Test
    void score_strike10_gutter_strike() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(10));
        player.spare(9, new Pins(0));
        player.spare(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|-|X |");
    }

    @Test
    void score_strike9_gutter_strike2() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(0));
        player.spare(9, new Pins(10));
        player.spare(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  -|X|X |");
    }

    @Test
    void score_strike10_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(10));
        player.spare(9, new Pins(9));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|9 |");
    }

    @Test
    void score_strike9_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(9));
        player.spare(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  9|X |");
    }

    @Test
    void score_strike10_gutter() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(10));
        player.spare(9, new Pins(0));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|- |");
    }

    @Test
    void score_strike9_gutter_strike() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(0));
        player.spare(9, new Pins(10));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  -|X |");
    }

    @Test
    void score_strike9_miss_gutter() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(9));
        player.spare(9, new Pins(0));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  9|- |");
    }

    @Test
    void score_strike9_gutter_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(0));
        player.spare(9, new Pins(9));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  -|9 |");
    }

    @Test
    void score_strike9_miss_spare() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(9));
        player.spare(9, new Pins(1));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  9|/ |");
    }

    @Test
    void score_strike9_miss_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        player.stroke(9, new Pins(8));
        player.spare(9, new Pins(1));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  8|1 |");
    }

    @Test
    void score_spare() {
        player.stroke(0, new Pins(9));
        player.spare(0, new Pins(1));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  9|/ |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score_spare_not_finished() {
        player.stroke(0, new Pins(9));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  9   |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score_miss() {
        player.stroke(0, new Pins(8));
        player.spare(0, new Pins(1));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  8|1 |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score_gutter() {
        player.stroke(0, new Pins(0));
        player.spare(0, new Pins(0));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  -|- |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score_gutter_not_finished() {
        player.stroke(0, new Pins(0));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  -   |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void frame_instance_normal() {
        Frame frameNext = player.stroke(0, new Pins(10));
        assertThat(frameNext).isInstanceOf(NormalFrame.class);

        frameNext = player.stroke(1, new Pins(10));
        assertThat(frameNext).isInstanceOf(NormalFrame.class);

        frameNext = player.stroke(2, new Pins(10));
        assertThat(frameNext).isInstanceOf(NormalFrame.class);

        frameNext = player.stroke(3, new Pins(10));
        assertThat(frameNext).isInstanceOf(NormalFrame.class);

        frameNext = player.stroke(4, new Pins(10));
        assertThat(frameNext).isInstanceOf(NormalFrame.class);

        frameNext = player.stroke(5, new Pins(10));
        assertThat(frameNext).isInstanceOf(NormalFrame.class);

        frameNext = player.stroke(6, new Pins(10));
        assertThat(frameNext).isInstanceOf(NormalFrame.class);

        frameNext = player.stroke(7, new Pins(10));
        assertThat(frameNext).isInstanceOf(NormalFrame.class);

        frameNext = player.stroke(8, new Pins(10));
        assertThat(frameNext).isInstanceOf(FinalFrame.class);
    }

    @Test
    void score_strike_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(8));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  8   |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score_strike_spare() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(8));
        player.spare(1, new Pins(2));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  8|/ |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score_strike_spare_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(8));
        player.spare(1, new Pins(2));
        player.stroke(2, new Pins(8));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  8|/ |  8   |      |      |      |      |      |      |      |");
    }

    @Test
    void score_strike_spare_miss_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(8));
        player.spare(1, new Pins(2));
        player.stroke(2, new Pins(8));
        player.spare(2, new Pins(1));
        assertThat(Grid.score(player))
                .isEqualTo("|  aaa |  X   |  8|/ |  8|1 |      |      |      |      |      |      |      |");
    }
}
