package bowling.domain.result;

import bowling.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(new GameResult(User.from("miz"), new FrameResults(Arrays.asList(new FrameResult("X")))))
                .isEqualTo(new GameResult(User.from("miz"), new FrameResults(Arrays.asList(new FrameResult("X")))));
    }
}
