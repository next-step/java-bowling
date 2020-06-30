package bowling.game.frame;

import bowling.game.frame.FinalPitches;
import bowling.game.frame.NormalPitches;
import bowling.game.frame.Pitches;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalPitchesTest {

    @DisplayName("첫 투구가 Strike가 아니면 그 프레임은 투구 기회가 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9})
    void firstBowlIsNotStrikeRemainChance(int firstPinCount) {
        Pitches normalPitches = new NormalPitches();

        normalPitches.throwBall(firstPinCount);

        assertThat(normalPitches.hasChance()).isEqualTo(true);
    }

    @DisplayName("첫 투구가 Strike면 그 프레임은 더 이상 투구 기회가 없다.")
    @Test
    void firstBowlIsStrikeNotRemainChance() {
        Pitches normalPitches = new NormalPitches();

        normalPitches.throwBall(10);

        assertThat(normalPitches.hasChance()).isEqualTo(false);
    }

    @DisplayName("2개의 투구를 모두 완료하면 더 이상 투구 기회가 없다.")
    @ParameterizedTest
    @CsvSource({"9, 1", "8, 1", "0, 7", "0, 0"})
    void bowlTwiceNotRemainChance(int firstPinCount, int nextPinCount) {
        Pitches pitches = new NormalPitches();

        pitches.throwBall(firstPinCount);
        pitches.throwBall(nextPinCount);

        assertThat(pitches.hasChance()).isEqualTo(false);
    }

    @DisplayName("전체 투구 결과를 반환한다.")
    @Test
    void getPitchesPinCounts() {
        Pitches pitches = new FinalPitches();

        pitches.throwBall(10);
        List<Integer> pinCounts = pitches.getPitchesPinCounts();

        assertThat(pinCounts).containsExactly(10);
    }
}
