package camp.nextstep.edu.nextstep8.bowling;

import java.util.Optional;

public class BowlingGame {
    public static void main(String[] args) {
        String player = BowlingGameInput.getPlayer();
        ScoreBoard scoreBoard = new ScoreBoard();

        int frame = 1;
        int score = 0;
        int spare = 0;

        while(true) {
            System.out.print(String.format("|%5s\t", "NAME"));
            for(int i = 1; i < 11; i++) {
                System.out.print(String.format("|%5s\t", String.format("%02d", i)));
            }
            System.out.println("|");

            System.out.print(String.format("|%5s\t", player));
            for(int i = 1; i < 11; i++) {

                System.out.print(
                        String.format("|%5s\t", Optional.ofNullable(scoreBoard.getFrame(i)).map(Frame::getFrameResultSymbol).orElse(""))
                );
            }
            System.out.println("|");

            score = BowlingGameInput.getHitCount(frame);
            if(score < 10) {
                spare = BowlingGameInput.getHitCount(frame);
            }

            scoreBoard.markScore(frame, score, spare);

            spare = 0;

            if(frame++ == 11) {
                break;
            }
        }
    }
}


