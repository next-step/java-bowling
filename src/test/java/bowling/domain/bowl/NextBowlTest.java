package bowling.domain.bowl;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NextBowlTest {


    @DisplayName("직전에 넘어뜨린 핀이 10일 경우 예외를 던진다.")
    @Test
    void create_illegalPin_throwsException() {
        assertThatThrownBy(() -> new NextBowl(10))
                .isInstanceOf(BowlCreationException.class);
    }

    @DisplayName("스페어")
    @ParameterizedTest(name = "[{index}] previousHitCount: {0}, hitCount: {1}")
    @CsvSource({
            "0, 10",
            "3, 7",
            "5, 5",
    })
    void pitch_spare(int previousHitCount, int hitCount) {
        assertThat(pitchTwoBowls(previousHitCount, hitCount)).isInstanceOf(SpareBowl.class);
    }

    @DisplayName("미스")
    @ParameterizedTest(name = "[{index}] previousHitCount: {0}, hitCount: {1}")
    @CsvSource({
            "0, 4",
            "3, 6",
            "1, 0"
    })
    void pitch_miss(int previousHitCount, int hitCount) {
        assertThat(pitchTwoBowls(previousHitCount, hitCount)).isInstanceOf(MissBowl.class);
    }

    @DisplayName("거터")
    @Test
    void pitch_gutter() {
        assertThat(pitchTwoBowls(0, 0)).isInstanceOf(GutterBowl.class);
    }

    private Bowl pitchTwoBowls(int previousHitCount, int hitCount) {
        Bowl bowl = new NextBowl(previousHitCount);
        return bowl.pitch(pin(hitCount));
    }

    private static Pin pin(int hitCount) {
        return Pin.from(hitCount);
    }

}
