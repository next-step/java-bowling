package camp.nextstep.edu.nextstep8.bowling;

public class BowlingGame {
    public static void main(String[] args) {
//        String player = BowlingGameInput.getPlayer();
//        System.out.println(player);
        String player = BowlingGameInput.getPlayer();
        int frame = 1;

        ScoreBoard scoreBoard = new ScoreBoard();


        while(true) {
            System.out.print(String.format("|%5s", "NAME"));
            for(int i = 0; i < 10; i++) {
                System.out.print(String.format("|%5s", String.format("%02d", i+1)));
            }
            System.out.println("|");

            System.out.print(String.format("|%5s", player));
            for(int i = 0; i < 10; i++) {
                System.out.print(
                        String.format("|%5s", scoreBoard.get(i)
                                .map(f -> String.valueOf(f.score()))
                                .orElse(""))
                );
            }
            System.out.println("|");

            int score = BowlingGameInput.getHitCount(frame);
            scoreBoard.markScore(frame, score);

            if(frame++ == 10) {
                break;
            }
        }
    }
}


