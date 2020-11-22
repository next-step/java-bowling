package qna.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A1;

public class AnswersTest {
    public static final Answers ANSWERS = Answers.of(Collections.singletonList(A1));

    @Test
    public void deleteAnswers() {
        List<DeleteHistory> histories = ANSWERS.delete(UserTest.JAVAJIGI);

        assertThat(histories).containsOnly(DeleteHistory.answer(A1.getId(), A1.getWriter()));
    }
}