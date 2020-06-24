package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class FrameNumericScoreTest {

    @DisplayName("객체 정상 생성 테스트")
    @Test
    public void makeFrameNumericScore_정상() {
        assertThatCode(() -> {
            FrameNumericScore.of(10);
        }).doesNotThrowAnyException();
    }

}
