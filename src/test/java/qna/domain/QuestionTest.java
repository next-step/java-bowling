package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import static org.assertj.core.api.Assertions.*;
import static qna.domain.AnswerTest.*;
import static qna.domain.UserTest.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    void checkOwner() {
        assertThatThrownBy(
                () -> Q1.checkOwner(SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete() {
        DeleteHistories deleteHistories = new DeleteHistories();
        Q1.deleteQuestion(deleteHistories);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void checkAnswersOwner() {
        Q1.addAnswer(A2);
        assertThatThrownBy(
                () -> Q1.checkAnswersOwner(JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void addAnswer() {
        Q1.addAnswer(A1);
        assertThat(Q1.getAnswers().contains(A1)).isTrue();
    }
}
