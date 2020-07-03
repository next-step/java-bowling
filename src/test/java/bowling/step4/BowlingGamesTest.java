package bowling.step4;

import bowling.step4.domain.process.BowlingGame;
import bowling.step4.domain.process.BowlingGames;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGamesTest {

    BowlingGames bowlingGames;
    BowlingGames bowlingGames2;

    @BeforeEach
    void setup(){
        List<String> playerNames = Lists.list("POB","JDK");
        bowlingGames = BowlingGames.of(playerNames);

        List<Integer> pitches = Lists.list(10,10,10,10,10,10,10,10,10,9,1,5);
        BowlingGame bowlingGame2 = BowlingGame.of("BJR");
        BowlingGame bowlingGame3 = BowlingGame.of("JDK");
        for (int pitch : pitches){
            bowlingGame2 = bowlingGame2.play(pitch);
            bowlingGame3 = bowlingGame3.play(pitch);
        }
        bowlingGames2 = Lists.list(bowlingGame2, bowlingGame3)
                             .stream()
                             .collect(collectingAndThen(Collectors.toList(),BowlingGames::new));
    }

    @Test
    @DisplayName("생성 테스트")
    void create() {
        assertThat(bowlingGames.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("getter 테스트")
    void get_bowlingGame() {
        bowlingGames2.getBowlingGames();
        assertThat(bowlingGames2.getBowlingGames().size()).isEqualTo(2);
        assertThat(bowlingGames2.getBowlingGames().get(0).getPlayerName()).isEqualTo("BJR");
        assertThat(bowlingGames2.getBowlingGames().get(1).getPlayerName()).isEqualTo("JDK");
    }

    @Test
    @DisplayName("게임오버 테스트")
    void gameover_test() {
        assertThat(bowlingGames2.isGamesOver()).isTrue();
    }
}
