package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    List<String> playerNames = Arrays.asList("JHJ", "PJH");
    TotalFrames allFrames = new TotalFrames(playerNames.size());

    @DisplayName("Players 객체 생성 성공 테스트")
    @Test
    void createTest() {
        Players players = new Players(playerNames, allFrames);
        assertThat(players).isInstanceOf(Players.class);
        assertThat(players.numberOfPlayers()).isEqualTo(playerNames.size());
    }
}
