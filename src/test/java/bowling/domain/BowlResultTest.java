package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("투구 결과 객체 테스트")
public class BowlResultTest {

    private static final FrameBowl frameBowl_strike = new FrameBowl(10);
    private static final FrameBowl frameBowl_spare = new FrameBowl(9, 1);
    private static final FrameBowl frameBowl_miss = new FrameBowl(8, 0);
    private static final FrameBowl frameBowl_gutter = new FrameBowl(0, 0);

    @DisplayName("객체 가져오기(찾기) 테스트")
    @Test
    public void getType() {
        assertThat(BowlResult.getType(frameBowl_strike)).isEqualTo(BowlResult.STRIKE);
        assertThat(BowlResult.getType(frameBowl_spare)).isEqualTo(BowlResult.SPARE);
        assertThat(BowlResult.getType(frameBowl_miss)).isEqualTo(BowlResult.MISS);
        assertThat(BowlResult.getType(frameBowl_gutter)).isEqualTo(BowlResult.GUTTER);
    }

}
