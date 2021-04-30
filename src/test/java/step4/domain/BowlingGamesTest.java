package step4.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;

public class BowlingGamesTest {
    BowlingGames bowlingGames;

    @BeforeEach
    void setUp() {
        bowlingGames = new BowlingGames(Arrays.asList(new BowlingGame("KSB"), new BowlingGame("KBS")));
    }

    @Test
    @DisplayName("모든 플레이어가 게임을 완료했다면, 참을 반환한다.")
    public void isAllFinished() throws Exception {
        //given
        BowlingGame firstBowlingGame = bowlingGames.bowlingGames().get(0);
        BowlingGame secondBowlingGame = bowlingGames.bowlingGames().get(1);
        for (int i = 0; i < 12; i++) {
            firstBowlingGame.throwBowl("10");
            secondBowlingGame.throwBowl("10");
        }

        //when
        boolean isAllFinished = bowlingGames.isAllFinished();

        then(isAllFinished).isTrue();
    }
}
