package bowling.step4.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersFramesTest {

    @DisplayName("Players로 부터 PlayersFrames가 정상적으로 생성되는지 확인")
    @Test
    void playersFrames_생성_테스트 () {
        Players players = Players.of(asList(Player.valueOf("p01"), Player.valueOf("p02")));
        PlayersFrames playersFrames = PlayersFrames.init(players);
        assertEquals(
            players.stream().map(Player::toString).collect(joining(";")),
            playersFrames.stream().map(PlayerFrames::getPlayerName).collect(joining(";"))
        );
        assertEquals(
            players.stream().count(),
            playersFrames.stream().count()
        );
    }
}