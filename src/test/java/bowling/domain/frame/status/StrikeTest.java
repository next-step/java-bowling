package bowling.domain.frame.status;

import bowling.bowlingexception.IllegalFrameRecordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {

    @Test
    @DisplayName("출력 테스트")
    void description() {
        assertThat(new Strike().getDescription())
                .isEqualTo("X");
    }

    @Test
    @DisplayName("추가 입력 불가 예외 처리")
    void deniedRecord() {
        assertThatThrownBy(
                () -> new Strike().record(4)
        ).isInstanceOf(IllegalFrameRecordException.class);
    }

    @Test
    @DisplayName("종료 여부 확인")
    void isEnd() {
        assertThat(new Strike().isEnd())
                .isTrue();
    }
}
