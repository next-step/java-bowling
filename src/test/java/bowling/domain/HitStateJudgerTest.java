package bowling.domain;

import static bowling.domain.HitStateJudger.judgeScore;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitStateJudgerTest {

    @DisplayName("투구가 추가 점수없는 결과로 끝난 프레임을 넣으면 일반을 반환한다.")
    @Test
    void judgeScoreTest1() {
        Frame frame = new Frame();
        frame.shot(4);
        frame.shot(3);

        assertThat(judgeScore(frame)).isEqualTo(HitState.NORMAL);
    }

    @DisplayName("투구가 스트라이크로 끝난 프레임을 넣으면 스트라이크를 반환한다.")
    @Test
    void judgeScoreTest2() {
        Frame frame = new Frame();
        frame.shot(10);

        assertThat(judgeScore(frame)).isEqualTo(HitState.STRIKE);
    }

    @DisplayName("투구가 스페어로 끝난 프레임을 넣으면 스페어를 반환한다.")
    @Test
    void judgeScoreTest3() {
        Frame frame = new Frame();
        frame.shot(7);
        frame.shot(3);

        assertThat(judgeScore(frame)).isEqualTo(HitState.SPARE);
    }

}
