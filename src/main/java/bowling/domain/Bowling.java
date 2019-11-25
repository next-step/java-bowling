package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

    private List<Frames> frames;

    public Bowling() {
        this.frames = create();
    }

    private List<Frames> create() {
        List<Frames> elapsedFrame = new ArrayList<>();
        Frames frames = Frames.initiate(ScoreGenerator.getScore());
        elapsedFrame.add(frames);
        while (frames.size() < 10) {
            if (frames.isRemain()) {
                int remainScore = 10 - frames.getRemainScore();
                elapsedFrame.add(frames.nextSecond(ScoreGenerator.getScoreBySecond(remainScore)));
            }
            elapsedFrame.add(frames.next(ScoreGenerator.getScore()));
        }
        return elapsedFrame;
    }

    public List<Frames> getFrames() {
        return new ArrayList<>(frames);
    }
}
