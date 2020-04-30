package bowling.domain.shot.type;

import bowling.domain.shot.Score;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTypeTest {
    @ParameterizedTest
    @CsvSource(value = {
            "STRIKE,true",
            "SPARE,true",
            "MISS_FIRST,false",
            "MISS_SECOND,true",
            "GUTTER_FIRST,false",
            "GUTTER_SECOND,true"})
    void isFinished(ScoreType scoreType, boolean isTrue) {
        assertThat(scoreType.isFinished())
                .isEqualTo(isTrue);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,GUTTER_FIRST",
            "1,MISS_FIRST",
            "2,MISS_FIRST",
            "3,MISS_FIRST",
            "4,MISS_FIRST",
            "5,MISS_FIRST",
            "6,MISS_FIRST",
            "7,MISS_FIRST",
            "8,MISS_FIRST",
            "9,MISS_FIRST",
            "10,STRIKE"})
    void ofFirst(int score, ScoreType expectType) {
        assertThat(ScoreType.of(Score.of(score)))
                .isEqualTo(expectType);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,0,GUTTER_SECOND", "0,1,MISS_SECOND", "0,2,MISS_SECOND", "0,3,MISS_SECOND", "0,4,MISS_SECOND", "0,5,MISS_SECOND", "0,6,MISS_SECOND", "0,7,MISS_SECOND", "0,8,MISS_SECOND", "0,9,MISS_SECOND",
            "1,0,GUTTER_SECOND", "1,1,MISS_SECOND", "1,2,MISS_SECOND", "1,3,MISS_SECOND", "1,4,MISS_SECOND", "1,5,MISS_SECOND", "1,6,MISS_SECOND", "1,7,MISS_SECOND", "1,8,MISS_SECOND",
            "2,0,GUTTER_SECOND", "2,1,MISS_SECOND", "2,2,MISS_SECOND", "2,3,MISS_SECOND", "2,4,MISS_SECOND", "2,5,MISS_SECOND", "2,6,MISS_SECOND", "2,7,MISS_SECOND", "2,8,SPARE",
            "3,0,GUTTER_SECOND", "3,1,MISS_SECOND", "3,2,MISS_SECOND", "3,3,MISS_SECOND", "3,4,MISS_SECOND", "3,5,MISS_SECOND", "3,6,MISS_SECOND", "3,7,SPARE",
            "4,0,GUTTER_SECOND", "4,1,MISS_SECOND", "4,2,MISS_SECOND", "4,3,MISS_SECOND", "4,4,MISS_SECOND", "4,5,MISS_SECOND", "4,6,SPARE",
            "5,0,GUTTER_SECOND", "5,1,MISS_SECOND", "5,2,MISS_SECOND", "5,3,MISS_SECOND", "5,4,MISS_SECOND", "5,5,SPARE",
            "6,0,GUTTER_SECOND", "6,1,MISS_SECOND", "6,2,MISS_SECOND", "6,3,MISS_SECOND", "6,4,SPARE",
            "7,0,GUTTER_SECOND", "7,1,MISS_SECOND", "7,2,MISS_SECOND", "7,3,SPARE",
            "8,0,GUTTER_SECOND", "8,1,MISS_SECOND", "8,2,SPARE",
            "9,0,GUTTER_SECOND", "9,1,SPARE"
    })
    void ofSecond(int first, int second, ScoreType expectType) {
        assertThat(ScoreType.of(Score.of(first), Score.of(second)))
                .isEqualTo(expectType);
    }

}
