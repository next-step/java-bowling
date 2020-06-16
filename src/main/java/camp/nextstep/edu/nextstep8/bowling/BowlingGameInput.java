package camp.nextstep.edu.nextstep8.bowling;

import java.util.Scanner;

public class BowlingGameInput {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static String getPlayer() {
        System.out.println("참여할 플레이어를 입력하세요 (최대 3글자, 알파뱃)");
        if(SCANNER.hasNextLine()) {
            return SCANNER.next().trim();
        }
        throw new IllegalArgumentException("입력된 값이 없습니다");
    }
}
