package bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class ScoreTest {
    @ParameterizedTest
    @CsvSource(value={"10:X","9:9","8:8","7:7","6:6","5:5","4:4","3:3","2:2","1:1","0:-"}, delimiter = ':')
    void parseTest(int num, String parsed){
        Score score = Score.of(num, false);
        assertThat(score.toString()).isEqualTo(parsed);
    }

    @Test
    void spareTest(){
        Score score = Score.of(1, true);
        assertThat(score.toString()).isEqualTo("/");
    }

    @Test
    void smallThantMinNumTest(){
        assertThatIllegalArgumentException().isThrownBy(()-> Score.of(-1, true))
        .withMessage("Invalid score");
    }

    @Test
    void largeThantMaxNumTest(){
        assertThatIllegalArgumentException().isThrownBy(()-> Score.of(11, true))
                .withMessage("Invalid score");
    }
}