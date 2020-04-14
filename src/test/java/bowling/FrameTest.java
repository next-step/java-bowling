package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameTest {
    @Test
    @DisplayName("Frame의 No는 10 이상이 될 수 없다.")
    void assertFrameNumber() {
        int no = 11;
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Frame(no);
        }).withMessage("Frame은 최대 10개까지만 생성됩니다.");
    }
}
