package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @DisplayName("플레이어 인스턴스를 정상적으로 생성한다.")
    @Test
    void create() {
        Player player = new Player("abc");
        assertThat(player).isEqualTo(new Player("abc"));
    }

    @DisplayName("3글자 이상 입력시 예외를 반환한다.")
    @Test
    void length() {
        assertThatThrownBy(() -> new Player("blue")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 투구가 10이 아닌경우 프레임진행여부는 true 를 반환한다.")
    @Test
    void pitch_isFrameContinue_true() {
        Player player = new Player("abc");
        player.pitch(1, 8);

        assertThat(player.isFrameContinue(1)).isTrue();
    }

    @DisplayName("두번째 투구까지 진행한 경우 프레임진행여부는 false 를 반환한다.")
    @Test
    void pitch_isFrameContinue_false() {
        Player player = new Player("abc");
        player.pitch(1, 8);
        player.pitch(1, 1);

        assertThat(player.isFrameContinue(1)).isFalse();
    }

    @DisplayName("첫 투구가 스트라이크의 경우 프레임진행여부는 false 를 반환한다.")
    @Test
    void pitch_isFrameContinue_strike() {
        Player player = new Player("abc");
        player.pitch(1, 10);

        assertThat(player.isFrameContinue(1)).isFalse();
    }

    @DisplayName("마지막 프레임의 첫 투구가 스트라이크의 경우 프레임진행여부는 true 를 반환한다.")
    @Test
    void pitch_isFrameContinue_final_strike() {
        int finalFrame = 10;
        Player player = new Player("abc");
        player.pitch(finalFrame, 10);

        assertThat(player.isFrameContinue(finalFrame)).isTrue();
    }

    @DisplayName("마지막 프레임 스페어의 경우 프레임진행여부는 true 를 반환한다.")
    @Test
    void pitch_isFrameContinue_final_spare() {
        int finalFrame = 10;
        Player player = new Player("abc");
        player.pitch(finalFrame, 8);
        player.pitch(finalFrame, 2);

        assertThat(player.isFrameContinue(finalFrame)).isTrue();
    }

    @DisplayName("마지막 프레임 오픈의 경우 프레임진행여부는 false 를 반환한다.")
    @Test
    void pitch_isFrameContinue_final_open() {
        int finalFrame = 10;
        Player player = new Player("abc");
        player.pitch(finalFrame, 8);
        player.pitch(finalFrame, 1);

        assertThat(player.isFrameContinue(finalFrame)).isFalse();
    }

    @DisplayName("마지막 프레임 보너스 투가까지 마친 경우 프레임진행여부는 false 를 반환한다.")
    @Test
    void pitch_isFrameContinue_final_bonus() {
        int finalFrame = 10;
        Player player = new Player("abc");
        player.pitch(finalFrame, 8);
        player.pitch(finalFrame, 2);
        player.pitch(finalFrame, 10);

        assertThat(player.isFrameContinue(finalFrame)).isFalse();
    }
}
