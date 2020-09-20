package bowling;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("플레이어 이름은(3 english letters)?");
        String userName = scanner.nextLine();

        Bowling bowling = Bowling.start();

        String line = "| NAME |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |\n";


        while(!bowling.isTerminated()){
            System.out.print((bowling.getCurrentFrameIndex()+1)+"프레임 투구 : ");
            int bowl = scanner.nextInt();

            Frame frame = bowling.bowl(bowl);
            StringBuilder str = new StringBuilder();
            str.append(line);
            str.append(String.format("| %s  |", userName));
            str.append(bowling.print());
            str.append("\n");
            System.out.println(str.toString());
        }

    }
}
