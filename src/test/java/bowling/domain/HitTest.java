package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HitTest {
    Hit hit = new Hit(1);

    @Test
    void returnRemainingPin() {
        assertThat(hit.remainingPin()).isEqualTo(9);
    }

    @Test
    void HitOnlyFirst() {
        Hit hit = new Hit(1);
        assertThatThrownBy(() -> hit.second()).isInstanceOf(NullPointerException.class);
    }

    @Test
    void HitSecondPresent() {
        Hit hit = new Hit(1, 3);
        assertThat(hit.first()).isEqualTo(1);
        assertThat(hit.second()).isEqualTo(3);
    }

    @Test
    void firstStrReturnsString() {
        Hit hit0 = new Hit(0);
        assertThat(hit0.firstStr()).isEqualTo("-");
        Hit hit1 = new Hit(1);
        assertThat(hit1.firstStr()).isEqualTo("1");
    }

    @Test
    void secondStrReturnsString() {
        Hit hit0 = new Hit(9, 0);
        assertThat(hit0.secondStr()).isEqualTo("-");
        Hit hit1 = new Hit(9, 1);
        assertThat(hit1.secondStr()).isEqualTo("1");
    }

    @Test
    void convertZeroToDash() {
        assertThat(Hit.gutterHandler(0)).isEqualTo("-");
    }
}
