package bowling.domain;

import bowling.domain.engine.frame.FrameNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    private Players players;
    private String firstPlayerName;
    private String secondPlayerName;

    @BeforeEach
    void setUp() {
        firstPlayerName = "aaa";
        secondPlayerName = "bbb";
        players = Players.of(Arrays.asList(firstPlayerName, secondPlayerName));
    }

    @Test
    @DisplayName("특정 프레임에서 플레이 하지 않은 첫 번째 사용자를 가져온다.")
    void getFirstPlayerNotPlayedInSomeFrame() {
        FrameNumber frameNumber = FrameNumber.firstFrame();

        Optional<Player> player = players.getFirstPlayerPlayingIn(frameNumber);

        assertThat(player.get().exportPlayerName()).isEqualTo(firstPlayerName);
        player.get().roll(RollResult.of(10));

        player = players.getFirstPlayerPlayingIn(frameNumber);

        assertThat(player.get().exportPlayerName()).isEqualTo(secondPlayerName);
        player.get().roll(RollResult.of(10));

        player = players.getFirstPlayerPlayingIn(frameNumber);
        assertThat(player.isPresent()).isFalse();
    }
    
}
