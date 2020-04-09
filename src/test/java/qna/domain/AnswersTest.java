package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변들 중에 다른사람이 쓴 답변이 있다면 삭제시킬 수 없다")
    @Test
    void checkAnswers() throws CannotDeleteException {
        Answers answers = new Answers(Stream.of(A1, A2).collect(Collectors.toList()));
        assertThatThrownBy(() -> {
            answers.checkDeleteAnswers(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);

    }
}
