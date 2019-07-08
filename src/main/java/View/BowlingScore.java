package View;

public interface BowlingScore {
	boolean bowl(int score);

	boolean nowBowlable();

	int sumScore();

	int getPointSize();

	String getResult();
}
