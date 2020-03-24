package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.framestatus.Strike;
import bowling.domain.player.Player;

import java.util.LinkedList;

public class Bowling {

    private LinkedList<Frame> defaultFrames;
    private int frameNumber;
    private Player player;

    public Bowling() {
        defaultFrames = new LinkedList<>();
        frameNumber = 0;
    }

    public Bowling(Player player) {
        this.player = player;
        defaultFrames = new LinkedList<>();
        frameNumber = 0;
    }

    public void bowl(int hit) {
        if (frameNumber < 9) {
            createNormalFrame(hit);
            return;
        }
        createFinalFrame(hit);
    }

    private void createNormalFrame(int hit) {
        if (size() == 0) {
            createFrame(hit);
            return;
        }

        Frame frame = getRecentFrame();
        if (frame.getFrameStatus() instanceof Strike) {
            createFrame(hit);
            return;
        }

        if (frame.size() < 2) {
            frame.bowl(hit);
            return;
        }

        createFrame(hit);
    }

    private void createFinalFrame(int hit) {
        if (defaultFrames.getLast() instanceof FinalFrame) {
            FinalFrame finalFrame1 = getRecentFinalFrame();
            if (finalFrame1.size() == 2) {
                finalFrame1.bowlByBonus(hit);
                return;
            }
            finalFrame1.bowl(hit);
            return;
        }
        createFrameByFinal(hit);

    }

    private void createFrameByFinal(int hit) {
        FinalFrame finalFrame = new FinalFrame(++frameNumber);
        finalFrame.bowl(hit);
        defaultFrames.add(finalFrame);
    }

    private void createFrame(int hit) {
        NormalFrame normalFrame = new NormalFrame(++frameNumber);
        normalFrame.bowl(hit);
        defaultFrames.add(normalFrame);
    }

    private Frame getRecentFrame() {
        return defaultFrames.getLast();
    }

    private FinalFrame getRecentFinalFrame() {
        return (FinalFrame) defaultFrames.getLast();
    }

    private int size() {
        return defaultFrames.size();
    }

    public String displayPlayer() {
        return player.display();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public LinkedList<Frame> getDefaultFrames() {
        return new LinkedList<>(defaultFrames);
    }
}
