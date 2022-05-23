package bowling.domain;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    private static final String PJS = "PJS";
    private static final String KYJ = "KYJ";
    private static Players players;

    @BeforeEach
    void setUp() {
        players = new Players();
        players.addPlayer(new Player(PJS));
        players.addPlayer(new Player(KYJ));
    }

    @Test
    void finished() {
        int bowlCount = players.size() * 10;

        for (int i = 0; i < bowlCount; i++) {
            players.bowl(Pin.of(4));
            players.bowl(Pin.of(5));
        }

        assertThat(players.finished()).isTrue();
    }

    @Test
    void currentBowlerName() {
        assertThat(players.currentBowlerName()).isEqualTo(PJS);
        players.bowl(Pin.of(10));

        assertThat(players.currentBowlerName()).isEqualTo(KYJ);
        players.bowl(Pin.of(4));
        players.bowl(Pin.of(5));

        assertThat(players.currentBowlerName()).isEqualTo(PJS);
    }
}
