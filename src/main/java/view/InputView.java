package view;

import domain.Frames;

import java.util.Scanner;

public class InputView {

    public static String inputPlayerName(){
        Scanner sc = new Scanner(System.in);
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return sc.nextLine();
    }


    public static int inputThrowBall(Frames frameCount) {
        Scanner sc = new Scanner(System.in);
        System.out.print(String.format("%d프레임 투구 : ",frameCount.getSize()));
        return sc.nextInt();
    }
}
