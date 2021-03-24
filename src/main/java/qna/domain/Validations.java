package qna.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Validations {

	NO_AUTHORIZED_USER(Question::isOwner, ExceptionFactory.NO_AUTHORIZED_USER),
	HAS_ANOTHER_USER_ANSWER(Question::countUnauthorizedUserAnswer, ExceptionFactory.ANOTHER_PERSON_ANSWER);
	private final BiFunction<Question, User, Boolean> checkFunc;
	private final ExceptionFactory exception;

	Validations(BiFunction<Question, User, Boolean> checkFunc, ExceptionFactory exception) {
		this.checkFunc = checkFunc;
		this.exception = exception;
	}

	public static void validate(Question question, User loginUser) {
		Arrays.stream(values())
			.filter(validation -> !validation.checkFunc.apply(question, loginUser))
			.findFirst()
			.ifPresent(validation -> {
				throw validation.exception.getException();
			});
	}
}
