package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    LocalDateTime deleteDateTime = LocalDateTime.now();

    @Test
    public void deleteQuestion() {
        List<DeleteHistory> history = Q1.delete(UserTest.JAVAJIGI, deleteDateTime);

        assertThat(history).containsOnly(new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, deleteDateTime));
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    public void deleteByOtherUser() {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI, deleteDateTime);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void deleteQuestionWithAnswers() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "answer");
        question.addAnswer(answer);

        List<DeleteHistory> history = question.delete(UserTest.JAVAJIGI, deleteDateTime);

        assertThat(history).containsOnly(new DeleteHistory(ContentType.QUESTION, question.getId(), UserTest.JAVAJIGI, deleteDateTime),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, deleteDateTime)
        );
        assertThat(question.isDeleted()).isEqualTo(true);
        assertThat(answer.isDeleted()).isEqualTo(true);
    }


}
