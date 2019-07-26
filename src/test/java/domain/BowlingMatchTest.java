package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingMatchTest {
    @Test
    void 플레이어들을_입력하여_볼링경기를_생성한다() {
        //given
        Player player1 = Player.from("hjs");
        Player player2 = Player.from("pje");
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));

        //when
        BowlingMatch bowlingMatch = BowlingMatch.start(players);

        //then
        assertThat(bowlingMatch).isNotNull();
    }
}
