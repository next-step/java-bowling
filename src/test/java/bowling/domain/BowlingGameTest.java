package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BowlingGameTest {

    @DisplayName("볼링 게임이 10개의 프레임으로 구성되어 있지 않으면 예외가 발생해야 한다.")
    @Test
    void create_givenNot10Frames() {
        List<BowlingGameFrame> frames = new ArrayList<>();
        IntStream.range(0, 9)
                .forEach(i -> frames.add(new NormalBowlingGameFrame()));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BowlingGame(frames))
                .withMessage("프레임은 10개로 구성되어 있어야 합니다.");
    }

}
