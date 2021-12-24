package bowling;

import bowling.domain.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc"})
    void 이름은_3글자까지_가능함_동등성_가짐(String name) {
        assertThat(new Player(name)).isEqualTo(new Player(name));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"abcd"})
    void 이름_1에서_3글자_이외에는_예외발생(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Player.MAX_LENGTH_MESSAGE);
    }


}
