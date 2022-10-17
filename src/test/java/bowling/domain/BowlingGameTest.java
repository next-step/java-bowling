package bowling.domain;

import bowling.domain.dto.Record;
import bowling.domain.state.StateType;
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

    @Test
    @DisplayName("퍼펙트 게임")
    void game() {
        //given
        BowlingGame bowlingGame = new BowlingGame(new PlayerName("AAA"));
        //when
        bowlingGame.bowl(range -> new Pin(5));
        List<Record> gameRecord = bowlingGame.getGameRecord();
        //then
        assertAll(
                () -> assertThat(bowlingGame.isFinished()).isFalse(),
                () -> assertThat(bowlingGame.getFrameNumber()).isEqualTo(1),
                () -> assertThat(gameRecord.get(0).getState()).isEqualTo(StateType.RUNNING),
                () -> assertThat(gameRecord.get(0).getScores()).isEqualTo(List.of(5)),
                () -> assertThat(bowlingGame.getNow().getValue()).isEqualTo(5)
        );

    }
}