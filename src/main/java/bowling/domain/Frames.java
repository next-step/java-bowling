package bowling.domain;

import bowling.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    public static final int MAP_ROUND_TO_INDEX_CONSTANT = 1;

    private final List<Frame> frames = new ArrayList<>();
    private final String player;

    public Frames(String player) {
        this.player = player;
    }

    public boolean playFrame(final int numberOfFallenPins, final Frame newFrame) {
        addFrame(newFrame);
        Frame frame = play(numberOfFallenPins);
        return frame.endFrame();
    }

    private void addFrame(final Frame frame) {
        if (frames.size() == 0) {
            frames.add(frame);
        }
        if (lastFrame().endFrame()) {
            frames.add(frame);
        }
    }

    private Frame play(final int numberOfFallenPins) {
        return lastFrame().play(lastIndex(), numberOfFallenPins);
    }

    public Frame lastFrame() {
        return frames.get(lastIndex());
    }

    private int lastIndex() {
        return frames.size() - MAP_ROUND_TO_INDEX_CONSTANT;
    }

    public List<Frame> getGameRecords() {
        return Collections.unmodifiableList(frames);
    }

    public String getPlayer() {
        return player;
    }

    public List<String> score() {
        List<String> scores = new ArrayList<>();
        for (int round = 0; round < frames.size(); round++) {
            int score = Bowl.READY;
            // strike
            score = scoreIfStrike(round);
            if (score != Bowl.READY) {
                scores.add(round, String.valueOf(score));
                continue;
            }
            // spare
            score = scoreIfSpare(round);
            if (score != Bowl.READY) {
                scores.add(round, String.valueOf(score));
                continue;
            }
            // completed
            score = scoreIfRemain(round);
            if (score != Bowl.READY) {
                scores.add(round, String.valueOf(score));
                continue;
            }
            // not completed
//            break;
            scores.add(round, "");
        }
        return scores;
    }

    private int scoreIfRemain(int round) {
        Frame frame = frames.get(round);
        if (!frame.endFrame()) {
            return Bowl.READY;
        }
        if (round == Controller.FINAL_FRAME - 1) {
            return scoreIfRemainInFinalFrame(round);
        }
        if (frame.first() == Bowl.MAX_NUMBER_OF_PIN || frame.first() + frame.second() == Bowl.MAX_NUMBER_OF_PIN) { // 리팩토링 필요...
            return Bowl.READY;
        }
        return frame.first() + frame.second();
    }

    private int scoreIfRemainInFinalFrame(int round) {
        LastFrame frame = (LastFrame) frames.get(round);
        if (frame.third() != Bowl.READY) {
            return frame.first() + frame.second() + frame.third();
        }
        return frame.first() + frame.second();
    }

    private int scoreIfStrike(int round) {
        if (round == Controller.FINAL_FRAME - 1) {
            return scoreIfStrikeInFinalFrame(round);
        }
        if (round == 8) {
            return scoreIfStrikeInBeforeFinalFrame(round);
        }
        return scoreIfStrikeInNormalFrame(round);
    }

    private int scoreIfStrikeInNormalFrame(int round) {
        Frame current = frames.get(round);
        if (current.first() != Bowl.MAX_NUMBER_OF_PIN) {
            return Bowl.READY;
        }
        if (!checkIfNextExist(round)) {
            return Bowl.READY;
        }
        if (frames.get(round + 1).first() != Bowl.MAX_NUMBER_OF_PIN && frames.get(round + 1).second() == Bowl.READY) {
            return Bowl.READY;
        }
        Frame next = frames.get(round + 1);
        if (next.first() != Bowl.MAX_NUMBER_OF_PIN) {
            return Bowl.MAX_NUMBER_OF_PIN  + next.first() + next.second();
        }
        if (!checkIfNextAfterNextExist(round)) {
            return Bowl.READY;
        }
        Frame nextAfterNext = frames.get(round + 2);
        return Bowl.MAX_NUMBER_OF_PIN * 2 + nextAfterNext.first();
    }

    private int scoreIfStrikeInBeforeFinalFrame(int round) {
        Frame current = frames.get(round);
        if (current.first() != Bowl.MAX_NUMBER_OF_PIN) {
            return Bowl.READY;
        }
        if (!(checkIfNextExist(round) && frames.get(round + 1).second() != Bowl.READY)) {
            return Bowl.READY;
        }
        Frame next = frames.get(round + 1);
        return Bowl.MAX_NUMBER_OF_PIN + ((LastFrame) next).first() + ((LastFrame) next).second();
    }

    private int scoreIfStrikeInFinalFrame(int round) {
        Frame current = frames.get(round);
        if (current.first() != Bowl.MAX_NUMBER_OF_PIN) {
            return Bowl.READY;
        }
        if (!current.endFrame()) {
            return Bowl.READY;
        }
        return Bowl.MAX_NUMBER_OF_PIN + ((LastFrame) current).second() + ((LastFrame) current).third();
    }

    private int scoreIfSpare(int round) {
        if (round == Controller.FINAL_FRAME) {
            return scoreIfSpareInFinalFrame(round);
        }
        return scoreIfSpareInNormalFrame(round);
    }

    private int scoreIfSpareInNormalFrame(int round) {
        Frame current = frames.get(round);
        if (current.first() == Bowl.MAX_NUMBER_OF_PIN) { // Strike 거르기...
            return Bowl.READY;
        }
        if (current.first() + current.second() != Bowl.MAX_NUMBER_OF_PIN) {
            return Bowl.READY;
        }
        if (!checkIfNextExist(round)) {
            return Bowl.READY;
        }
        Frame next= frames.get(round + 1);
        return Bowl.MAX_NUMBER_OF_PIN + next.first();
    }

    private int scoreIfSpareInFinalFrame(int round) {
        Frame current = frames.get(round);
        if (current.second() == Bowl.MAX_NUMBER_OF_PIN) { // Strike 거르기..
            return Bowl.READY;
        }
        if (current.first() + current.second() != Bowl.MAX_NUMBER_OF_PIN) {
            return Bowl.READY;
        }
        return Bowl.MAX_NUMBER_OF_PIN + ((LastFrame) current).third();
    }

    private boolean checkIfNextExist(int round) {
        if (round + 1 >= frames.size()) {
            return false;
        }
        return true;
    }

    private boolean checkIfNextAfterNextExist(int round) {
        if (round + 2 >= frames.size()) {
            return false;
        }
        return true;
    }
}
