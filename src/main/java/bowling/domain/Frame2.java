package bowling.domain;

import java.util.LinkedList;

public class Frame2 {

    private int frameNumber;
    private LinkedList<NormalFrame> frame2s;

    public Frame2(int frameNumber) {
        this.frameNumber = frameNumber;
        if (frameNumber < 9) {
            this.frame2s = new LinkedList<>();
        }
    }

    public void bowl(int countOfHit) {
        frame2s.push(new NormalFrame(frameNumber, countOfHit));
    }

    public int getScore() {
        return frame2s.stream()
                .map(NormalFrame::getScore)
                .reduce(Integer::sum)
                .orElse(0)
                ;
    }
}
