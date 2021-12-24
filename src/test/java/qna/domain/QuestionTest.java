package qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static qna.domain.User.GUEST_USER;

import org.junit.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void 작성자가_미로그인시_삭제_데이터() {
        assertThatThrownBy(() -> {
            Q2.getDeleteHistories(GUEST_USER, UserTest.DEFAULT_TIME);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 같은_작성자_답변글_미존재() throws CannotDeleteException {
        Question question = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), UserTest.JAVAJIGI, UserTest.DEFAULT_TIME);
        assertThat(question.getDeleteHistories(UserTest.JAVAJIGI, UserTest.DEFAULT_TIME)).isEqualTo(Arrays.asList(deleteHistory));
    }

    @Test
    public void 다른_작성자_답변글_미존재_CannotDeleteException() {
        assertThatThrownBy(() -> {
            Question question = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);
            question.getDeleteHistories(UserTest.SANJIGI, UserTest.DEFAULT_TIME);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 같은_작성자_같은_답변글() throws CannotDeleteException {
        Question question = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        DeleteHistory questionHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), UserTest.JAVAJIGI, UserTest.DEFAULT_TIME);
        DeleteHistory answerHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, UserTest.DEFAULT_TIME);

        assertThat(question.getDeleteHistories(UserTest.JAVAJIGI, UserTest.DEFAULT_TIME)).isEqualTo(Arrays.asList(questionHistory, answerHistory));
    }

    @Test
    public void 같은_작성자_다른_답변글_CannotDeleteException() {
        assertThatThrownBy(() -> {
            Question question = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);
            question.addAnswer(AnswerTest.A2);
            question.getDeleteHistories(UserTest.JAVAJIGI, UserTest.DEFAULT_TIME);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 같은_작성자_같은_답변글_포함_다른_답변글_CannotDeleteException() {
        assertThatThrownBy(() -> {
            Question question = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);
            question.addAnswer(AnswerTest.A1);
            question.addAnswer(AnswerTest.A2);
            question.getDeleteHistories(UserTest.JAVAJIGI, UserTest.DEFAULT_TIME);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 질문_삭제이력_비교() {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, UserTest.DEFAULT_TIME);
        assertThat(Q1.getDeleteHistory(UserTest.DEFAULT_TIME)).isEqualTo(deleteHistory);
    }
}
