package bowling.step3.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class PlayerTest {
    @Test
    void 노멀프레임_스트라이크를_쳤을_때() {
        Player player = new Player("jak");
        player.bowl(10);
        assertSoftly(softly -> {
            assertThat(player.name()).isEqualTo("jak");
            assertThat(player.IsEndedFrame(1)).isTrue();
        });
    }

    @Test
    void 노멀프레임_스트라이트가_아닐_때() {
        Player player = new Player("Jin");
        player.bowl(2);
        assertSoftly(softly -> {
            assertThat(player.name()).isEqualTo("Jin");
            assertThat(player.IsEndedFrame(1)).isFalse();
        });
    }
}
