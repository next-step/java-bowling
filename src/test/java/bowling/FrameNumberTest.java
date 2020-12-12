package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameNumberTest {

    @Test
    void 정상_값() {
        assertThat(FrameNumber.from(1)).isEqualTo(1);
        assertThat(FrameNumber.from(2).toString()).isEqualTo("02");
    }

    @Test
    void 비정상_값(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FrameNumber.from(11))
                .withMessage("범위를 벗어난 프레임 수 입니다.");
    }
}
