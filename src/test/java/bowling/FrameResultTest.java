package bowling;

import bowling.domain.FrameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {
    @DisplayName("첫번째 포인트가 10, 두번째가 0일 때 스트라이크를 반환")
    @Test
    void findFrameResultWhenStrike() {
        //when
        FrameResult result = FrameResult.findResult(10, 0);

        //then
        assertThat(result).isEqualTo(FrameResult.STRIKE);
    }
}