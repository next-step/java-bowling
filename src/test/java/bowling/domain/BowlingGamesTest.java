package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTest {

    private BowlingGames bowlingGames;

    @BeforeEach
    void setUp() {
        bowlingGames = new BowlingGames(Arrays.asList("asd", "qwe"));
    }

    @DisplayName("현재 볼링게임을 하는 플레이어가 스트라이크를 하면 해당 플레이어의 턴(프레임)은 끝나고 다음 선수 차례가 된다.")
    @Test
    void currentPlayerFrames_strike() {
        BowlingGame currentGame = bowlingGames.currentPlayerFrames();
        bowlingGames.pitch(10);
        assertThat(currentGame.player()).isNotEqualTo(bowlingGames.currentPlayerFrames().player());
    }

    @DisplayName("현재 볼링게임을 하는 플레이어가 두번 투구를 던지면 해당 플레이어의 턴(프레임)은 끝나고 다음 선수 차례가 된다.")
    @ParameterizedTest
    @CsvSource(value = {"0:0", "5:5"}, delimiter = ':')
    void currentPlayerFrames_end(int first, int second) {
        BowlingGame currentGame = bowlingGames.currentPlayerFrames();
        bowlingGames.pitch(first);
        bowlingGames.pitch(second);
        assertThat(currentGame.player()).isNotEqualTo(bowlingGames.currentPlayerFrames().player());
    }

    @DisplayName("현재 볼링게임을 하는 플레이어의 턴에서 스트라이크가 아니고 한번 투구하면 현재 플레이어 턴은 끝나지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void currentPlayerFrames_not_end(int countOfPins) {
        BowlingGame currentGame = bowlingGames.currentPlayerFrames();
        bowlingGames.pitch(countOfPins);
        assertThat(currentGame.player()).isEqualTo(bowlingGames.currentPlayerFrames().player());
    }

}