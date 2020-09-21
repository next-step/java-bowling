package bowling.view;

import bowling.Bowling;

public class OutputView {

	private final String LINE = "| NAME |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |\n";

	private final Bowling bowling;
	private final String userName;

	private OutputView(String userName, Bowling bowling) {
		this.userName = userName;
		this.bowling = bowling;
	}

	public static OutputView make(String userName, Bowling bowling){
		return new OutputView(userName, bowling);
	}

	public void printFrame(){
		System.out.print((bowling.getCurrentFrameIndex()+1)+"프레임 투구 : ");
	}

	public void printResult() {
		StringBuilder str = new StringBuilder();
		str.append(LINE);
		str.append("|");
		str.append(String.format("%-7.7s", userName));
		str.append("|");
		str.append(bowling.print());
		str.append("\n");

		System.out.println(str.toString());
	}
}
