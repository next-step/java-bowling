package bowling.view;

import bowling.domain.frame.Round;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String inputName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public int inputPitch(Round round) {
        System.out.println(String.format("%d 프레임 투구 : ", round.value()));
        return scanner.nextInt();
    }
}
