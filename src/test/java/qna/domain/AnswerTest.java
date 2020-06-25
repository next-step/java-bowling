package qna.domain;




import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    void checkWriterTest() {
        Answers answers = new Answers(Arrays.asList(A1,A2));
        DeleteHistories deleteHistories = new DeleteHistories();
        assertThatThrownBy(() -> {
            answers.deleteALL(UserTest.JAVAJIGI, deleteHistories);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void answerDeleteTest() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(A1));
        DeleteHistories deleteHistories = new DeleteHistories();
        answers.deleteALL(UserTest.JAVAJIGI, deleteHistories);
        assertThat(A1.isDeleted()).isTrue();
    }
}
