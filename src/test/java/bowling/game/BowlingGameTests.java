package bowling.game;

import bowling.BowlingGame;
import bowling.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

@DisplayName("볼링 게임 테스트")
public class BowlingGameTests {

    private BowlingGame bowlingGame = BowlingGame.newInstance(Arrays.asList("AAA", "BBB"));

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingGame.newInstance(Arrays.asList("AAA", "BBB")).doesNotThrowAnyException();
    }

    @DisplayName("투구 테스트")
    @Test
    public void bowlTest() {
        assertThatCode(() -> bowlingGame.bowl("AAA", Pin.ofMax())).doesNotThrowAnyException();
    }


    @DisplayName("게임 투구 테스트 - 에러")
    @Test
    public void bowlAbnormalTest() {
        IntStream.range(0, 11)
                .forEach(i -> bowlingGame.bowl("AAA", Pin.ofMax()));


        assertThatIllegalStateException()
                .isThrownBy(() -> bowlingGame.bowl("AAA", Pin.ofMax()));
    }

    @DisplayName("사용자 턴 테스트")
    @Test
    public void getNextTurnTest() {
        assertThat(bowlingGame.getNextTurn()).isEqualsTo("AAA");
        bowlingGame.bowl("AAA", Pin.ofMax());
        assertThat(bowlingGame.getNextTurn()).isEqualsTo("BBB");
    }

    @DisplayName("사용자 턴 테스트2")
    @Test
    public void getNextTurnTest2() {
        assertThat(bowlingGame.getNextTurn()).isEqualsTo("AAA");
        bowlingGame.bowl("AAA", Pin.of(7));
        assertThat(bowlingGame.getNextTurn()).isEqualsTo("BBB");
    }
}
