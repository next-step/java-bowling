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
 * create date  : 2019-07-10 16:10
 */
public class FinalFrameScoreTest {
    @DisplayName("처음 투구 스트라이크 후 두번째 투구를 던질 수 있다. (점수는 최대 은 20까지 만 가능)")
    @Test
    void first_strike_onemore_bowl() {
        FinalFrameScore finalFrameScore = new FinalFrameScore(10);
        assertThat(finalFrameScore.addScore(10)).isTrue();
    }

    @DisplayName("처음과 두번째 투구 스트라이크 후 세번째 투구를 던질 수 없다. (점수는 최대 은 20까지 만 가능)")
    @Test
    void first_and_second_strike_onemore_bowl() {
        FinalFrameScore finalFrameScore = new FinalFrameScore(10);
        finalFrameScore.addScore(10);
        assertThat(finalFrameScore.addScore(10)).isFalse();
    }

    @DisplayName("투구의 점수가 20점 이상인 상태이면 투구를 할수 없다.")
    @Test
    void bowl_score_20() {
        FinalFrameScore finalFrameScore = new FinalFrameScore(10);
        finalFrameScore.addScore(10);
        assertThat(finalFrameScore.addScore(10)).isFalse();
    }

    @DisplayName("2번 투구 후 스패어 이상 처리가 안된 경우 세번째 투구를 할 수 없다.")
    @Test
    void spare_score_under() {
        FinalFrameScore finalFrameScore = new FinalFrameScore(5);
        finalFrameScore.addScore(4);
        assertThat(finalFrameScore.addScore(10)).isFalse();
    }

    @DisplayName("초기화 생성 시 유효성 체크")
    @Test
    void create_exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            FinalFrameScore finalFrameScore = new FinalFrameScore(11);
        }).withMessageContaining("한번에 얻을수 있는 최대 점수를 넘었습니다.");
    }
}
