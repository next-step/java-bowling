package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @DisplayName("생성 실패 테스트 - 이름 길이 제한(3자) 초과")
    @ParameterizedTest
    @ValueSource(strings = {"KHSM", "SAMKKO", "LIMIT", "ABCD"})
    void create_fail_exceed_length(String name) {

        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Player.from(name);
        });
    }
}