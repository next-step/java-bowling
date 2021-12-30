package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FrameIndexTest {
    public static final FrameIndex FIRST_INDEX = FrameIndex.first();
    public static final FrameIndex SECOND_INDEX = FrameIndex.create(2);

    @DisplayName("프레임 인덱스 생성시 범위 예외 확인")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, FrameIndex.MIN_INDEX - 1, FrameIndex.MAX_INDEX + 1, Integer.MAX_VALUE})
    void createWithInvalidName(int invalidRangeValue) {
        // when & then
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> FrameIndex.create(invalidRangeValue));
    }

    @DisplayName("프레임 인덱스가 최대 값인지 확인")
    @Test
    void isMax() {
        // given
        FrameIndex first = FrameIndex.first();
        FrameIndex last = FrameIndex.last();
        // when & then
        assertAll(
                () -> assertThat(first.isMax()).isFalse(),
                () -> assertThat(last.isMax()).isTrue()
        );
    }

    @DisplayName("next 호출시, Index의 최대값을 넘을 경우 예외 확인")
    @Test
    void nextFailed() {
        // given
        FrameIndex last = FrameIndex.last();
        // when & then
        assertThatIllegalStateException().isThrownBy(last::next);
    }

    @DisplayName("next 호출시, Index 반환")
    @Test
    void next() {
        // given
        FrameIndex first = FrameIndex.first();
        // when & then
        assertThat(first.next()).isEqualTo(FrameIndex.create(FrameIndex.MIN_INDEX + 1));
    }
}
