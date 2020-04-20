package bowling.game;

import bowling.BowlingGame;
import bowling.Pin;
import bowling.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("볼링 게임 테스트")
public class BowlingGameTests {

    private BowlingGame bowlingGame = BowlingGame.newInstance("AAA", "BBB");

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingGame.newInstance("AAA", "BBB")).doesNotThrowAnyException();
    }

    @DisplayName("투구 테스트")
    @Test
    public void bowlTest() {
        assertThatCode(() -> bowlingGame.bowl(Player.of("AAA"), Pin.ofMax())).doesNotThrowAnyException();
    }


    @DisplayName("게임 투구 테스트 - 에러")
    @Test
    public void bowlAbnormalTest() {
        IntStream.range(0, 11)
                .forEach(i -> bowlingGame.bowl(Player.of("AAA"), Pin.ofMax()));


        assertThatIllegalStateException()
                .isThrownBy(() -> bowlingGame.bowl(Player.of("AAA"), Pin.ofMax()));
    }

    @DisplayName("사용자 턴 테스트")
    @Test
    public void getNextTurnTest() {
        assertThat(bowlingGame.getNextTurn()).isEqualTo(Player.of("AAA"));
        bowlingGame.bowl(Player.of("AAA"), Pin.ofMax());
        assertThat(bowlingGame.getNextTurn()).isEqualTo(Player.of("BBB"));
    }

    @DisplayName("사용자 턴 테스트2")
    @Test
    public void getNextTurnTest2() {
        assertThat(bowlingGame.getNextTurn()).isEqualTo(Player.of("AAA"));
        bowlingGame.bowl(Player.of("AAA"), Pin.of(7));
        assertThat(bowlingGame.getNextTurn()).isEqualTo(Player.of("BBB"));
    }
}
