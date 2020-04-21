package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PlayersTest {

    @Test
    @DisplayName("플레이어들 생성 테스트")
    void getNextFrameTest() {
        assertThatCode(
                () -> new Players(Arrays.asList(new Player("jjy"), new Player("ddd")))
        ).doesNotThrowAnyException();
    }
}
