package bowling.ui;

import static java.lang.System.out;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class InputCui {
    private static final int NAME_LENGTH = 3;

    private static Scanner scanner = new Scanner(System.in);

    private InputCui() { }

    public static List<String> inputParticipants() {
        out.printf("참가자는 몇 명 인가요? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<String> names = new LinkedList<>();
        int index = 1;
        while (count-- > 0) {
            out.printf("플레이어 %d 의 이름은(%d english letters)?: ", index++, NAME_LENGTH);
            names.add(scanner.nextLine());
        }

        if (!names.stream()
            .allMatch(InputCui::isValidNames)) {
            out.printf("플레이어의 이름 길이는 %d 의 문자열만 가능합니다. 전체 다시 입력 ㅎㅎ" + System.lineSeparator(), NAME_LENGTH);
            return inputParticipants();
        }

        return Collections.unmodifiableList(names);
    }

    private static boolean isValidNames(String nameInitial) {
        return nameInitial.length() == NAME_LENGTH;
    }

    public static int inputHitCount(String name) {
        out.print(name + "'s turn: ");
        int hitCount = scanner.nextInt();
        scanner.nextLine();

        return hitCount;
    }
}
