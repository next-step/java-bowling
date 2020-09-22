package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.BDDAssertions.then;

class ScoreBoardTest {

    @Test
    @DisplayName("참가자 명 반환 검증")
    void getPlayer() {
        String expected = "abc";
        ScoreBoard scoreBoard = ScoreBoard.of(Player.of(expected), 2);

        then(scoreBoard.getPlayerName()).isEqualTo(Player.of(expected));
    }

    @ParameterizedTest
    @CsvSource(value = {"2", "3", "4"})
    @DisplayName("경기 종료 여부 검증")
    void isFinished(int totalFrames) {
        ScoreBoard scoreBoard = ScoreBoard.of(Player.of("abc"), totalFrames);

        IntStream.range(0, totalFrames + 1).forEach(index -> {
            then(scoreBoard.isFinished()).isFalse();
            scoreBoard.bowl(Pin.ofMax());
        });

        then(scoreBoard.isFinished()).isTrue();
    }
}
