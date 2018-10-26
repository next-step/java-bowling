package bowling.frame;

public class FrameResult {
    private String desc;
    private int score;
    private int totalScore = -1;

    public FrameResult(String desc, int score) {
        this.desc = desc;
        this.score = score;
    }

    public int addTotalScore(int beforeTotalScore) {
        if (isUnScore()) {
            this.totalScore = this.score;
            return this.totalScore;
        }

        this.totalScore = this.score + beforeTotalScore;
        return this.totalScore;
    }

    boolean isUnScore() {
        return this.score == NormalFrame.UN_SCORE_STATE;
    }

    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + score;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FrameResult other = (FrameResult) obj;
        if (desc == null) {
            if (other.desc != null)
                return false;
        } else if (!desc.equals(other.desc))
            return false;
        if (score != other.score)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FrameResult [desc=" + desc + ", score=" + score + ", totalScore=" + totalScore + "]";
    }
}
