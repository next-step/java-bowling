package bowling.domain.state.progress.end.second;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import bowling.domain.state.end.second.Normal;
import bowling.domain.state.progress.FirstProgress;
import bowling.domain.state.progress.SecondProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalTest {

    private Normal normal;

    @BeforeEach
    void init() {
        FirstProgress firstProgress = new FirstProgress(Pin.of(3));
        SecondProgress mockSecondProgress = new SecondProgress(Pin.of(5), firstProgress);

        normal = new Normal(mockSecondProgress);
    }

    @Test
    @DisplayName("Normal 표기가 잘 되는지 확인한다.")
    void getPrintMarkTest() {
        assertThat(normal.getPrintMark()).isEqualTo("3|5");
    }
}