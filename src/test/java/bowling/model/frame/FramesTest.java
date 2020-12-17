package bowling.model.frame;

import bowling.model.User;
import bowling.view.ResultView;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FramesTest {
    @Test
    void bowling_RANDOME() {
        for(int i = 0; i < 100; i++){
            Frames frames = new Frames();
            Random random = new Random();
            int previous = random.nextInt(11);
            while (!frames.isFinished()){
                int now = random.nextInt(11-previous);
                run(frames,now);
                previous = now;
            }
        }
    }

    private void run(Frames frames, int fallenPins){
        frames.bowling(fallenPins);
        ResultView.printHeadFrame();
        ResultView.printFrame(User.from("TST"), frames.result());
        ResultView.printFrame(User.from("TST"), frames.getScores());
    }

    @Test
    void bowling_STRIKE_STRIKE_OPEN() {
        Frames frames = new Frames();
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(1);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);


        assertThat(totalScore).containsExactly("21");
    }

    @Test
    void bowling_STRIKE_STRIKE_STRIKE() {
        Frames frames = new Frames();
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);


        assertThat(totalScore).containsExactly("30");
    }

    @Test
    void bowling_STRIKE_STRIKE_STRIKE_STRIKE() {
        Frames frames = new Frames();
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30","60");
    }

    @Test
    void bowling_STRIKE_OPEN_MISS() {
        Frames frames = new Frames();
        frames.bowling(10);
        frames.bowling(5);
        frames.bowling(4);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("19","28");
    }

    @Test
    void bowling_OPEN_MISS() {
        Frames frames = new Frames();
        frames.bowling(5);
        frames.bowling(4);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("9");
    }

    @Test
    void bowling_SPARE_FINAL_FRAME_OPEN_MISS() {
        Frames frames = new Frames();

        IntStream.range(0,8)
                .forEach(idx -> frames.bowling(10));

        frames.bowling(9);
        frames.bowling(1);

        //final frame
        frames.bowling(1);
        frames.bowling(1);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30","60","90","120","150","180","209","229","240","242");
    }

    @Test
    void bowling_SPARE_FINAL_FRAME_STRIKE() {
        Frames frames = new Frames();

        IntStream.range(0,8)
                .forEach(idx -> frames.bowling(10));

        frames.bowling(9);
        frames.bowling(1);

        //final frame
        frames.bowling(10);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30","60","90","120","150","180","209","229","249");
    }

    @Test
    void bowling_STRIKE_FINAL_FRAME_OPEN() {
        Frames frames = new Frames();

        IntStream.range(0,8)
                .forEach(idx -> frames.bowling(10));

        frames.bowling(10);

        //final frame
        frames.bowling(1);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30","60","90","120","150","180","210","231");
    }

    @Test
    void bowling_STRIKE_FINAL_FRAME_OPEN_MISS() {
        Frames frames = new Frames();

        IntStream.range(0,8)
                .forEach(idx -> frames.bowling(10));

        frames.bowling(10);

        //final frame
        frames.bowling(1);
        frames.bowling(1);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30","60","90","120","150","180","210","231","243", "245");
    }

    @Test
    void bowling_STRIKE_FINAL_FRAME_OPEN_SPARE() {
        Frames frames = new Frames();

        IntStream.range(0,8)
                .forEach(idx -> frames.bowling(10));

        frames.bowling(10);

        //final frame
        frames.bowling(1);
        frames.bowling(9);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("30","60","90","120","150","180","210","231","251");
    }


    @Test
    void bowling_OPEN_SPARE_OPEN() {
        Frames frames = new Frames();
        frames.bowling(5);
        frames.bowling(5);
        frames.bowling(1);
        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("11");
    }

    @Test
    void bowling_OPEN_SPARE_STRIKE() {
        Frames frames = new Frames();
        frames.bowling(9);
        frames.bowling(1);
        frames.bowling(10);

        String[] totalScore = frames.getScores()
                .stream()
                .filter(score -> 0 < score.length())
                .toArray(String[]::new);

        assertThat(totalScore).containsExactly("20");
    }

    @Test
    void bowling_STRIKE_OPEN_SPARE() {
        Frames frames = new Frames();
        frames.bowling(10);
        frames.bowling(1);
        frames.bowling(9);

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
            frames.bowling(10);
            assertThat(frames.isFinished()).isFalse();
        }
        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_종료() {

        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(8);
        frames.bowling(1);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_마지막_스트라이크() {
        Frames frames = getBeforeLastFrames();
        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_스트라이크_후_보너스() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(10);
        frames.bowling(1);
        frames.bowling(1);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_스페어_후_미스() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(9);
        frames.bowling(1);
        frames.bowling(9);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_시_정상_마지막_스페어() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(9);
        frames.bowling(1);
        frames.bowling(10);
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    void bowling_프레임_모두_소진_보너스_없는_경우_계속_던짐() {
        Frames frames = getBeforeLastFrames();

        assertThat(frames.nowFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER);
        frames.bowling(8);
        frames.bowling(1);

        assertThatIllegalArgumentException().isThrownBy(() -> frames.bowling(10));
    }

    private Frames getBeforeLastFrames() {
        Frames frames = new Frames();
        for (int i = FrameNumber.MIN_FRAME_NUMBER; i < FrameNumber.MAX_FRAME_NUMBER; i++) {
            frames.bowling(10);
        }
        return frames;
    }
}