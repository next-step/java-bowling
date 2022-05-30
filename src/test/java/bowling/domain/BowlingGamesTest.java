package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingGamesTest {
    @Test
    @DisplayName("볼링게임들에 대한 객체 생성을 확인한다")
    void checkedBowlingGamesObjectGenerate() {
        // given
        List<Player> players = Arrays.asList(new Player("AAA"), new Player("BBB"));

        // when
        BowlingGames bowlingGames = BowlingGames.create(players);

        // then
        assertAll(
                () -> assertThat(bowlingGames).isNotNull(),
                () -> assertThat(bowlingGames.isNextPitching()).isTrue(),
                () -> assertThat(bowlingGames.getValues()).hasSize(2),
                () -> assertThat(bowlingGames.getFrameNumber().getValue()).isEqualTo(FrameNumber.first().getValue())
        );
    }
}