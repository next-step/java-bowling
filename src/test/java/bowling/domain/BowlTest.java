package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlTest {

    @Test
    @DisplayName("핀을 쓰러뜨린 볼링 공 객체를 반환한다.")
    public void create() {
        Bowl Bowl = new Bowl(0, true);
        assertThat(Bowl).isEqualTo(new Bowl(0, true));
    }

    @Test
    @DisplayName("스트라이크일 경우, 참을 반환한다.")
    public void isStrike() {
        Bowl Bowl = new Bowl(10, true);
        boolean isStrike = Bowl.isStrike();
        assertThat(isStrike).isTrue();
    }

    @Test
    @DisplayName("쓰러뜨린 핀의 개수를 반환한다.")
    public void knockedDownPinCount() {
        Bowl Bowl = new Bowl(5, true);
        PinCount pinCount = Bowl.knockedDownPinCount();
        assertThat(pinCount.value()).isEqualTo(5);
    }

    @Test
    @DisplayName("해당 볼이 던져졌을 경우, 참을 반환한다.")
    public void isThrown() throws Exception {
        Bowl notThrownBowl = new Bowl(false);
        Bowl thrownBowl = new Bowl(true);
        assertThat(notThrownBowl.isThrown()).isFalse();
        assertThat(thrownBowl.isThrown()).isTrue();
    }

    @Test
    @DisplayName("쓰러뜨린 핀의 개수를 더한 결과를 반환한다.")
    public void plusPinCountOf() throws Exception {
        Bowl Bowl = new Bowl(5, true);
        int totalPinCount = Bowl.plusPinCountOf(new Bowl(5, true));
        assertThat(totalPinCount).isEqualTo(10);
    }
}
