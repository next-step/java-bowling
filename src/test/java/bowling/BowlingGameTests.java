package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

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
        assertThatCode(() -> bowlingGame.bowl(10)).doesNotThrowAnyException();
    }


    @DisplayName("게임 투구 테스트 - 에러")
    @Test
    public void bowlAbnormalTest() {
        BowlingGame bowlingGame = BowlingGame.newInstance("ABC");
        IntStream.range(0, 12)
                .forEach(i -> bowlingGame.bowl(10));


        assertThatIllegalStateException()
                .isThrownBy(() -> bowlingGame.bowl(10))
                .withMessageContaining("The bowling Game is Over.");
    }
}
