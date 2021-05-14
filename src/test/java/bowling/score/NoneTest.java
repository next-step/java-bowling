package bowling.score;

import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.NormalScore;
import bowling.entity.score.ScoreType;
import bowling.entity.score.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NoneTest {
    private Pin normalPin;
    Pin strikePin;
    private ScoreType none;

    @BeforeEach
    void setup() {
        normalPin = new Pin(4);
        strikePin = new Pin(10);
        none = new None();
    }

    @Test
    @DisplayName("프레임 시작")
    public void isFrameEnd() {
        assertThat(none.isFrameEnd()).isFalse();
    }
}
