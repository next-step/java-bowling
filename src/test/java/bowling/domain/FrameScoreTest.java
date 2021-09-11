package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FrameScoreTest {
    @DisplayName("한번 던진 후 테스트")
    @Test
    void oneShotTest() {
        //given
        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(new Shot(5));

        //when
        boolean finished = frameScore.isFinished();

        //then
        assertThat(finished).isEqualTo(false);
    }

    @DisplayName("두번 던진 후 테스트")
    @Test
    void twoShotTest() {
        //given
        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(new Shot(5));
        frameScore.addPoint(new Shot(3));

        //when
        boolean finished = frameScore.isFinished();
        int score = frameScore.calculateFrameScore();

        //then
        assertThat(finished).isEqualTo(true);
        assertThat(score).isEqualTo(8);
    }

    @DisplayName("Spare 후 테스트")
    @Test
    void spareTest() {
        //given
        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(new Shot(5));
        frameScore.addPoint(new Shot(5));

        //when
        boolean finished = frameScore.isFinished();

        //then
        assertThat(finished).isEqualTo(false);
    }

    @DisplayName("Spare 후 원샷 테스트")
    @Test
    void spareAndOneShotTest() {
        //given
        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(new Shot(5));
        frameScore.addPoint(new Shot(5));
        frameScore.addPoint(new Shot(5));

        //when
        boolean finished = frameScore.isFinished();
        int score = frameScore.calculateFrameScore();

        //then
        assertThat(finished).isEqualTo(true);
        assertThat(score).isEqualTo(15);
    }

    @DisplayName("Strike  테스트")
    @Test
    void strikeTest() {
        //given
        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(new Shot(10));


        //when
        boolean finished = frameScore.isFinished();

        //then
        assertThat(finished).isEqualTo(false);
    }

    @DisplayName("Strike 하고 한번더 던진 후 테스트")
    @Test
    void strikeAndOneShotTest() {
        //given
        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(new Shot(10));
        frameScore.addPoint(new Shot(5));

        //when
        boolean finished = frameScore.isFinished();

        //then
        assertThat(finished).isEqualTo(false);
    }

    @DisplayName("Strike 하고 두번 던진 후  테스트")
    @Test
    void strikeAndTwoShotTest() {
        //given
        FrameScore frameScore = new FrameScore();
        frameScore.addPoint(new Shot(10));
        frameScore.addPoint(new Shot(5));
        frameScore.addPoint(new Shot(5));

        //when
        boolean finished = frameScore.isFinished();
        int score = frameScore.calculateFrameScore();

        //then
        assertThat(finished).isEqualTo(true);
        assertThat(score).isEqualTo(20);
    }
}
