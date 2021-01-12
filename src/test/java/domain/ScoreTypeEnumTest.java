package domain;

import bowling.domain.ScoreTypeEnum;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScoreTypeEnumTest {

    @Test
    public void check_strike(){
        assertThat("X").isEqualTo(ScoreTypeEnum.STRIKE.getScoreType());
    }

    @Test
    public void check_spare(){
        assertThat("/").isEqualTo(ScoreTypeEnum.SPARE.getScoreType());
    }

    @Test
    public void check_gutter(){
        assertThat("-").isEqualTo(ScoreTypeEnum.GUTTER.getScoreType());
    }
}
