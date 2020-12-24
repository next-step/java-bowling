package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @ParameterizedTest
    @DisplayName("플레이어 이름 정상 등록 확인")
    @ValueSource(strings = {"WYR", "HHH", "KKK"})
    public void 플레이어_이름_정상등록확인(String name) {
        Player player = Player.of(name);
        assertThat(player.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @DisplayName("플레이어 이름 빈 값인 경우 예외처리")
    @EmptySource
    public void 플레이어_이름_빈값인경우(String name) {
        assertThatThrownBy(() -> Player.of(name))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("플레이어를 입력해주세요.");
    }

    @ParameterizedTest
    @DisplayName("플레이어 이름 최대 길이 초과한 경우 예외처리")
    @ValueSource(strings = {"WYRR", "AKKI", "KKII"})
    public void 플레이어_이름_최대_길이_초과(String name) {
        assertThatThrownBy(() -> Player.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 최대 3글자 입니다.");
    }
}
