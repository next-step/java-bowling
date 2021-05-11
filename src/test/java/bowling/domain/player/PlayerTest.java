package bowling.domain.player;

import bowling.domain.state.Pins;
import bowling.exception.NameNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @DisplayName("Player 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        Name name = Name.valueOf("kwj");

        // when
        Player player = Player.from(name);

        // then
        assertThat(player).isNotNull();
    }

    @DisplayName("Player 인스턴스 생성할 때 null 입력시 예외처리 테스트")
    @Test
    void 검증() {
        // given
        Name name = null;

        // when
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(NameNullPointerException.class)
                .hasMessage("Name 인스턴스가 null 입니다.");
    }

    @DisplayName("Player 인스턴스가 종료 여부를 반환하는 기능 테스트")
    @Test
    void 종료_여부() {
        // given
        Name name = Name.valueOf("kwj");

        // when
        Player player = Player.from(name);
        boolean actual = player.isFinish();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("Player 인스턴스가 게임을 시작하는 기능 테스트")
    @Test
    void 기능_투구() {
        // given
        Name name = Name.valueOf("kwj");

        // when
        Player player = Player.from(name);
        for (int i = 1; i <= 12; i++) {
            player.playBowl(Pins.full());
        }

        // then
        assertThat(player.isFinish()).isTrue();
    }

    @DisplayName("Player 인스턴스가 현재 투구 순서(라운드)를 반환하는 기능 테스트")
    @Test
    void 반환_현재_순서() {
        // given
        Name name = Name.valueOf("kwj");

        // when
        Player player = Player.from(name);

        // then
        assertThat(player.sequence()).isEqualTo(1);
    }

}