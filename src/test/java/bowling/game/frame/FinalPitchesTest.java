package bowling.game.frame;

import bowling.game.frame.FinalPitches;
import bowling.game.frame.Pitches;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalPitchesTest {

    @DisplayName("마지막 프레임은 첫번째 투구가 Strike 면 투구 기회가 있다.")
    @Test
    void firstStrikeHasRemainChance() {
        Pitches finalPitches = new FinalPitches();

        finalPitches.throwBall(10);

        assertThat(finalPitches.hasChance()).isTrue();
    }

    @DisplayName("마지막 프레임은 두번째 투구가 Spare 면 투구 기회가 있다.")
    @Test
    void spareHasRemainChance() {
        Pitches finalPitches = new FinalPitches();

        finalPitches.throwBall(8);
        finalPitches.throwBall(2);

        assertThat(finalPitches.hasChance()).isTrue();
    }

    @DisplayName("마지막 프레임은 두번째 투구가 Spare가 아니면 투구 기회가 없다")
    @Test
    void notSpareHasNotRemainChance() {
        Pitches finalPitches = new FinalPitches();

        finalPitches.throwBall(8);
        finalPitches.throwBall(1);

        assertThat(finalPitches.hasChance()).isFalse();
    }

    @DisplayName("전체 투구 결과를 반환한다.")
    @Test
    void getPitchesPinCounts() {
        Pitches finalPitches = new FinalPitches();

        finalPitches.throwBall(10);
        finalPitches.throwBall(1);
        finalPitches.throwBall(9);

        List<Integer> pinCounts = finalPitches.getPitchesPinCounts();

        assertThat(pinCounts).containsExactly(10, 1, 9);
    }
}
