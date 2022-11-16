package bowling.view;

import bowling.*;

import java.util.List;

public class OutputView {
    private static final String BASE_SCORE_SCREEN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";
    private static final String START_SHAPE = "|      |";
    private static final String FORMAT_SHAPE = "  %-4s|";
    private static final String BONUS_SHAPE = "  %-6s|";
    private static final String SEPARATOR = "|";
    private static final String BLANK = "";

    private OutputView() {
    }


    public static void printScoreScreen(Username username, Frames frames, BowlingGame bowlingGame) {
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

        int totalScore = 0;
        sb.append(START_SHAPE);
        for (int i = 0; i < framesList.size() - 1; i++) {
            Frame frame = framesList.get(i);
            FrameNumber frameNumber = bowlingGame.getFrameNumber();
            String score = makeFrameScore(i, frame.hitRecords(), frameNumber, totalScore);
            if (!score.equals("")) {
                totalScore += Integer.parseInt(score);
                score = String.valueOf(totalScore);
            }
            sb.append(String.format(FORMAT_SHAPE, score));
        }
        FrameNumber frameNumber = bowlingGame.getFrameNumber();
        lastFrame = framesList.get(framesList.size() - 1);
        String score = makeFrameScore(framesList.size() - 1, lastFrame.hitRecords(), frameNumber, totalScore);
        if (!score.equals("")) {
            totalScore += Integer.parseInt(score);
            score = String.valueOf(totalScore);
        }
        sb.append(String.format(BONUS_SHAPE, score));
        sb.append("\n");
        System.out.println(sb);
    }

    private static String makeFrameScore(int index, HitRecords hitRecords, FrameNumber frameNumber, int totalScore) {
        Score score = hitRecords.getScore();
        if (score.remainBonus() || index + 1 >= frameNumber.getFrameNumber()) {
            return "";
        }
        return String.valueOf(score.getHitCount());
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
