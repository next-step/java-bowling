package bowling.util;

import bowling.domain.Shot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BowlingUtilTest {
    @DisplayName("합계 테스트")
    @Test
    public void sumTest() {
        //given
        List<Shot> shots = Arrays.asList(new Shot(10), new Shot(3), new Shot(6));

        //when
        int sum = BowlingUtils.sum(shots);

        //then
        assertThat(sum).isEqualTo(19);
    }
}
