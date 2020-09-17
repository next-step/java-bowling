package bowling.domain;

import bowling.domain.bowl.NormalBowl;
import bowling.domain.bowl.NormalBowlResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("투구 결과 객체 테스트")
public class NormalBowlResultTest {

    private static final NormalBowl NORMAL_BOWL_STRIKE = new NormalBowl(10);
    private static final NormalBowl NORMAL_BOWL_SPARE = new NormalBowl(9, 1);
    private static final NormalBowl NORMAL_BOWL_MISS = new NormalBowl(8, 0);
    private static final NormalBowl NORMAL_BOWL_GUTTER = new NormalBowl(0, 0);
    private static final NormalBowl NORMAL_BOWL_NONE = new NormalBowl();

    @DisplayName("객체 가져오기(찾기) 테스트")
    @Test
    public void getType() {
        assertThat(NormalBowlResult.getType(NORMAL_BOWL_STRIKE)).isEqualTo(NormalBowlResult.STRIKE);
        assertThat(NormalBowlResult.getType(NORMAL_BOWL_SPARE)).isEqualTo(NormalBowlResult.SPARE);
        assertThat(NormalBowlResult.getType(NORMAL_BOWL_MISS)).isEqualTo(NormalBowlResult.MISS);
        assertThat(NormalBowlResult.getType(NORMAL_BOWL_GUTTER)).isEqualTo(NormalBowlResult.GUTTER);
        assertThat(NormalBowlResult.getType(NORMAL_BOWL_NONE)).isEqualTo(NormalBowlResult.NONE);
    }

}
