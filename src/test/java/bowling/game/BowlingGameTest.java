package bowling.game;

import bowling.player.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    @DisplayName("현재 투구할 사람의 이름을 반환한다.")
    @Test
    void getCurrentPlayerName() {
        BowlingGame bowlingGame = new BowlingGame(Arrays.asList(new Player("LMH"),
                new Player("HGD")));

        assertThat(bowlingGame.getCurrentPlayerName().toString()).isEqualTo("LMH");

        bowlingGame.bowlCurrentPlayer(10);

        assertThat(bowlingGame.getCurrentPlayerName().toString()).isEqualTo("HGD");
    }

    @DisplayName("게임이 끝났는지 반환한다.")
    @ParameterizedTest
    @CsvSource({"3, false", "24, true"})
    void isEndGame(int bowlCount, boolean result) {
        BowlingGame bowlingGame = new BowlingGame(Arrays.asList(new Player("LMH"),
                new Player("HGD")));

        for (int i = 0; i < bowlCount; i++) {
            bowlingGame.bowlCurrentPlayer(10);
        }

        assertThat(bowlingGame.isEndGame()).isEqualTo(result);
    }
}