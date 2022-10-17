package bowling.domain;

import bowling.domain.dto.Record;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BowlingGameTest {

    @Test
    @DisplayName("퍼펙트 게임")
    void perfect_game() {
        //given
        BowlingGame bowlingGame = new BowlingGame(new PlayerName("AAA"));
        //when
        IntStream.range(0, 11)
                .forEach(i -> bowlingGame.bowl(range -> new Pin(10)));
        List<Record> gameRecord = bowlingGame.getGameRecord();
        //then
        assertAll(
                () -> assertThat(bowlingGame.isFinished()).isTrue(),
                () -> assertThat(bowlingGame.getFrameNumber()).isEqualTo(10),
                () -> assertThat(gameRecord).hasSize(10),
                () -> assertThat(bowlingGame.getNow().getValue()).isEqualTo(10)
        );

    }
}