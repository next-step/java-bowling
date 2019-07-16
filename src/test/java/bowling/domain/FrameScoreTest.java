package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * create date  : 2019-07-16 23:16
 */
public class FrameScoreTest {
    @DisplayName("기본적인 프레임 스코어는 10점을 넘길 수 없다.")
    @Test
    void 프레임의_점수는_10점을_초과할수없다() {
        Point firstPoint = Point.of(9);
        Point secondPoint = Point.of(2);

        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(firstPoint);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            frameScore.addPoint(secondPoint);
        }).withMessageContaining("입력된 점수는 추가할 수 없습니다.");
    }

}
