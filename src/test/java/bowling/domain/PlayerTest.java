package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 03:03
 */
public class PlayerTest {
    @DisplayName("플레이어 생성")
    @Test
    void 플레이어() {
        Player player = Player.of("KBY");
        assertThat(player.getName()).isEqualTo("KBY");
    }

    @DisplayName("플레이어 생성 - 예외(영문대문자만)")
    @ParameterizedTest
    @ValueSource(strings = {
            "kBY",
            "123",
            "KB!"
    })
    void 플레이어_예외_영문대문자만_가능(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Player.of(name);
        }).withMessageContaining("이름은 영문 대문자 3자리만 가능합니다. : {} " + name);
    }
}
