package bowling.view;

import bowling.BowilingTerm;
import bowling.Frame;
import bowling.HitRecord;
import bowling.Username;

import java.util.List;

public class OutputView {
    private static final String BASE_SCORE_SCREEN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String FORMAT_SHAPE = "  %-4s|";
    private static final String SEPARATOR = "|";
    private static final int FIRST_ATTEMPT = 0;
    private static final int LAST_ATTEMPT = 1;
    private static final int ZERO_HIT_COUNT = 0;
    private static final int MAX_HIT_COUNT = 10;

    private OutputView() {
    }


    public static void printScoreScreen(Username username, List<Frame> frames) {
        System.out.println(BASE_SCORE_SCREEN);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|" + FORMAT_SHAPE, username.getName()));
        for (Frame frame : frames) {
            frameScore(sb, frame);
        }
        System.out.println(sb);
        System.out.println();
    }

    private static void frameScore(StringBuilder sb, Frame frame) {
        StringBuilder hitBuilder = new StringBuilder();
        List<HitRecord> hitRecords = frame.getHitRecords();

        int hitCount = 0;
        for (int index = 0; index < hitRecords.size(); index++) {
            HitRecord hitRecord = hitRecords.get(index);
            hitCount += hitRecord.getHitCount();
            if (index == 1) {
                hitBuilder.append(SEPARATOR);
                if (hitCount == MAX_HIT_COUNT) {
                    hitBuilder.append(BowilingTerm.SPARE.shape());
                    continue;
                }
            }

            if (index == 0 && hitRecord.hitAll()) {
                hitBuilder.append(BowilingTerm.STRIKE.shape());
                continue;
            }

            if (hitRecord.hitZero()) {
                hitBuilder.append(BowilingTerm.GUTTER.shape());
                continue;
            }
            hitBuilder.append(hitRecord.getHitCount());
        }
        sb.append(String.format(FORMAT_SHAPE, hitBuilder));
    }
}
