package bowling.view;

import java.util.Scanner;

public class InputView {

    public String getName() {
        System.out.println("플레이어 이름은? (3 english letters?) : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public int getBowl(int order) {
        System.out.println(order + "프레임 투구 : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
