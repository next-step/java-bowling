package bowling.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameResultTest {

    @DisplayName("FrameResult 생성 실패: null 또는 빈 문자열")
    @ParameterizedTest
    @NullAndEmptySource
    void createFailure(final String desc) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FrameResult.of(desc));
    }

    @DisplayName("상태값 문자열을 반환")
    @Test
    void create() {
        final String desc = "9|/";

        assertThat(FrameResult.of(desc).getDesc())
                .isEqualTo(desc);
    }
}
