package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class GameTypeTest {
    @ParameterizedTest
    @CsvSource(value = {
            "2:10:SPARE",
            "1:10:STRIKE",
            "2:5:MISS",
            "1:5:PENDING"
    }, delimiter = ':')
    @DisplayName("rolling 수와 득점에 따른 게임 frame 게임 상태 조회")
    void get(int size, int score, GameType gameType) {
        assertThat(GameType.get(size, score)).isEqualTo(gameType);
    }
}