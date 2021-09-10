package bowling.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @ParameterizedTest(name = "플레이어 생성 - 3글자 이외의 값 | {arguments}")
    @ValueSource(strings = {"", "abcd"})
    public void 플레이어생성_실패_3글자아님(String name) {
        // given
        String message = String.format("플레이어 이름은 3글자만 가능합니다 -> %s", name);

        // when
        ThrowingCallable throwingCallable = () -> new Player(name);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @ParameterizedTest(name = "플레이어 생성 - 영어 이름이 아닌 경우 | {arguments}")
    @ValueSource(strings = {"!@#", "세글자"})
    public void 플레이어생성_실패_영어이름아님(String name) {
        // given
        String message = String.format("플레이어 이름은 영어만 가능합니다 -> %s", name);

        // when
        ThrowingCallable throwingCallable = () -> new Player(name);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @ParameterizedTest(name = "플레이어 생성 - 정상적인 경우 | {arguments}")
    @ValueSource(strings = {"abc", "ABC"})
    public void 플레이어생성_성공(String name) {
        // given
        Player expectedPlayer = new Player(name);

        // when
        Player player = new Player(name);

        // then
        assertThat(player).isEqualTo(expectedPlayer);
        assertThat(player.name()).isEqualTo(name);
    }

}