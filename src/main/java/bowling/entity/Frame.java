package bowling.entity;

import java.util.List;

public interface Frame {

	List<Ward> getWards();

	boolean isKeepGoing();

	String getScoreString();

	boolean isLast();

	int getScore();

	int getRemainderCount();
}
