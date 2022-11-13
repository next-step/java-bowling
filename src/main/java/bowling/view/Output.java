package bowling.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import bowling.domain.Pitch;
import bowling.domain.Frames;

public class Output {
    private static final int MAX_FRAME_COLUMN_SIZE = 7;
    
    private final Frames result;

    public Output(final Frames result) {
        this.result = result;
    }

    public void show() {
        for (Pitch pitch : result.getPitches()) {
            showPitch(pitch);
        }
    }

    private void showPitch(final Pitch pitch) {
        System.out.println(pitch.getFrameNumber() + "프레임 투구 : " + pitch.getPinCount());
        showHeaderColumn();
        showPinCountHistories(pitch);
        System.out.println();
    }
    
    private void showHeaderColumn() {
        System.out.print("| NAME |");
        for (int round : result.getFrameNumbers()) {
            System.out.print("   " + getFrameNumber(round) + "  |");
        }
        System.out.println();
    }

    private String getFrameNumber(int round) {
        if (round < 10) {
            return "0" + round;
        }
        return String.valueOf(round);
    }

    private void showPinCountHistories(final Pitch lastPitch) {
        System.out.print("|  " + result.getUserName() + " |");
        Map<Integer, List<Pitch>> frames = result.getPitches(lastPitch).stream().collect(Collectors.groupingBy(Pitch::getFrameNumber));
        for (int round : result.getFrameNumbers()) {
            showPinCount(frames.get(round));
        }
    }

    private void showPinCount(List<Pitch> frames) {
        if (frames == null) {
            showPinCount();
            return;
        }
        String counts = ' ' + frames.stream().map(count -> CountView.getCount(count)).collect(Collectors.joining("|")) + ' ';
        String blank = " ".repeat((MAX_FRAME_COLUMN_SIZE - counts.length()) / 2);
        System.out.print(blank + counts + blank + "|");
    }

    private void showPinCount() {
        String blank = "       ";
        System.out.print(blank + '|');
    }
}
