package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PitchTest {

    @DisplayName("Pitch 객체 정상 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 10})
    public void makePitch_정상(int hitCounts) {
        assertThatCode(() -> {
            new Pitch(hitCounts);
        }).doesNotThrowAnyException();
    }

    @DisplayName("Pitch 객체 정상 생성 실패 (hitCounts가 음수이거나 10을 초과)")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void makePitch_예외(int hitCounts) {
        assertThatThrownBy(() -> {
            new Pitch(hitCounts);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_PITCH);
    }
}
