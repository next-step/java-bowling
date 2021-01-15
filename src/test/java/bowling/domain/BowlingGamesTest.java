package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGamesTest {

    private Players players;

    @BeforeEach
    void init(){
        String[] playerNames = {"AAA", "BBB"};
        players = Players.from(playerNames);
    }

    @DisplayName("BowlingGames 생성 테스트")
    @Test
    void bowlingGamesConstructorTest(){

        BowlingGames bowlingGames = BowlingGames.from(players);

        // then
        assertThat(bowlingGames.getBowlingGames())
                .containsExactly(BowlingGame.of(Player.from("AAA"), Frames.of()), BowlingGame.of(Player.from("BBB"), Frames.of()));

    }
}
