package bowling.domain.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("참가자 테스트")
class PlayerTest {
    @Test
    @DisplayName("참가자 정상생성 검증")
    void create() {
        assertDoesNotThrow(() -> Player.from("SJB"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참가자 미입력 예외 검증")
    void create_exception(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> Player.from(name));
    }

    @Test
    @DisplayName("참가자 영문명이 3글자가 넘어가는 예외 발생")
    void create_exception2() {
        assertThatIllegalArgumentException().isThrownBy(() -> Player.from("SJBB"));
    }
}
