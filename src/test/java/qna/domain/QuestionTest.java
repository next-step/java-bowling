package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(1, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(2, "title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    void checkWriterTest() {
        Questions questions = new Questions(Q2);
        DeleteHistories deleteHistories = new DeleteHistories();
        assertThatThrownBy(() -> {
            questions.deleteQuestion(Q2.getId(), UserTest.JAVAJIGI, deleteHistories);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteQuestionTest() throws CannotDeleteException {
        Questions questions = new Questions(Q1);
        DeleteHistories deleteHistories = new DeleteHistories();
        questions.deleteQuestion(Q1.getId(), UserTest.JAVAJIGI, deleteHistories);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
