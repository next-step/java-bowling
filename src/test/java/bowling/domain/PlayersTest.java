package bowling.domain;

import bowling.exception.PlayerCountOutOfBoundsException;
import bowling.exception.SameNamePlayerExistException;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    @DisplayName("생성 테스트 - 같은 이름이 존재하는 경우")
    public void invalidCount() {
        // given
        List<String> names = Arrays.asList("abc", "abc");

        // when
        ThrowingCallable throwingCallable = () -> new Players(names);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(SameNamePlayerExistException.class);
    }

    @Test
    @DisplayName("names, count 테스트")
    public void namesAndCount() {
        // given
        Players players = new Players(Arrays.asList("abc", "def"));
        int expectedCount = 2;

        // when
        List<String> names = players.names();
        int count = players.count();

        // then
        assertThat(names).containsExactly("abc", "def");
        assertThat(count).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("getName 테스트 - 플레이어 수보다 큰 요청인 경우")
    public void getNameInvalid() {
        // given
        Players players = new Players(Arrays.asList("abc", "def"));

        // when
        ThrowingCallable throwingCallable = () -> players.getName(2);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(PlayerCountOutOfBoundsException.class);
    }

    @Test
    @DisplayName("getName 테스트 - 정상적인 요청")
    public void getName() {
        // given
        Players players = new Players(Arrays.asList("abc", "def"));
        String expected = "def";

        // when
        String name = players.getName(1);

        // then
        assertThat(name).isEqualTo(expected);
    }

}