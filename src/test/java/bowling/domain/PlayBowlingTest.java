package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayBowlingTest {
    private static PlayBowling playBowling = PlayBowling.getPlayBowling();

    @DisplayName("입력하는 볼링 점수가 범위를 초과할 경우 예외를 발생시킨다.")
    @Test
    void validatePinRange() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            playBowling.validatePinRange(11);
        });
    }
}
