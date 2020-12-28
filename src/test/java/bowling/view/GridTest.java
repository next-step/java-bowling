package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Pins;
import bowling.view.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GridTest {
    private Frames frames;
    private Player player;
    private int playerIndex;
    private int frameIndex;

    @BeforeEach
    void setUp() {
        frames = new Frames();
        player = new Player("aaa");
        playerIndex = 0;
        frameIndex = 0;
    }

    @Test
    void header() {
        assertThat(Grid.header())
                .isEqualTo("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    @Test
    void score1() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

//        System.out.println(Grid.score(frames, player, playerIndex));
        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score2() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 1; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |      |      |      |      |      |      |      |      |");
    }

    @Test
    void score3() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 2; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |      |      |      |      |      |      |      |");
    }

    @Test
    void score4() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 3; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |      |      |      |      |      |      |");
    }

    @Test
    void score5() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 4; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |      |      |      |      |      |");
    }

    @Test
    void score6() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 5; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |      |      |      |      |");
    }

    @Test
    void score7() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 6; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |      |      |      |");
    }

    @Test
    void score8() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 7; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |      |      |");
    }

    @Test
    void score9() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |      |");
    }

    @Test
    void score10() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |");
    }

    @Test
    void score10_2() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(10));

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  XX  |");
    }

    @Test
    void score10_3() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(10));

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  XXX |");
    }

    @Test
    void score10_4() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(9));

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  XX|9 |");
    }

    @Test
    void score10_5() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(8));

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  1|8 |");
    }

    @Test
    void score10_6() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(9));

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  1|/ |");
    }

    @Test
    void score10_7() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(9));
        frame.spare(playerIndex, new Pins(10));

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  1|/|X |");
    }

    @Test
    void score10_8() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(9));
        frame.spare(playerIndex, new Pins(9));

        assertThat(Grid.score(frames, player, playerIndex))
                .isEqualTo("|  aaa |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  1|/|9 |");
    }

    @Test
    void sum() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum2() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 1; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum3() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 2; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum4() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 3; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |      |      |      |      |      |      |      |      |");
    }

    @Test
    void sum5() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 4; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |      |      |      |      |      |      |      |");
    }

    @Test
    void sum6() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 5; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |      |      |      |      |      |      |");
    }

    @Test
    void sum7() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 6; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |      |      |      |      |      |");
    }

    @Test
    void sum8() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 7; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |      |      |      |      |");
    }

    @Test
    void sum9() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |      |      |      |");
    }

    @Test
    void sum10() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |      |      |");
    }

    @Test
    void sum11() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(10));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |      |");
    }

    @Test
    void sum12() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(10));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  300 |");
    }

    @Test
    void sum13() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(9));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  299 |");
    }

    @Test
    void sum14() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(8));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  298 |");
    }

    @Test
    void sum15() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        for (int i = 0; i < 8; i++) {
            frames.next(frameIndex++);
            frame = frames.get(frameIndex);
            frame.stroke(playerIndex, new Pins(10));
        }

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(9));
        frame.spare(playerIndex, new Pins(8));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  298 |");
    }
}
