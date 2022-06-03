package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BeforeProgressTest {
    @DisplayName("BeforeProgress 생성한다.")
    @Test
    void BeforeProgress_생성() {
        assertThat(new BeforeProgress()).isNotNull().isInstanceOf(BeforeProgress.class);
    }

    @DisplayName("BeforeProgress 상태에서 스트라이크 할 경우 다음 상태는 'Strike' 가 된다.")
    @Test
    void bowl_다음_상태_Strike() {
        assertThat(new BeforeProgress().bowl(new Pins(10))).isInstanceOf(Strike.class);
    }

    @DisplayName("BeforeProgress 상태에서 스트라이크 못 할 경우 다음 상태는 'FirstBowl' 이 된다.")
    @Test
    void bowl_다음_상태_FirstBowl() {
        assertThat(new BeforeProgress().bowl(new Pins(1))).isInstanceOf(FirstBowl.class);
    }

    @DisplayName("BeforeProgress 상태는 프레임 종료가 아니므로 false 를 반환한다.")
    @Test
    void isFrameEnd_종료_상태_체크() {
        assertThat(new BeforeProgress().isEnd()).isFalse();
    }

    @DisplayName("BeforeProgress 상태는 기호를 빈값으로 반환한다.")
    @Test
    void symbol_기호_체크() {
        assertThat(new BeforeProgress().symbol()).isEmpty();
    }

    @DisplayName("BeforeProgress 상태의 점수는 존재 하지 않고, 점수 값과, 남은 기회가 -1인 인스턴스를 반환한다.")
    @Test
    void score_점수() {
        FrameState frameState = new BeforeProgress();
        assertThat(frameState.score()).isEqualTo(Score.init());
    }

    @DisplayName("BeforeProgress 상태는 해당 프레임의 이전 점수가 없으므로 추가 점수 계산을 할 수 없고, 점수 값과 남은 기회가 -1인 인스턴스를 반환한다.")
    @Test
    void calculateAdditionalScore_추가점수() {
        FrameState frameState = new BeforeProgress();
        Score previousScore = Score.init();
        assertThat(frameState.calculateAdditionalScore(previousScore)).isEqualTo(Score.init());
    }
}