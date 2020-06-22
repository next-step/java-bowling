package camp.nextstep.edu.nextstep8.bowling;

public class Frame {
    private final int score;
    private final int spare;

    public Frame(int score, int spare) {
        this.score = score;
        this.spare = spare;
    }

    public int getScore() {
        return this.score;
    }

    public String getFrameResultSymbol() {
        if(score == 10 && spare == 0) {
            return "X";
        }

        if(score + spare == 10) {
            return score + "|" + "/";
        }

        return score + "|" + spare;

    }

//    프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현
}
