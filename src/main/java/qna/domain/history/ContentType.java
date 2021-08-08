package qna.domain.history;

import qna.domain.answer.Answer;
import qna.domain.common.AbstractEntity;
import qna.domain.question.Question;

import java.util.Arrays;
import java.util.Optional;

public enum ContentType {
    QUESTION(Question.class),
    ANSWER(Answer.class);

    private final Class<?> matchesClassType;

    ContentType(final Class<?> matchesClassType) {
        this.matchesClassType = matchesClassType;
    }

    public static Optional<ContentType> of(AbstractEntity abstractEntity) {
        return Arrays.stream(values())
                .filter(iContentType -> iContentType.matchesClassType == abstractEntity.getClass())
                .findFirst();
    }
}
