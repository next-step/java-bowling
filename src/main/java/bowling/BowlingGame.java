package bowling;

import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.Player;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BowlingGame {
    private static final Scanner USER_INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        String userName = USER_INPUT.nextLine();
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.println("|  " +
                userName +
                " |      |      |      |      |      |      |      |      |      |      |");

        Player initPlayer = Player.createByName(userName);

        // 1 프레임 처리
        System.out.println("1프레임 투구 : ");
        int numberOfHitPin = USER_INPUT.nextInt();
        USER_INPUT.nextLine();
        Player player = initPlayer.bowlFirst(numberOfHitPin);

        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.println("|  " +
                player.getName() + " |" +
                parsePlayerResult(player.calculateResult())
        );

        if (!player.isCurrentFrameCompleted()) {
            System.out.println("1프레임 투구 : ");
            player = player.bowlCurrentFrame(USER_INPUT.nextInt());
            USER_INPUT.nextLine();
            System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
            System.out.println("|  " +
                    player.getName() + " |" +
                    parsePlayerResult(player.calculateResult())
            );
        }

        // 2 ~ 9 프레임 처리
        while(true) {
            while (!player.isCurrentFrameCompleted()) {
                System.out.println((player.getCurrentFrameIndex()) + "프레임 투구 : ");
                player = player.bowlCurrentFrame(USER_INPUT.nextInt());
                USER_INPUT.nextLine();
                System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
                System.out.println("|  " +
                        player.getName() + " |" +
                        parsePlayerResult(player.calculateResult())
                );
            }
            if ((player.getCurrentFrameIndex() == 9) && (player.isCurrentFrameCompleted())) {
                break;
            }
            System.out.println((player.getCurrentFrameIndex() + 1) + "프레임 투구 : ");
            player = player.toNextFrame(USER_INPUT.nextInt());
            USER_INPUT.nextLine();
            System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
            System.out.println("|  " +
                    player.getName() + " |" +
                    parsePlayerResult(player.calculateResult())
            );
        }

        // 10 프레임 처리
        System.out.println("10프레임 투구 : ");
        player = player.toFinalFrame(USER_INPUT.nextInt());
        USER_INPUT.nextLine();

        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.println("|  " +
                player.getName() + " |" +
                parsePlayerResult(player.calculateResult())
        );

        while(!player.isCurrentFrameCompleted()) {
            System.out.println(10 + "프레임 투구 : ");
            player = player.bowlCurrentFrame(USER_INPUT.nextInt());
            USER_INPUT.nextLine();
            System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
            System.out.println("|  " +
                    player.getName() + " |" +
                    parsePlayerResult(player.calculateResult()) +
                    "|"
            );
        }
    }

    public static String parsePlayerResult(List<FrameResults> frameResultsList) {
        return frameResultsList.stream()
                .map(BowlingGame::parseFrameResults)
                .map(BowlingGame::formatResult)
                .collect(Collectors.joining("|"));
    }

    private static String parseFrameResults(FrameResults frameResults) {
        return frameResults.getList().stream()
                .map(FrameResult::getValue)
                .collect(Collectors.joining("|"));
    }

    private static String formatResult(String frameResult) {
        if (frameResult.length() == 1) {
            return "  " + frameResult + "   ";
        }
        if (frameResult.length() == 3) {
            return "  " + frameResult + " ";
        }
        return " " + frameResult;
    }
}
