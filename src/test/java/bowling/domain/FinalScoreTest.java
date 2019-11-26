package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FinalScoreTest {

    @Test
    public void 생성() {
        Score score = new FinalScore();
        assertThat(score).isEqualTo(new FinalScore());
    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,STRIKE,STRIKE,STRIKE", "10,10,0,STRIKE,STRIKE,GUTTER"
            , "10,0,10,STRIKE,GUTTER,SPARE", "10,0,0,STRIKE,GUTTER,GUTTER"
            , "0,10,10,GUTTER,SPARE,STRIKE", "0,10,0,GUTTER,SPARE,GUTTER"})
    public void Symbol_STRIKE_SPARE_정합성(int p1, int p2, int p3, Symbol s1, Symbol s2, Symbol s3) {
        FinalScore score = new FinalScore();
        score.bowl(p1);
        score.bowl(p2);
        score.bowl(p3);
        assertThat(score.getSymbols()).isEqualTo(Arrays.asList(s1, s2, s3));
    }

    @Test
    public void finished_False() {
        FinalScore score = new FinalScore();
        assertThat(score.isFinished()).isFalse();

        score.bowl(10);
        assertThat(score.isFinished()).isFalse();

        score.bowl(0);
        assertThat(score.isFinished()).isFalse();
    }

    @Test
    public void finished_True() {
        FinalScore score = new FinalScore();
        score.bowl(9);
        score.bowl(0);
        assertThat(score.isFinished()).isTrue();

        FinalScore score2 = new FinalScore();
        score2.bowl(9);
        score2.bowl(1);
        score2.bowl(0);
        assertThat(score.isFinished()).isTrue();
    }

}