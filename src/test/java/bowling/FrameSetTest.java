package bowling;

import bowling.domain.set.*;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameSetTest {

    @Test
    void hitCountWhenIsOverRange() {
        FrameSet frameSet = NormalFrameSet.createFirst();
        assertThatIllegalArgumentException().isThrownBy(() -> frameSet.play(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> frameSet.play(11));
    }

    @Test
    void frameSetPlayCountWhenIsOverRange() {
        State ready = new Ready();
        assertThatIllegalArgumentException().isThrownBy(() -> BaseFrameSet.create(0, ready));
        assertThatIllegalArgumentException().isThrownBy(() -> BaseFrameSet.create(12, ready));
    }

    @Test
    void normalSetPlayCountWhenIsOverRange() {
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrameSet.create(0));
        assertThatIllegalArgumentException().isThrownBy(() -> NormalFrameSet.create(10));
    }

    @Test
    void toLastSet() {
        FrameSet frameSet = NormalFrameSet.create(NormalFrameSet.END_SET_PLAY_COUNT);
        frameSet.play(10);
        assertThat(frameSet.getNext()).isInstanceOf(LastFrameSet.class);
    }

    @Test
    void toBonusSet() {
        FrameSet spareSet = LastFrameSet.create();
        spareSet.play(1);
        spareSet.play(9);
        assertThat(spareSet.getNext()).isInstanceOf(BonusFrameSet.class);

        FrameSet strikeSet = LastFrameSet.create();
        strikeSet.play(10);
        assertThat(strikeSet.getNext()).isInstanceOf(BonusFrameSet.class);
    }

    @Test
    void isGameEnd() {
        FrameSet normalSet = NormalFrameSet.create(NormalFrameSet.END_SET_PLAY_COUNT);
        assertThat(normalSet.isEndedGame()).isFalse();

        FrameSet lastMissSet = LastFrameSet.create();
        lastMissSet.play(4);
        lastMissSet.play(5);
        assertThat(lastMissSet.isEndedGame()).isTrue();

        FrameSet bonusSet = BonusFrameSet.create();
        bonusSet.play(10);
        assertThat(bonusSet.isEndedGame()).isTrue();
    }

    @Test
    void playStrike() {
        FrameSet firstSet = NormalFrameSet.create(1);
        firstSet.play(10);

        FrameSet nextSet = firstSet.getNext();
        assertThat(nextSet.getPlayCount()).isEqualTo(2);
    }

    @Test
    void playExceptStrike() {
        FrameSet firstSet = NormalFrameSet.create(1);
        firstSet.play(1);
        firstSet.play(0);

        FrameSet nextSet = firstSet.getNext();
        assertThat(nextSet.getPlayCount()).isEqualTo(2);
    }
}
