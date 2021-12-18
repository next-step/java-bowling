package bowling;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FrameTest {

    @Test
    @DisplayName("Frame이 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> new Frame(1, new Scores(1, 2))).doesNotThrowAnyException();
    }


    @Test
    @DisplayName("마지막 라운드가 아니면, 10점 마지막 라운드면 30점을 초과하면 예외가 발생한다.")
    void createExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Frame(1, new Scores(1, 10)));

        assertThatIllegalArgumentException().isThrownBy(
            () -> new Frame(9, new Scores(10, 10, 10, 1)));
    }
}
