package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void deleteQuestion() {
        List<DeleteHistory> history = Q1.delete(UserTest.JAVAJIGI);

        assertThat(history).containsOnly(DeleteHistory.question(Q1.getId(), UserTest.JAVAJIGI));
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    public void deleteByOtherUser() {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void deleteQuestionWithAnswers() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "answer");
        question.addAnswer(answer);

        List<DeleteHistory> history = question.delete(UserTest.JAVAJIGI);

        assertThat(history).containsOnly(DeleteHistory.question(question.getId(), UserTest.JAVAJIGI),
                DeleteHistory.answer(answer.getId(), UserTest.JAVAJIGI)
        );
        assertThat(question.isDeleted()).isEqualTo(true);
        assertThat(answer.isDeleted()).isEqualTo(true);
    }


}
