package domain.frame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameIndexTest {

    @Test
    @DisplayName("프레임 인덱스 증가")
    void next() {
        FrameIndex frameIndex = FrameIndex.of(1);
        FrameIndex next = frameIndex.next();
        assertThat(next.equals(FrameIndex.of(2))).isTrue();
    }

    @Test
    @DisplayName("이미 10프레임 이상의 진행 예외처리")
    void nextException() {
        FrameIndex frameIndex = FrameIndex.of(10);
        Assertions.assertThrows(RuntimeException.class, frameIndex::next);
    }
}