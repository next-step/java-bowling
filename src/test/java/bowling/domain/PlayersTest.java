package bowling.domain;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    @Test
    @DisplayName("생성")
    void create() {
        assertDoesNotThrow(() -> new Players(List.of("PJS", "KYJ")));
    }

    @Test
    @DisplayName("현재 플레이어")
    void currentPlayer() {
        Players players = new Players(List.of("PJS", "KYJ"));
        players.roll("10");

        Player player = players.currentPlayer();

        assertEquals(player.getName(), Name.of("KYJ"));
    }

    @Test
    @DisplayName("퍼펙트 게임")
    void isFinish() {
        Players players = new Players(List.of("PJS"));

        perfectGame(players);

        assertTrue(players.isFinish());
    }

    public void perfectGame(final Players players) {
        IntStream.range(0, 12)
                .mapToObj(i -> "10")
                .forEach(players::roll);
    }
}
