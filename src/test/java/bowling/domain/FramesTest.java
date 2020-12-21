package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    @DisplayName("입력 받은 투구 수 정상 반환 확인")
    public void 입력받은투구() {
        Frames frames = new Frames();
        frames.initFirst(1, 4);
        assertThat(frames.bowlingKnockDownExpression(1).trim()).isEqualTo("4");
    }

    @Test
    @DisplayName("입력 받은 투구 10인 경우 스트라이크 표시되는지")
    public void 입력받은투구_10인경우_스트라이크() {
        Frames frames = new Frames();
        frames.initFirst(1, 10);
        assertThat(frames.bowlingKnockDownExpression(1).trim()).isEqualTo("X");
    }

    @Test
    @DisplayName("입력 받은 투구 0인 경우 gutter 표시되는지")
    public void 입력받은투구_0인경우_gutter() {
        Frames frames = new Frames();
        frames.initFirst(1, 0);
        assertThat(frames.bowlingKnockDownExpression(1).trim()).isEqualTo("-");
    }

    @Test
    @DisplayName("현재 투구와 다음 입력 받은 투구 인 경우 정상 표시되는지")
    public void 입력받은투구와_다음입력받은투구_정상노출() {
        Frames frames = new Frames();
        BowlingKnockDown bowlingKnockDown = new BowlingKnockDown(9);
        frames.initNext(1, bowlingKnockDown, 1);
        assertThat(frames.bowlingKnockDownExpression(1).trim()).isEqualTo("9 | /");
    }
}
