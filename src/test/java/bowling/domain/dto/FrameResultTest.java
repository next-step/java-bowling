package bowling.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameResultTest {

    static final String desc = "9|/";

    @DisplayName("FrameResult 생성 실패: null")
    @ParameterizedTest
    @NullSource
    void createFailure(final String desc) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FrameResult.of(desc));
    }

    @DisplayName("상태값 문자열을 반환")
    @Test
    void create() {
        assertThat(FrameResult.of(desc).getDesc())
                .isEqualTo(desc);
    }

    @DisplayName("논리적 동치성 비교")
    @Test
    void equals() {
        assertThat(FrameResult.of(desc))
                .isEqualTo(FrameResult.of(desc));
    }
}
