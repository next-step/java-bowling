package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NomalFrameTest {

    @Test
    @DisplayName("입력 받은 투구 수 정상 반환 확인")
    public void 입력받은투구() {
        NomalFrame nomalFrame = NomalFrame.of(4);
        assertThat(nomalFrame.getBowlingKnockDowns().getKnockDownExpression().trim()).isEqualTo("4");
    }

    @Test
    @DisplayName("입력 받은 투구 10인 경우 스트라이크 표시되는지")
    public void 입력받은투구_10인경우_스트라이크() {
        NomalFrame nomalFrame = NomalFrame.of(10);
        assertThat(nomalFrame.getBowlingKnockDowns().getKnockDownExpression().trim()).isEqualTo("X");
    }

    @Test
    @DisplayName("입력 받은 투구 0인 경우 gutter 표시되는지")
    public void 입력받은투구_0인경우_gutter() {
        NomalFrame nomalFrame = NomalFrame.of(0);
        assertThat(nomalFrame.getBowlingKnockDowns().getKnockDownExpression().trim()).isEqualTo("-");
    }

    @Test
    @DisplayName("현재 투구와 다음 입력 받은 투구 인 경우 정상 표시되는지")
    public void 입력받은투구와_다음입력받은투구_정상노출() {
        NomalFrame nomalFrame = NomalFrame.of("9",1);
        assertThat(nomalFrame.getBowlingKnockDowns().getKnockDownExpression().trim()).isEqualTo("9 | /");
    }
}
