package bowling.composer;

import java.util.regex.Pattern;

import bowling.exception.InstanceCreateException;

public class RegexComposer {

	private static final String INSTANCE_CREATE_ERROR_MESSAGE = "인스턴스 생성이 불가능 합니다.";
	private static final String REG_EXP = "^[a-zA-Z]*$";
	private static final Pattern pattern = Pattern.compile(REG_EXP);

	private RegexComposer() {
		throw new InstanceCreateException(INSTANCE_CREATE_ERROR_MESSAGE);
	}

	public static boolean isMatched(String name) {
		return pattern.matcher(name).find();
	}

}
