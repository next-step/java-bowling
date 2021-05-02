package bowling.ui;

import java.util.Scanner;

public class Placeholder {
    private final Scanner scanner;

    public Placeholder() {
        scanner = new Scanner(System.in);
    }

    public String inputFromUser() {
        return scanner.nextLine();
    }

    public void printNamingMessage() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
    }

    public void printInputScoreMessage(int frameNumber) {
        System.out.print(frameNumber + "프레임 투구 : ");
    }
}
