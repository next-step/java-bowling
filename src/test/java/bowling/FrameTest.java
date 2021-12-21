package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.scores.FinalScores;
import bowling.domain.scores.GeneralScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FrameTest {

    @Test
    @DisplayName("Frame이 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> new Frame(new GeneralScores(1, 2))).doesNotThrowAnyException();
    }


    @Test
    @DisplayName("마지막 라운드가 아니면, 10점 마지막 라운드면 30점을 초과하면 예외가 발생한다.")
    void createExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Frame(new GeneralScores(1, 10)));

        assertThatIllegalArgumentException().isThrownBy(
            () -> new Frame(new GeneralScores(10, 10, 10, 1)));
    }

    @Test
    @DisplayName("10라운드가 아닌경우, 동일 프레임에서 스트로크가 가능한지 기능을 확인한다.")
    void isClosedStrokeTest() {
        assertThat(new Frame(new GeneralScores(10)).isClosedStroke()).isTrue();
        assertThat(new Frame(new GeneralScores(7, 3)).isClosedStroke()).isTrue();
        assertThat(new Frame(new GeneralScores(1, 2)).isClosedStroke()).isTrue();
    }

    @Test
    @DisplayName("10라운드인경우, 동일 프레임에서 스트로크가 가능한지 기능을 확인한다.")
    void isClosedStrokeLastRoundTest() {
        assertThat(new Frame(new FinalScores(10)).isClosedStroke()).isFalse();
        assertThat(new Frame(new FinalScores(7, 3)).isClosedStroke()).isFalse();
        assertThat(new Frame(new FinalScores(10, 10, 10)).isClosedStroke()).isTrue();
    }
}
