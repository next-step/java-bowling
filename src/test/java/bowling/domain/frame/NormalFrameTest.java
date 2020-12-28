package bowling.domain.frame;

import bowling.domain.score.Pins;
import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created : 2020-12-24 오전 9:57
 * Developer : Seo
 */
class NormalFrameTest {
    private Frame frame;
    private int playerIndex;

    @BeforeEach
    void setUp() {
        playerIndex = 0;
        frame = new NormalFrame(1);
    }

    @Test
    void init() {
        assertThat(frame).isNotNull().isInstanceOf(NormalFrame.class);
    }

    @Test
    void next_normal() {
        assertThat(frame.next()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void next_final() {
        frame = new NormalFrame(8);
        assertThat(frame.next()).isInstanceOf(FinalFrame.class);
    }

    @Test
    void stroke() {
        frame.stroke(playerIndex,new Pins(10));
        assertThat(frame.getLastState()).isInstanceOf(Strike.class);
        assertThat(frame.getState(playerIndex)).isInstanceOf(Strike.class);
    }

    @Test
    void spare() {
        frame.stroke(playerIndex, new Pins(7));
        frame.spare(playerIndex, new Pins(3));
        assertThat(frame.getLastState()).isInstanceOf(Spare.class);
        assertThat(frame.getState(playerIndex)).isInstanceOf(Spare.class);
    }

//    @Test
//    void getState_throwException() {
//        assertThatThrownBy(() -> frame.getState(1))
//                .withFailMessage("사용자를 확인해주십시요. 현재 사용자 번호 : %d", playerIndex)
//                .isInstanceOf(IllegalArgumentException.class);
//    }

    @Test
    void getState() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getState(playerIndex)).isInstanceOf(Strike.class);
    }

    @Test
    void getSymbol() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getSymbol(playerIndex)).isEqualTo("X");
        assertThat(frame.getLastStateSymbol()).isEqualTo("X");
    }

    @Test
    void getFrameScore() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getFrameScore(playerIndex)).isEqualTo(10);
    }

    @Test
    void getFirstScore() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getFirstScore(playerIndex)).isEqualTo(10);
    }

    @Test
    void getSecondScore() {
        frame.stroke(playerIndex, new Pins(7));
        frame.spare(playerIndex, new Pins(3));
        assertThat(frame.getSecondScore(playerIndex)).isEqualTo(3);
    }

    @Test
    void getLastState() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getLastState()).isInstanceOf(Strike.class);
    }

    @Test
    void getLastStateFrameScore() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getLastStateFrameScore()).isEqualTo(10);
    }

    @Test
    void getLastStateFirstScore() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getLastStateFirstScore()).isEqualTo(10);
    }

    @Test
    void getLastStateSecondScore() {
        frame.stroke(playerIndex, new Pins(10));
        frame.spare(playerIndex, new Pins(0));
        assertThat(frame.getLastStateSecondScore()).isZero();
    }

    @Test
    void getLastStateSymbol() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getLastStateSymbol()).isEqualTo("X");
    }

    @Test
    void symbol_strike() {
        frame.stroke(playerIndex, new Pins(10));
        assertThat(frame.getSymbol(playerIndex)).isEqualTo("X");
    }

    @Test
    void symbol_spare() {
        frame.stroke(playerIndex, new Pins(9));
        frame.spare(playerIndex, new Pins(1));
        assertThat(frame.getSymbol(playerIndex)).isEqualTo("9|/");
    }

    @Test
    void symbol_miss() {
        frame.stroke(playerIndex, new Pins(8));
        frame.spare(playerIndex, new Pins(1));
        assertThat(frame.getSymbol(playerIndex)).isEqualTo("8|1");
    }

    @Test
    void symbol_gutter() {
        frame.stroke(playerIndex, new Pins(0));
        frame.spare(playerIndex, new Pins(1));
        assertThat(frame.getSymbol(playerIndex)).isEqualTo("-|1");
    }

    @Test
    void symbol_gutter2() {
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(0));
        assertThat(frame.getSymbol(playerIndex)).isEqualTo("1|-");
    }

    @Test
    void symbol_gutter3() {
        frame.stroke(playerIndex, new Pins(0));
        frame.spare(playerIndex, new Pins(0));
        assertThat(frame.getSymbol(playerIndex)).isEqualTo("-|-");
    }
}
