package bowling.view;

import bowling.*;

import java.util.List;

public class OutputView {
    private static final String BASE_SCORE_SCREEN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";
    private static final String FORMAT_SHAPE = "  %-4s|";
    private static final String BONUS_SHAPE = "  %-6s|";
    private static final String SEPARATOR = "|";
    private static final String BLANK = "";

    private OutputView() {
    }


    public static void printScoreScreen(Username username, Frames frames) {
        System.out.println(BASE_SCORE_SCREEN);

        StringBuilder sb = new StringBuilder();
        sb.append(makeUserFormat(username));

        List<Frame> framesList = frames.getFrames();
        for (int i = 0; i < framesList.size() - 1; i++) {
            Frame frame = framesList.get(i);
            String hitShape = makeFrameScoreShape(frame.getHitRecords());
            sb.append(String.format(FORMAT_SHAPE, hitShape));
        }
        Frame lastFrame = framesList.get(framesList.size() - 1);
        sb.append(String.format(BONUS_SHAPE, makeFrameScoreShape(lastFrame.getHitRecords())));

        sb.append("\n");
        System.out.println(sb);
    }

    private static String makeUserFormat(Username username) {
        return String.format(SEPARATOR + FORMAT_SHAPE, username.getName());
    }

    private static String makeFrameScoreShape(List<HitRecord> hitRecords) {
        if (hitRecords.isEmpty()) {
            return BLANK;
        }

        StringBuilder hitBuilder = new StringBuilder();
        HitRecord hitRecordFirst = hitRecords.get(0);
        hitBuilder.append(retrieveHitShape(hitRecordFirst));

        for (int index = 1; index < hitRecords.size(); index++) {
            HitRecord hitRecord = hitRecords.get(index);
            hitBuilder.append(SEPARATOR);
            hitBuilder.append(retrieveHitShape(hitRecord));
        }

        return hitBuilder.toString();
    }

    private static String retrieveHitShape(HitRecord hitRecord) {
        BowilingTerm bowilingTerm = hitRecord.getBowilingTerm();
        if (bowilingTerm.equals(BowilingTerm.MISS)) {
            return String.valueOf(hitRecord.getHitCount());
        }
        return bowilingTerm.shape();
    }
}
