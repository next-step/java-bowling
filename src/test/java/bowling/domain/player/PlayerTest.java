package bowling.domain.player;

import bowling.exception.RangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {

    @DisplayName("이름 3자로 String으로 player 객체를 만들 수 있다")
    @Test
    void should_make_player_using_3_char() {
        //arrange, act, assert
        assertThat(Player.of("abc")).isInstanceOf(Player.class);
    }

    @DisplayName("플레이어 이름이 null 이거나 비어있으면 RangeArgumentException이 발생한다")
    @NullAndEmptySource
    @ParameterizedTest
    void should_throw_exception_when_null_or_empty(String name) {
        //arrange, act, assert
        assertThatThrownBy(() -> Player.of(name)).isInstanceOf(RangeArgumentException.class);
    }

    @DisplayName("플레이어 이름이 3자가 아니면 RangeArgumentException이 발생한다")
    @ValueSource(strings = {"aa", "aabc"})
    @ParameterizedTest
    void should_throw_exception_when_3_under_over_name(String name) {
        //arrange, act, assert
        assertThatThrownBy(() -> Player.of(name)).isInstanceOf(RangeArgumentException.class);
    }

    @DisplayName("player는 name을 반환할 수 있다")
    @Test
    void should_return_player_name() {
        //arrange
        Player player = Player.of("abc");

        //act
        String name = player.getName();

        //assert
        assertThat(name).isEqualTo("abc");
    }

}