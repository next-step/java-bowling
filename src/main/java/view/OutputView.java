package view;

import dto.ScoreDto;
import frame.Frame;
import frame.Frames;
import score.ScoreInfo;

import java.util.List;

public class OutputView {

    public static void showBasic() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void showName(String name) {
        System.out.print(String.format("|  %s |", name));
    }

    public static void showFrame(Frames frames) {

        for (int i = 0; i < 9; i++) {
            Frame frame = frames.findFrame(i);
            List<ScoreInfo> scoreInfos = frame.getScoreInfos();
            ScoreDto scoreDto = ScoreDto.of(scoreInfos);
            System.out.print(String.format("%3s%-3s|", scoreDto.getFirst(), scoreDto.getSecond()));
        }
    }

    public static void showLastFrame() {
        System.out.print("      |");
    }
}
