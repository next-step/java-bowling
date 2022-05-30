package bowling.ui;

import static java.lang.System.out;

import java.util.Scanner;

public final class InputCui {
    private static final int NAME_LENGTH = 3;

    private static Scanner scanner = new Scanner(System.in);

    private InputCui() { }

    public static String inputParticipants() {
        out.printf("플레이어 이름은(%d english letters)?: ", NAME_LENGTH);
        String nameInitial = scanner.nextLine();

        if (!isValidNames(nameInitial)) {
            out.printf("플레이어의 이름 길이는 %d 의 문자열만 가능합니다." + System.lineSeparator(), NAME_LENGTH);
            return inputParticipants();
        }

        return nameInitial;
    }

    private static boolean isValidNames(String nameInitial) {
        return nameInitial.length() == NAME_LENGTH;
    }

    public static int inputHitCount(int frameNumber) {
        out.printf("%d 프레임 투구 : ", frameNumber);
        int hitCount = scanner.nextInt();
        scanner.nextLine();

        return hitCount;
    }
}
