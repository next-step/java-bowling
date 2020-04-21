package bowling.game;

import bowling.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

@DisplayName("볼링 게임 테스트")
public class BowlingGameTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingGame.newInstance("ABC")).doesNotThrowAnyException();
    }

    @DisplayName("투구 테스트")
    @Test
    public void bowlTest() {
        BowlingGame bowlingGame = BowlingGame.newInstance("ABC");
        assertThatCode(() -> bowlingGame.bowl(Pin.ofMax())).doesNotThrowAnyException();
    }

    @DisplayName("게임 투구 테스트 - 에러")
    @Test
    public void bowlAbnormalTest() {
        BowlingGame bowlingGame = BowlingGame.newInstance("ABC");
        IntStream.range(0, 9)
                .forEach(i -> doStrike(bowlingGame));
        bowlingGame.bowl(Pin.ofMax());
        bowlingGame.bowl(Pin.ofMax());

        assertThatIllegalStateException()
                .isThrownBy(() -> bowlingGame.bowl(Pin.ofMax()));
    }

    private void doStrike(BowlingGame bowlingGame) {
        bowlingGame.bowl(Pin.ofMax());
        bowlingGame.prepareNextFrame();
    }
}
