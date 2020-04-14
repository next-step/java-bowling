package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class BowlingGameTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("jjy");
    }

    @Test
    @DisplayName("볼링게임 생성 테스트")
    void createBowlingGameTest() {
        assertThatCode(
                () -> new BowlingGame(player)
        ).doesNotThrowAnyException();
    }
}
