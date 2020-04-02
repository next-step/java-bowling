package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {

    @Test
    @DisplayName("플레이어 객체 비교")
    void equalsToPlayer() {
        // give
        Player player = new Player("KSJ");
        Player expectedPlayer = new Player("KSJ");
        // when
        boolean same = player.equals(expectedPlayer);
        // then
        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("이름길이 예외처리")
    void exceptNameLengthByPlayer() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Player("KSJD"));
    }

    @Test
    @DisplayName("플레이어 이름 가져오기")
    void getName() {
        // give
        Player player = new Player("KSJ");
        String expectedName = "KSJ";
        // when
        boolean same = expectedName.equals(player.getName());
        // then
        assertThat(same).isTrue();
    }
}
