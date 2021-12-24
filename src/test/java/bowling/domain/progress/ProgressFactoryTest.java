package bowling.domain.progress;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.Pin;
import bowling.domain.state.end.EndState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProgressFactoryTest {

    private static final Pin STRIKE = Pin.of(10);

    private Progress progress;

    @BeforeEach
    void init() {
        progress = ProgressFactory.create();
    }

    @Test
    @DisplayName("FirstProgress 가 생성된다.")
    void createTest() {
        assertThat(progress).isInstanceOf(FirstProgress.class);
    }

    @Test
    @DisplayName("GeneralProgress가 반환된다.")
    void progressStrikeTest() {
        Opened openedProgress = (Opened) this.progress;
        EndState mockState = openedProgress.pitch(STRIKE);

        assertThat(ProgressFactory.progress(mockState)).isInstanceOf(GeneralProgress.class);
    }

    @Test
    @DisplayName("Closed가 반환된다.")
    void progressGeneralTest() {
        assertThat(ProgressFactory.closed()).isInstanceOf(Closed.class);
    }


}
