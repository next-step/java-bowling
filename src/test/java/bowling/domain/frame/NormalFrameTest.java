package bowling.domain.frame;

import bowling.domain.score.Pins;
import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
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
    private int userIndex;

    @BeforeEach
    void setUp() {
        userIndex = 0;
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
    void bowl_getLastStateFrameScore() {
        frame.bowl(new Pins(10));
        assertThat(frame.getLastStateFrameScore()).isEqualTo(10);
    }

    @Test
    void bowl_getLastStateFirstScore() {
        frame.bowl(new Pins(10));
        assertThat(frame.getLastStateFirstScore()).isEqualTo(10);
    }

    @Test
    void bowl_getLastStateSecondScore() {
        frame.bowl(new Pins(10));
        assertThat(frame.getLastStateSecondScore()).isZero();
    }

    @Test
    void bowl_getLastStateSymbol() {
        frame.bowl(new Pins(10));
        assertThat(frame.getLastStateSymbol()).isEqualTo("X");
    }

    @Test
    void bowl_getFrameScore() {
        frame.bowl(new Pins(10));
        assertThat(frame.getFrameScore(userIndex)).isEqualTo(10);
    }

    @Test
    void bowl_getFirstScore() {
        frame.bowl(new Pins(10));
        assertThat(frame.getFirstScore(userIndex)).isEqualTo(10);
    }

    @Test
    void bowl_getSecondScore() {
        frame.bowl(new Pins(10));
        assertThat(frame.getSecondScore(userIndex)).isZero();
    }

    @Test
    void bowl_getSymbol() {
        frame.bowl(new Pins(10));
        assertThat(frame.getSymbol(userIndex)).isEqualTo("X");
    }

    @Test
    void secondBowl_getLastStateFrameScore() {
        frame.bowl(new Pins(0));
        frame.secondBowl(userIndex, new Gutter(new Pins(0)), new Pins(1));
        assertThat(frame.getLastStateFrameScore()).isEqualTo(1);
    }

    @Test
    void secondBowl_getLastStateFirstScore() {
        frame.bowl(new Pins(0));
        frame.secondBowl(userIndex, new Gutter(new Pins(0)), new Pins(1));
        assertThat(frame.getLastStateFirstScore()).isZero();
    }

    @Test
    void secondBowl_getLastStateSecondScore() {
        frame.bowl(new Pins(0));
        frame.secondBowl(userIndex, new Gutter(new Pins(0)), new Pins(1));
        assertThat(frame.getLastStateSecondScore()).isEqualTo(1);
    }

    @Test
    void secondBowl_getFrameScore() {
        frame.bowl(new Pins(0));
        frame.secondBowl(userIndex, new Gutter(new Pins(0)), new Pins(1));
        assertThat(frame.getLastStateFrameScore()).isEqualTo(1);
    }

    @Test
    void secondBowl_getFirst() {
        frame.bowl(new Pins(0));
        frame.secondBowl(userIndex, new Gutter(new Pins(0)), new Pins(1));
        assertThat(frame.getLastStateFirstScore()).isZero();
    }

    @Test
    void symbol_strike() {
        frame.bowl(new Pins(10));
        frame.secondBowl(userIndex, new Strike(), new Pins(0));
        assertThat(frame.getSymbol(userIndex)).isEqualTo("X");
    }

    @Test
    void symbol_spare() {
        frame.bowl(new Pins(9));
        frame.secondBowl(userIndex, new Miss(new Pins(9)), new Pins(1));
        assertThat(frame.getSymbol(userIndex)).isEqualTo("9|/");
    }

    @Test
    void symbol_miss() {
        frame.bowl(new Pins(8));
        frame.secondBowl(userIndex, new Miss(new Pins(8)), new Pins(1));
        assertThat(frame.getSymbol(userIndex)).isEqualTo("8|1");
    }

    @Test
    void symbol_gutter() {
        frame.bowl(new Pins(0));
        frame.secondBowl(userIndex, new Gutter(new Pins(0)), new Pins(1));
        assertThat(frame.getSymbol(userIndex)).isEqualTo("-|1");
    }

    @Test
    void symbol_gutter2() {
        frame.bowl(new Pins(1));
        frame.secondBowl(userIndex, new Miss(new Pins(1)), new Pins(0));
        assertThat(frame.getSymbol(userIndex)).isEqualTo("1|-");
    }

    @Test
    void symbol_gutter3() {
        frame.bowl(new Pins(0));
        frame.secondBowl(userIndex, new Gutter(new Pins(0)), new Pins(0));
        assertThat(frame.getSymbol(userIndex)).isEqualTo("-|-");
    }

    @Test
    void validate() {
        frame.bowl(new Pins(10));
        assertThatThrownBy(() -> frame.secondBowl(0, new Strike(), new Pins(10)))
                .withFailMessage("잘못된 2구 핀입니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateUserIndex() {
        frame.bowl(new Pins(0));
        assertThatThrownBy(() -> frame.secondBowl(1, new Gutter(new Pins(0)), new Pins(0)))
                .withFailMessage("사용자를 확인해주십시요. 현재 사용자 번호 : %d", userIndex)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
