package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PlayerTest {
    @DisplayName("플레이어 이름을 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"khw", "pjs", "ljs"})
    void Player_이름_생성(String name) {
        assertThat(new Player(name)).isNotNull().isInstanceOf(Player.class);
    }

    @DisplayName("플레이어 이름이 null 일 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void Player_이름_null_일_경우(String name) {
        assertThatThrownBy(() -> new Player(name)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어 이름이 3자가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"wu2ee", "pobi", "honux"})
    void Player_이름_3글자_아닐_경우(String name) {
        assertThatThrownBy(() -> new Player(name)).isInstanceOf(IllegalArgumentException.class);
    }
}