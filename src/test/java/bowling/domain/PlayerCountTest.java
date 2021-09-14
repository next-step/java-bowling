package bowling.domain;

import bowling.exception.InvalidPlayerCountException;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerCountTest {

    @Test
    @DisplayName("생성 테스트 - 0이하의 요청")
    public void invalidCount() {
        // given
        int count = 0;

        // when
        ThrowingCallable throwingCallable = () -> new PlayerCount(count);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(InvalidPlayerCountException.class);
    }

    @Test
    @DisplayName("count 테스트")
    public void count() {
        // given
        int count = 10;

        // when
        PlayerCount playerCount = new PlayerCount(count);

        // then
        assertThat(playerCount.count()).isEqualTo(count);
    }

}