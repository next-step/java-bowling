package bowling.domain;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.BowlStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("투구 결과 객체 테스트")
public class BowlStatusTest {

    private static final Bowl NORMAL_BOWL_STRIKE = new Bowl(10);
    private static final Bowl NORMAL_BOWL_SPARE = new Bowl(9, 1);
    private static final Bowl NORMAL_BOWL_MISS = new Bowl(8, 0);
    private static final Bowl NORMAL_BOWL_GUTTER = new Bowl(0, 0);
    private static final Bowl NORMAL_BOWL_NONE = new Bowl();

    @DisplayName("객체 가져오기(찾기) 테스트")
    @Test
    public void getType() {
        assertThat(BowlStatus.getType(NORMAL_BOWL_STRIKE)).isEqualTo(BowlStatus.STRIKE);
        assertThat(BowlStatus.getType(NORMAL_BOWL_SPARE)).isEqualTo(BowlStatus.SPARE);
        assertThat(BowlStatus.getType(NORMAL_BOWL_MISS)).isEqualTo(BowlStatus.MISS);
        assertThat(BowlStatus.getType(NORMAL_BOWL_GUTTER)).isEqualTo(BowlStatus.GUTTER);
        assertThat(BowlStatus.getType(NORMAL_BOWL_NONE)).isEqualTo(BowlStatus.NONE);
    }

}
