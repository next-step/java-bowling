package bowling.domain.scoreboard;

import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
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
        List<Player> players = toPlayers(strPlayerName);

        PlayerOrderStrategy playerOrderStrategy = new RotationPlayerOrderStrategy();
        for (int i = 0; i < checkoutSize; i++) {
            playerOrderStrategy.checkout();
        }

        assertThat(
                playerOrderStrategy.currentPlayer(players).name()
        ).isEqualTo(
                new PlayerName(correctPlayerName)
        );
    }

    private List<Player> toPlayers(String strPlayerNames) {
        String[] splitPlayerNames = strPlayerNames.split(",");

        return IntStream.range(0, splitPlayerNames.length)
                .boxed()
                .map(index ->
                        new Player(
                                new PlayerName(
                                        splitPlayerNames[index]
                                ), index)
                )
                .collect(Collectors.toList());
    }
}