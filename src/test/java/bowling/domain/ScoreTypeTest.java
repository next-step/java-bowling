package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTypeTest {

    @DisplayName("이전 값, 현재 값에 따른 점수 타입 찾기")
    @Test
    void findType(){
        assertThat(ScoreType.findType(-1,10)).isEqualTo("X");
        assertThat(ScoreType.findType(-1,3)).isEqualTo("3");
        assertThat(ScoreType.findType(0,10)).isEqualTo("|/");
        assertThat(ScoreType.findType(2,8)).isEqualTo("|/");
        assertThat(ScoreType.findType(2,4)).isEqualTo("|4");
        assertThat(ScoreType.findType(2,0)).isEqualTo("|-");
    }

}