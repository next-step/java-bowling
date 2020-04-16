package bowling.domain;

import org.junit.jupiter.api.Test;

class BowlingGameTest {

    @Test
    public void play_success() throws Exception {
        //given
        Player player = new Player("aa");
        BowlingGame bowlingGame = new BowlingGame(player);

        //when
        bowlingGame = bowlingGame.play(1);
        bowlingGame = bowlingGame.play(1);
        bowlingGame = bowlingGame.play(1);
        bowlingGame = bowlingGame.play(1);
        bowlingGame = bowlingGame.play(1);

        //then
    }
}
