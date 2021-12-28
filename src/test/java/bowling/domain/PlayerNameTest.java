package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PlayerNameTest {

    @Test
    @DisplayName("플레이어 이름 객체 생성")
    void create() {
        PlayerName name = new PlayerName("KYH");
        assertThat(name).isEqualTo(new PlayerName("KYH"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"KANG, KA"})
    @DisplayName("이름 3글자 아니면 IllegalArgumentException 반환")
    void invalid(String name) {
        assertThatThrownBy(() -> new PlayerName(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름 입력값 정상")
    void valid() {
        assertThatCode(() -> new PlayerName("KYH"))
                .doesNotThrowAnyException();
    }

}