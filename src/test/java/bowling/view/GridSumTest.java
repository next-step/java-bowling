package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-28 오후 3:48
 * Developer : Seo
 */
class GridSumTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("aaa");
    }

    @Test
    void sum() {
        assertThat(Grid.sum(player))
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike() {
        player.stroke(0, new Pins(10));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike2() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike3() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike4() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |  60  |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike5() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |  60  |  90  |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike6() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike7() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |      |      |      |      |      |");
    }

    @Test
    void sum_strike8() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |      |      |      |      |");
    }

    @Test
    void sum_strike9() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(10));
        player.stroke(3, new Pins(10));
        player.stroke(4, new Pins(10));
        player.stroke(5, new Pins(10));
        player.stroke(6, new Pins(10));
        player.stroke(7, new Pins(10));
        player.stroke(8, new Pins(10));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |      |      |      |");
    }

    @Test
    void sum_strike10() {
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
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |      |      |");
    }

    @Test
    void sum_strike11() {
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
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |      |");
    }

    @Test
    void sum_strike12() {
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
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  300 |");
    }

    @Test
    void sum_strike2_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(9));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  29  |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike2_gutter() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(10));
        player.stroke(2, new Pins(0));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  20  |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike_spare() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(8));
        player.spare(1, new Pins(2));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  20  |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike_spare_miss() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(8));
        player.spare(1, new Pins(2));
        player.stroke(2, new Pins(8));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  20  |  38  |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum_strike_spare_miss2() {
        player.stroke(0, new Pins(10));
        player.stroke(1, new Pins(8));
        player.spare(1, new Pins(2));
        player.stroke(2, new Pins(8));
        player.spare(2, new Pins(1));
        assertThat(Grid.sum(player))
                .isEqualTo("|      |  20  |  38  |  47  |      |      |      |      |      |      |      |");
    }
}
