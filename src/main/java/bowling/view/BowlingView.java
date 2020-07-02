package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.player.Player;
import bowling.domain.pitching.Pitching;
import bowling.util.StringUtil;

import java.util.Scanner;

public class BowlingView {

    private static final int MAX_FRAME_NUMBER = 10;

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int inputFallenPinNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void outputFrameNumberPitching(int frameNumber) {
        System.out.print(frameNumber + "프레임 투구 : ");
    }

    public static void displayBowlingDefaultFrameView(Player player) {
        displayBowlingFrameView(player);
        displayLeftPitchingView(10);
    }

    private static void displayBowlingFrameView(Player player) {
        System.out.print("| NAME ");
        for (int i = 1; i < MAX_FRAME_NUMBER; i++) {
            String value = "0" + i;
            System.out.print("|" + StringUtil.center(value, 6));
        }
        System.out.println("|" + StringUtil.center(MAX_FRAME_NUMBER + "", 6) + "|");
        System.out.print("|" + StringUtil.center(player.toString(), 6));
    }

    public static void displayPitchingView(Player player, Frame frame) {
        displayBowlingFrameView(player);
        int left = 10;
        Frame current = frame;
        while (current != null) {
            left--;
            Pitching pitching = current.getPitching();
            System.out.print("|" + StringUtil.center(pitching.getPitchingDescription(), 6));
            current = current.getNextFrame();
        }
        displayLeftPitchingView(left);
    }

    private static void displayLeftPitchingView(int left) {
        for (int i = 0; i < left; i++) {
            System.out.print("|" + StringUtil.center(" ", 6));
        }

        System.out.println("|");
        System.out.println();
    }
}
