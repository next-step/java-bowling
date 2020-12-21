package bowling.model.frame;

import bowling.model.Name;
import bowling.model.Pins;
import bowling.view.ResultView;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FramesTest {
    private final Pins[] pins = IntStream.rangeClosed(0, 10)
            .boxed()
            .map(Pins::from)
            .toArray(Pins[]::new);


    @Test
    void bowling_STRIKE_STRIKE_OPEN() {
        Frames frames = new Frames();
        frames.bowling(pins[10]);
        frames.bowling(pins[10]);
        frames.bowling(pins[1]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);


        assertThat(totalScore).containsExactly("21");
    }

    @Test
    void bowling_STRIKE_STRIKE_STRIKE() {
        Frames frames = new Frames();
        frames.bowling(pins[10]);
        frames.bowling(pins[10]);
        frames.bowling(pins[10]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);


        assertThat(totalScore).containsExactly("30");
    }

    @Test
    void bowling_STRIKE_STRIKE_STRIKE_STRIKE() {
        Frames frames = new Frames();
        frames.bowling(pins[10]);
        frames.bowling(pins[10]);
        frames.bowling(pins[10]);
        frames.bowling(pins[10]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30", "60");
    }

    @Test
    void bowling_STRIKE_STRIKE_STRIKE_OPEN() {
        Frames frames = new Frames();
        frames.bowling(pins[10]);
        frames.getScores();
        frames.bowling(pins[10]);
        frames.getScores();
        frames.bowling(pins[10]);
        frames.getScores();
        frames.bowling(pins[5]);
        frames.getScores();

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30", "55");
    }

    @Test
    void bowling_STRIKE_OPEN_MISS() {
        Frames frames = new Frames();
        frames.bowling(pins[10]);
        frames.bowling(pins[5]);
        frames.bowling(pins[4]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("19", "28");
    }

    @Test
    void bowling_OPEN_MISS() {
        Frames frames = new Frames();
        frames.bowling(pins[5]);
        frames.bowling(pins[4]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("9");
    }

    @Test
    void bowling_SPARE_FINAL_FRAME_OPEN_MISS() {
        Frames frames = new Frames();

        IntStream.range(0, 8)
                .forEach(idx -> frames.bowling(pins[10]));

        frames.bowling(pins[9]);
        frames.bowling(pins[1]);

        //final frame
        frames.bowling(pins[1]);
        frames.bowling(pins[1]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30", "60", "90", "120", "150", "180", "209", "229", "240", "242");
    }

    @Test
    void bowling_SPARE_FINAL_FRAME_STRIKE() {
        Frames frames = new Frames();

        IntStream.range(0, 8)
                .forEach(idx -> frames.bowling(pins[10]));

        frames.bowling(pins[9]);
        frames.bowling(pins[1]);

        //final frame
        frames.bowling(pins[10]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30", "60", "90", "120", "150", "180", "209", "229", "249");
    }

    @Test
    void bowling_STRIKE_FINAL_FRAME_OPEN() {
        Frames frames = new Frames();

        IntStream.range(0, 8)
                .forEach(idx -> frames.bowling(pins[10]));

        frames.bowling(pins[10]);

        //final frame
        frames.bowling(pins[1]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30", "60", "90", "120", "150", "180", "210", "231");
    }

    @Test
    void bowling_STRIKE_FINAL_FRAME_OPEN_MISS() {
        Frames frames = new Frames();

        IntStream.range(0, 8)
                .forEach(idx -> frames.bowling(pins[10]));

        frames.bowling(pins[10]);

        //final frame
        frames.bowling(pins[1]);
        frames.bowling(pins[1]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30", "60", "90", "120", "150", "180", "210", "231", "243", "245");
    }

    @Test
    void bowling_STRIKE_FINAL_FRAME_OPEN_SPARE() {
        Frames frames = new Frames();

        IntStream.range(0, 8)
                .forEach(idx -> frames.bowling(pins[10]));

        frames.bowling(pins[10]);

        //final frame
        frames.bowling(pins[1]);
        frames.bowling(pins[9]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30", "60", "90", "120", "150", "180", "210", "231", "251");
    }


    @Test
    void bowling_OPEN_SPARE_OPEN() {
        Frames frames = new Frames();
        frames.bowling(pins[5]);
        frames.bowling(pins[5]);
        frames.bowling(pins[1]);
        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("11");
    }

    @Test
    void bowling_OPEN_SPARE_STRIKE() {
        Frames frames = new Frames();
        frames.bowling(pins[9]);
        frames.bowling(pins[1]);
        frames.bowling(pins[10]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("20");
    }

    @Test
    void bowling_STRIKE_OPEN_SPARE() {
        Frames frames = new Frames();
        frames.bowling(pins[10]);
        frames.bowling(pins[1]);
        frames.bowling(pins[9]);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("20");
    }


    @Test
    void bowling_프레임_마지막_직전_까지_소진() {
        Frames frames = new Frames();
        for (int i = FrameNumber.MIN_FRAME_NUMBER; i < FrameNumber.MAX_FRAME_NUMBER; i++) {
            assertThat(frames.nowFrameNumber()).isEqualTo(i);
            frames.bowling(pins[10]);
            assertThat(frames.isFinished()).isFalse();
        }

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_종료() {

        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(pins[8]);
        frames.bowling(pins[1]);

        long resultCount = frames.getScores()
                .stream()
                .filter(result -> 0 < result.length())
                .count();

        assertThat(resultCount).isEqualTo(10);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_마지막_스트라이크() {
        Frames frames = getBeforeLastFrames();
        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(pins[10]);
        frames.bowling(pins[10]);
        frames.bowling(pins[10]);

        long resultCount = frames.getScores()
                .stream()
                .filter(result -> 0 < result.length())
                .count();

        assertThat(resultCount).isEqualTo(10);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_스트라이크_후_보너스() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(pins[10]);
        frames.bowling(pins[1]);
        frames.bowling(pins[1]);

        long resultCount = frames.getScores()
                .stream()
                .filter(result -> 0 < result.length())
                .count();

        assertThat(resultCount).isEqualTo(10);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_스페어_후_미스() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(pins[9]);
        frames.bowling(pins[1]);
        frames.bowling(pins[9]);

        long resultCount = frames.getScores()
                .stream()
                .filter(result -> 0 < result.length())
                .count();

        assertThat(resultCount).isEqualTo(10);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_마지막_스페어() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(pins[9]);
        frames.bowling(pins[1]);
        frames.bowling(pins[10]);

        long resultCount = frames.getScores()
                .stream()
                .filter(result -> 0 < result.length())
                .count();

        assertThat(resultCount).isEqualTo(10);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_보너스_없는_경우_계속_던짐() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(pins[8]);
        frames.bowling(pins[1]);

        assertThatIllegalArgumentException().isThrownBy(() -> frames.bowling(pins[10]));
    }

    private Frames getBeforeLastFrames() {
        Frames frames = new Frames();
        for (int i = FrameNumber.MIN_FRAME_NUMBER; i < FrameNumber.MAX_FRAME_NUMBER; i++) {
            frames.bowling(pins[10]);
        }
        return frames;
    }
}