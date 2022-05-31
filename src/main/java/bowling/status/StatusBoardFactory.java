package bowling.status;

import bowling.frame.ShootScore;

public class StatusBoardFactory {

    private static final String GUTTER_SIGNATURE = "-";

    private StatusBoardFactory() { }

    public static String drawGutterOrScore(ShootScore shootScore) {
        if (shootScore.isGutter()) {
            return GUTTER_SIGNATURE;
        }
        return Integer.toString(shootScore.getShootScore());
    }
}
