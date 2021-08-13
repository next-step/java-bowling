package bowling.domain.scoreboard;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerGameIndex;
import bowling.domain.player.PlayerName;
import bowling.domain.score.TurnScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class RotationPlayerOrderStrategyTest {
    @CsvSource(value = {
            "A,B,C|0|A",
            "A,B,C|1|B",
            "A,B,C|3|A",
            "A,B,C|4|B"
    }, delimiter = '|')
    @DisplayName("3명의 플레이어로 번갈아가며 순서가 변경 되는지 테스트")
    @ParameterizedTest
    void currentPlayer(String strPlayerName, int checkoutSize, String correctPlayerName) {
        Map<Player, Frames> playerEachFrames = toPlayerEachFrames(strPlayerName);

        TurnScore strikeScore = TurnScore.of(10);

        PlayerOrderStrategy playerOrderStrategy = new RotationPlayerOrderStrategy(playerEachFrames);
        for (int i = 0; i < checkoutSize; i++) {
            playerEachFrames.get(
                    playerOrderStrategy.currentPlayer()
            ).bowl(strikeScore);
            playerOrderStrategy.checkout();
        }

        assertThat(
                playerOrderStrategy.currentPlayer().name()
        ).isEqualTo(
                new PlayerName(correctPlayerName)
        );
    }

    private Map<Player, Frames> toPlayerEachFrames(String strPlayerNames) {
        String[] splitPlayerNames = strPlayerNames.split(",");

        return IntStream.range(0, splitPlayerNames.length)
                .boxed()
                .collect(
                        Collectors.toMap(
                                index -> new Player(new PlayerName(splitPlayerNames[index]), new PlayerGameIndex(index)),
                                index -> new Frames()
                        )
                );
    }
}