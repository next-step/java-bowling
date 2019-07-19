package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-16 23:04
 */
public class PointTest {
    @DisplayName("포인트 생성 - 11입력 시 생성 불가")
    @Test
    void 포인트_생성_예외상황() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Point.of(11);
        }).withMessageContaining("점수는 10 이하의 자연수만 가능합니다.");
    }

    @DisplayName("현재 포인트 가지고 오기")
    @Test
    void 현재_포인트_가지고오기() {
        Point point = Point.of(10);
        assertThat(point.fallCount()).isEqualTo(10);
    }

    @DisplayName("스트라이크 확인")
    @Test
    void Point가_스트라이크인지_확인() {
        Point point = Point.of(10);
        assertThat(point.isStrike()).isTrue();
    }

    @DisplayName("거터 확인")
    @Test
    void Point가_거터인지_확인() {
        Point point = Point.of(0);
        assertThat(point.isGutter()).isTrue();
    }
}
