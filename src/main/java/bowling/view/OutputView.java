package bowling.view;

import bowling.*;

import java.util.List;

public class OutputView {
    private static final String BASE_SCORE_SCREEN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";
    private static final String FORMAT_SHAPE = "  %-4s|";
    private static final String BONUS_SHAPE = "  %-6s|";
    private static final String SEPARATOR = "|";
    private static final int MAX_HIT_COUNT = 10;

    private OutputView() {
    }


    public static void printScoreScreen(Username username, Frames frames) {
        List<Frame> framesList = frames.getFrames();
        System.out.println(BASE_SCORE_SCREEN);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|" + FORMAT_SHAPE, username.getName()));
        for (int i = 0; i < framesList.size(); i++) {
            String hitShape = frameScore(sb, framesList.get(i));
            if (i == framesList.size() - 1) {
                sb.append(String.format(BONUS_SHAPE, hitShape));
                continue;
            }
            sb.append(String.format(FORMAT_SHAPE, hitShape));
        }
        System.out.println(sb);
        System.out.println();
    }

    private static String frameScore(StringBuilder sb, Frame frame) {
        StringBuilder hitBuilder = new StringBuilder();
        List<HitRecord> hitRecords = frame.getHitRecords();

        int hitCount = 0;
        for (int index = 0; index < hitRecords.size(); index++) {
            HitRecord hitRecord = hitRecords.get(index);
            hitCount += hitRecord.getHitCount();
            if (index != 0) {
                hitBuilder.append(SEPARATOR);
                if (hitCount == MAX_HIT_COUNT) {
                    hitBuilder.append(BowilingTerm.SPARE.shape());
                    continue;
                }
            }

            if (index != 1 && hitRecord.hitAll()) {
                hitBuilder.append(BowilingTerm.STRIKE.shape());
                continue;
            }

            if (hitRecord.hitZero()) {
                hitBuilder.append(BowilingTerm.GUTTER.shape());
                continue;
            }
            hitBuilder.append(hitRecord.getHitCount());
        }
        return hitBuilder.toString();
    }
}
