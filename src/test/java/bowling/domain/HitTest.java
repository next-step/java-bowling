package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HitTest {
    Hit hit = new Hit(1);
    @Test
    @DisplayName("1 에 대한 남은 핀 9를 출력해야함")
    void returnRemainingPin() {
        assertThat(hit.remainingPin()).isEqualTo(9);
    }

    @Test
    @DisplayName("한번만 쳤는데 두번째 스코어 조회하면 에러")
    void HitOnlyFirst() {
        Hit hit = new Hit(1);
        assertThatThrownBy(() -> hit.second()).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("두 번 다 쳤으면 first, second 모두 존재")
    void HitSecondPresent() {
        Hit hit = new Hit(1, 3);
        assertThat(hit.first()).isEqualTo(1);
        assertThat(hit.second()).isEqualTo(3);
    }

    @Test
    @DisplayName("firstStr 호출 시 숫자를 string으로 전환, 0인 경우 - 출력")
    void firstStrReturnsString() {
        Hit hit0 = new Hit(0);
        assertThat(hit0.firstStr()).isEqualTo("-");
        Hit hit1 = new Hit(1);
        assertThat(hit1.firstStr()).isEqualTo("1");
    }

    @Test
    @DisplayName("secondStr 호출 시 숫자를 string으로 전환, 0인 경우 - 출력")
    void secondStrReturnsString() {
        Hit hit0 = new Hit(9, 0);
        assertThat(hit0.secondStr()).isEqualTo("-");
        Hit hit1 = new Hit(9, 1);
        assertThat(hit1.secondStr()).isEqualTo("1");
    }

    @Test
    @DisplayName("0를 - 로 변환")
    void convertZeroToDash() {
        assertThat(Hit.charHandler(0)).isEqualTo("-");
    }
}
