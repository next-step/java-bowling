package bowling.bowlinggame;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGamesTest {
    private BowlingGames bowlingGames;
    @BeforeEach
    void setUp() {
        bowlingGames = BowlingGames.of(Arrays.asList(new Player("LDC"), new Player("EEE")));
    }

    @DisplayName("볼링 게임 컬렉션 생성")
    @Test
    void create() {
        assertThatCode(() -> BowlingGames.of(Arrays.asList(new Player("LDC"), new Player("EEE"))));
    }

    @DisplayName("모든 게임이 종료되었는지 판단")
    @Test
    void isOverAllGame() {
        addBowlingGames(bowlingGames.getBowlingGames());

        assertThat(bowlingGames.isOverAllGames()).isTrue();
    }

    private void addBowlingGames(List<BowlingGame> bowlingGames) {
        for (BowlingGame bowlingGame : bowlingGames) {
            addBowlingGame(bowlingGame);
        }
    }

    private void addBowlingGame(BowlingGame bowlingGame) {
        for (int i = 0; i < 10; i++) {
            bowlingGame.addNextFrame();
        }
    }

    @DisplayName("게임진행이 가능한지 판단")
    @Test
    void isPlayingGames() {
        addAllFrame();
        addNineFrame();

        assertThat(bowlingGames.isOverAllGames()).isFalse();
    }

    private void addNineFrame() {
        for (int i = 0; i < 9; i++) {
            bowlingGames.addNextFrame(1);
        }
    }

    private void addAllFrame() {
        for (int i = 0; i < 10; i++) {
            bowlingGames.addNextFrame(0);
        }
    }
}
