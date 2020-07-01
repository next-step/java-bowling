package bowling.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("볼링 게임 테스트")
public class BowlingGameTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = Player.of("ABC");
    }

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> BowlingGame.newInstance(player)).doesNotThrowAnyException();
    }

    @DisplayName("투구 테스트")
    @Test
    public void bowlTest() {
        BowlingGame bowlingGame = BowlingGame.newInstance(player);
        assertThatCode(() -> bowlingGame.bowl(10)).doesNotThrowAnyException();
    }


    @DisplayName("게임 투구 테스트 - 에러")
    @Test
    public void bowlAbnormalTest() {
        BowlingGame bowlingGame = BowlingGame.newInstance(player);
        IntStream.range(0, 12)
            .forEach(i -> bowlingGame.bowl(10));

        assertThatIllegalStateException()
            .isThrownBy(() -> bowlingGame.bowl(10))
            .withMessageContaining("모든 프레임이 종료되었습니다.");
    }
}