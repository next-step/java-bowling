package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.service.DeleteHistoryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    Question instanceQ1;
    Question instanceQ2;

    @BeforeEach
    void setup() {
        instanceQ1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        instanceQ2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

    @DisplayName("질문 작성자가 아닌 사람이 delete 시도 시 (Exception 발생)")
    @Test
    void delete_notQustionOwner() {
        assertThatThrownBy(() -> Q1.deleteBy(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> Q2.deleteBy(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변이 없는 경우 삭제가 가능하다.")
    @Test
    void delete_noAnswer() throws CannotDeleteException {
        instanceQ1.deleteBy(UserTest.JAVAJIGI);
        assertThat(instanceQ1.isDeleted()).isTrue();
    }

    @DisplayName("답변들의 작성자가 질문 작성자와 동일하다면 삭제 가능하다.")
    @Test
    void delete_answersByWriter() throws CannotDeleteException {
        instanceQ1.addAnswer(AnswerTest.A1);
        instanceQ1.deleteBy(UserTest.JAVAJIGI);
        assertThat(instanceQ1.isDeleted()).isTrue();
    }

    @DisplayName("답변들의 작성자가 질문 작성자와 모두 동일하지 않다면 삭제 불가하다. (Exception 발생)")
    @Test
    void delete_answersByDeferentWriter() {
        instanceQ1.addAnswer(AnswerTest.A1);
        instanceQ1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> instanceQ1.deleteBy(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
