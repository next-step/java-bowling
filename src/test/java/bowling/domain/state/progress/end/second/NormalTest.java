package bowling.domain.state.progress.end.second;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.frame.GeneralFrame;
import bowling.domain.state.end.second.Normal;
import bowling.domain.state.progress.FirstProgress;
import bowling.domain.state.progress.GeneralProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalTest {

    private final Frame frame = new GeneralFrame(null);
    private Normal normal;

    @BeforeEach
    void init() {
        FirstProgress firstProgress = new FirstProgress(frame, Pin.of(3));
        GeneralProgress mockGeneralProgress = new GeneralProgress(frame, Pin.of(5), firstProgress);

        normal = new Normal(mockGeneralProgress);
    }

    @Test
    @DisplayName("Normal 표기가 잘 되는지 확인한다.")
    void getPrintMarkTest() {
        assertThat(normal.getPrintMark()).isEqualTo("3|5");
    }
}