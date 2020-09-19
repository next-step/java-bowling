package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.BDDAssertions.then;

class BowlingGameTest {

    @Test
    @DisplayName("참가자 명 반환 검증")
    void getPlayer() {
        String expected = "abc";
        BowlingGame bowlingGame = BowlingGame.of(2, expected);

        then(bowlingGame.getPlayer()).isEqualTo(Player.of(expected));
    }

    @ParameterizedTest
    @CsvSource(value = {"2", "3", "4"})
    @DisplayName("경기 종료 여부 검증")
    void isFinished(int totalFrames) {
        BowlingGame bowlingGame = BowlingGame.of(totalFrames, "abc");

        IntStream.range(0, totalFrames + 2).forEach(index -> {
            then(bowlingGame.isFinished()).isFalse();
            bowlingGame.bowl(Pin.ofMax());
        });

        then(bowlingGame.isFinished()).isTrue();
    }
}
