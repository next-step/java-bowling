package domain;

public class Score {

    private int first;
    private int second;

    public Score() {
        this.first = -1;
        this.second = -1;
    }

    public int bowl(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("음수는 입력 할 수 없습니다.");
        }

        if (this.first == -1) {
            return firstBall(score);
        }
        if (this.second == -1) {
            return secondBall(score);
        }

        throw new IllegalStateException("한 프레임에 공을 세 번 이상 던질 수 없습니다.");
    }

    private int firstBall(int score) {
        this.first = score;
        return sumScore();
    }

    private int secondBall(int score) {
        if(first == 10) {
            throw new IllegalStateException("초구가 10점이면 더이상 던질 수 없습니다.");
        }
        this.second = score;
        return sumScore();
    }

    private int sumScore() {
        int sum = first + (second == -1 ? 0 : second);
        if (sum > 10) {
            throw new IllegalArgumentException("점수는 10점을 초과할 수 없습니다.");
        }
        return sum;
    }
}
