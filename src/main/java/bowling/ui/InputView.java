package bowling.ui;

import bowling.domain.game.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Player promptPlayer() {
        System.out.print("플레이어 이름을 입력해주세요(3 english letters): ");
        return new Player(SCANNER.nextLine());
    }

    public static List<Integer> promptNormalPins(int frameNo) {
        System.out.println(frameNo + "번 프레임 투구를 예시와 같이 입력해주세요. ex) 10,0");
        return Arrays.stream(SCANNER.nextLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> promptFinalPin() {
        System.out.println("마지막 프레임 투구를 예시와 같이 입력해주세요. ex) 5,5,8 or 5,4");
        return Arrays.stream(SCANNER.nextLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
