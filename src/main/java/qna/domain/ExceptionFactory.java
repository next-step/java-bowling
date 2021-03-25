package qna.domain;

import java.lang.reflect.InvocationTargetException;

import qna.CannotDeleteException;
import qna.NotFoundException;

public enum ExceptionFactory {
	NO_AUTHORIZED_USER(CannotDeleteException.class, "질문을 삭제할 권한이 없습니다."),
	ANOTHER_PERSON_ANSWER(CannotDeleteException.class, "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

	private final Class<? extends RuntimeException> clazz;
	private final String message;

	ExceptionFactory(Class<? extends RuntimeException> clazz, String message) {
		this.message = message;
		this.clazz = clazz;
	}

	public RuntimeException getException() {
		try {
			return this.clazz
				.getDeclaredConstructor(String.class)
				.newInstance(this.message);
		} catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
			throw new NotFoundException();
		}
	}
}
