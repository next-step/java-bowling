package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @DisplayName("Player 생성 테스트")
    @Test
    void create() {
        Players players = Players.of(Arrays.asList(Player.from("syd"), Player.from("kjy"), Player.from("pjs")));

        assertThat(players).isEqualTo(Players.of(Arrays.asList(Player.from("syd"), Player.from("kjy"), Player.from("pjs"))));
    }
}
