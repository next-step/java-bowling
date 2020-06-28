package bowling.domain.game;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class SingleBowlingGameTest {

    @DisplayName("객체 정상 생성 - 파라미터로 Player String 이름과 Frames를 주입받는다.")
    @Test
    public void makeSingleBowlingGame() {
        assertThatCode(() -> {
            SingleBowlingGame.of("PJH", Frames.initiate());
        }).doesNotThrowAnyException();
    }
}
