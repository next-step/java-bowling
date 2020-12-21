package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BowlingGameTest {
    @Test
    public void bowlingGameTest() {
        List<PlayerName> playerNameList = Arrays.asList(PlayerName.valueOf("aaa"), PlayerName.valueOf("bbb"), PlayerName.valueOf("ccc"));
        PlayerNames playerNames = PlayerNames.of(playerNameList);
        BowlingGame bowlingGame = BowlingGame.init(playerNames);

        bowlingGame.setKnockDownPins(KnockDownPins.valueOf(10));
        Player currentPlayer = bowlingGame.getCurrentPlayer();
        assertThat(currentPlayer.getPlayerName()).isEqualTo(PlayerName.valueOf("bbb"));
    }
}
