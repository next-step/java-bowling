package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Player.MAX_LENGTH_OF_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @DisplayName("플레이어를 등록하는데 성공한다")
    @Test
    void createPlayer_inputStringName_success() {
        // given
        String name = "juy";

        // when
        Player player = Player.of(name);

        // then
        assertThat(player).isNotNull();
        assertThat(player).isEqualTo(Player.of(name));
    }

    @DisplayName("플레이어 이름이 " + MAX_LENGTH_OF_NAME + "글자 초과 시 생성에 실패한다")
    @Test
    void createPlayer_inputStringNameLengthMoreThanMax_fail() {
        // given
        String name = "juyoung";

        // exception
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Player.of(name));
    }
}
