package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FinalScoreTest {

    private Frame frame = new Frame(10);

    @Test
    public void 생성() {
        assertThat(frame.getScore()).isEqualTo(new FinalScore());
    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,STRIKE,STRIKE,STRIKE", "10,10,0,STRIKE,STRIKE,GUTTER"
            , "10,0,10,STRIKE,GUTTER,SPARE", "10,0,0,STRIKE,GUTTER,GUTTER"
            , "0,10,10,GUTTER,SPARE,STRIKE", "0,10,0,GUTTER,SPARE,GUTTER"})
    public void Symbol_STRIKE_SPARE_정합성(int p1, int p2, int p3, Symbol s1, Symbol s2, Symbol s3) {
        frame.bowl(p1);
        frame.bowl(p2);
        frame.bowl(p3);
        assertThat(frame.getScore().getSymbols()).isEqualTo(Arrays.asList(s1, s2, s3));
    }

    @Test
    public void finished_False() {
        assertThat(frame.isFinished()).isFalse();

        frame.bowl(10);
        assertThat(frame.isFinished()).isFalse();

        frame.bowl(0);
        assertThat(frame.isFinished()).isFalse();
    }

    @Test
    public void finished_True() {
        frame.bowl(9);
        frame.bowl(0);
        assertThat(frame.isFinished()).isTrue();

        frame = new Frame(10);
        frame.bowl(9);
        frame.bowl(1);
        frame.bowl(0);
        assertThat(frame.isFinished()).isTrue();
    }

}