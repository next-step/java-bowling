package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("투구 결과 객체 테스트")
public class BowlResultTest {

    private static final NormalBowl NORMAL_BOWL_STRIKE = new NormalBowl(10);
    private static final NormalBowl NORMAL_BOWL_SPARE = new NormalBowl(9, 1);
    private static final NormalBowl NORMAL_BOWL_MISS = new NormalBowl(8, 0);
    private static final NormalBowl NORMAL_BOWL_GUTTER = new NormalBowl(0, 0);
    private static final NormalBowl NORMAL_BOWL_NONE = new NormalBowl(4);

    @DisplayName("객체 가져오기(찾기) 테스트")
    @Test
    public void getType() {
        assertThat(BowlResult.getType(NORMAL_BOWL_STRIKE)).isEqualTo(BowlResult.STRIKE);
        assertThat(BowlResult.getType(NORMAL_BOWL_SPARE)).isEqualTo(BowlResult.SPARE);
        assertThat(BowlResult.getType(NORMAL_BOWL_MISS)).isEqualTo(BowlResult.MISS);
        assertThat(BowlResult.getType(NORMAL_BOWL_GUTTER)).isEqualTo(BowlResult.GUTTER);
        assertThat(BowlResult.getType(NORMAL_BOWL_NONE)).isEqualTo(BowlResult.NONE);
    }

}
