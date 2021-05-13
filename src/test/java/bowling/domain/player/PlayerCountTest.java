package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerCountTest {

    @DisplayName("PlayerCount 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        int count = 10;

        // when
        PlayerCount playerCount = new PlayerCount(count);

        // then
        assertThat(playerCount).isNotNull();
    }

    @DisplayName("PlayerCount 인스턴스에 부적절한 값 입력시 예외처리 여부 테스트")
    @Test
    void 검증_0이하의_값() {
        // given
        int zero = 0;
        int negative = -1;

        // when and then
        assertAll(
                () -> assertThatThrownBy(() -> new PlayerCount(zero))
                        .isInstanceOf(InvalidPlayerCountException.class)
                        .hasMessage("PlayerCount 에 대해 알맞지 않은 크기 (%s)가 입력 되었습니다."),

                () -> assertThatThrownBy(() -> new PlayerCount(negative))
                        .isInstanceOf(InvalidPlayerCountException.class)
                        .hasMessage("PlayerCount 에 대해 알맞지 않은 크기 (%s)가 입력 되었습니다.")
        );

    }
}