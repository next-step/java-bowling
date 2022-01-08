package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameIndexTest {

    @Test
    void 첫_번째_프레임_인덱스_생성() {
        FrameIndex firstIndex = FrameIndex.first();
        assertThat(firstIndex.getIndex()).isEqualTo(1);
    }

    @Test
    void 다음_프레임_생성() {
        FrameIndex firstIndex = FrameIndex.first();
        assertThat(firstIndex.next().getIndex()).isEqualTo(2);
    }

    @Test
    void 마지막_프레임_인덱스_생성() {
        FrameIndex lastIndex = FrameIndex.last();
        assertThat(lastIndex.getIndex()).isEqualTo(10);
    }

    @DisplayName("프레임 인덱스가 10개를 초과하면 예외")
    @Test
    void 예외_프레임_초과() {
        FrameIndex lastIndex = FrameIndex.last();
        assertThatThrownBy(() -> lastIndex.next())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("%d프레임을 초과할 수 없습니다.", FrameIndex.MAX_INDEX));
    }
}