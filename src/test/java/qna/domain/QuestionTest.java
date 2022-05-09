package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static qna.domain.ContentType.ANSWER;
import static qna.domain.ContentType.QUESTION;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    void delete_다른_사람질문_삭제() {
        Question question = new Question("title1", "contents1").writeBy(JAVAJIGI);

        assertThatThrownBy(() -> question.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_다른_사람이_작성한_답변_존재() {
        Question question = new Question("title1", "contents1").writeBy(JAVAJIGI);
        question.addAnswer(new Answer(SANJIGI, question, "answer content"));

        assertThatThrownBy(() -> question.delete(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_정상_동작() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(JAVAJIGI);
        Answer answer = new Answer(JAVAJIGI, question, "answer content");
        question.addAnswer(answer);

        assertThat(question.isDeleted()).isFalse();
        DeleteHistories deleteHistories = question.delete(JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories.contains(DeleteHistory.of(QUESTION, question.getId(), JAVAJIGI))).isTrue();
        assertThat(deleteHistories.contains(DeleteHistory.of(ANSWER, answer.getId(), JAVAJIGI))).isTrue();
    }

}
