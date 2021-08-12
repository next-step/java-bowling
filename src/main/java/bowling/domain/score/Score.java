package bowling.domain.score;

public interface Score {

    boolean isCompute();

    Score add(Score score);

    int getValue();

    Score NULL = new Score() {
        @Override
        public boolean isCompute() {
            return false;
        }

        @Override
        public Score add(Score score) {
            return NULL;
        }

        @Override
        public int getValue() {
            return 0;
        }
    };

}
