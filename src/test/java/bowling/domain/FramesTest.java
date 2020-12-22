package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    @DisplayName("입력 받은 투구 수 정상 반환 확인")
    public void 입력받은투구() {
        Frames frames = new Frames();
        frames.put(1, new BowlingKnockDown(4));
        assertThat(frames.getBowlingKnockDownMap().get(1).getCurrentOfKnockDown()).isEqualTo(4);
    }

    @Test
    @DisplayName("현재 투구와 다음 입력 받은 투구 인 경우 정상 표시되는지")
    public void 입력받은투구와_다음입력받은투구_정상노출() {
        Frames frames = new Frames();
        frames.put(2, new BowlingKnockDown(4, 6));
        assertThat(frames.getBowlingKnockDownMap().get(2).getCurrentOfKnockDown()).isEqualTo(4);
        assertThat(frames.getBowlingKnockDownMap().get(2).getNextOfKnockDown()).isEqualTo(6);
    }

    @Test
    @DisplayName("마지막 프레임 3개인경우")
    public void 마지막_프레임3개인경우() {
        Frames frames = new Frames();
        frames.put(10, new BowlingKnockDown(4, 6, 10));
        assertThat(frames.getBowlingKnockDownMap().get(10).getCurrentOfKnockDown()).isEqualTo(4);
        assertThat(frames.getBowlingKnockDownMap().get(10).getNextOfKnockDown()).isEqualTo(6);
        assertThat(frames.getBowlingKnockDownMap().get(10).getBonusKnockDown()).isEqualTo(10);
    }
}
