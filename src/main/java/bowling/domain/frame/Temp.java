package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Temp {

    private final List<Frame> frames;
    private Frame current;

    public Temp() {
        this.frames = init();
        this.current = frames.get(0);
    }

    public static void main(String[] args) {
        Temp frames = new Temp();
        Pins pins = Pins.valueOf(2);
        List<Score> scores1 = frames.scores();
        scores1.stream().map(Score::score).map(String::valueOf).map(str -> str+"\t").forEach(System.out::print);
        System.out.println("\n");
        for(int i=1 ; i <= 20; i ++) {
            System.out.println(i);
            frames.bowl(pins);
            List<Score> scores = frames.scores();
            scores.stream().map(Score::score).map(String::valueOf).map(str -> str+"\t").forEach(System.out::print);
            System.out.println("\n");
        }
    }

    private List<Frame> init() {
        List<Frame> frames = IntStream.range(Frame.START_SEQUENCE, FinalFrame.LAST_SEQUENCE)
                .mapToObj(NormalFrame::from)
                .collect(Collectors.toList());
        frames.add(FinalFrame.initialize());
        return frames;
    }

    public void bowl(Pins pins) {
        current = current.bowl(pins);
        frames.set(current.sequence()-1, current);
    }

    public List<Score> scores() {
        return frames.stream()
                .filter(Frame::isFinish)
                .map(this::score)
                .collect(Collectors.toList());
    }

    private Score score(Frame frame) {
        Frame now = frame;
        Score score = now.score();
        while (!score.isFinish()) {
           now = nextFrame(now);
           score = now.calculateAdditionalScore(score);
        }
        return score;
    }

    private Frame nextFrame(Frame frame) {
        int nextIndex = frame.sequence();
        if(nextIndex == Frame.LAST_SEQUENCE) {
            return frame;
        }
        return frames.get(nextIndex);
    }


}
