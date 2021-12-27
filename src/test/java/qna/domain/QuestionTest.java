package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("다른 사람이 작성한 질문을 삭제할 경우")
    void deleteByAnotherUser() {
        //then
        assertThatThrownBy(() -> Q1.deleteByUser(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }


    @Test
    @DisplayName("본인이 작성한 질문을 삭제할 경우")
    void deleteByWriter() throws CannotDeleteException {
        //when
        Q1.deleteByUser(UserTest.JAVAJIGI);
        //then
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("본인이 작성한 질문이지만, 다른 사람이 쓴 답변이 있을 경우")
    void deleteWithAnotherAnswer() {
        //given
        Q1.addAnswer(AnswerTest.A2);
        //then
        assertThatThrownBy(() -> Q1.deleteByUser(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("본인이 작성한 질문에 본인이 작성한 답변만 있을 경우")
    void deleteWithWriterAnswer() throws CannotDeleteException {
        //given
        Q2.addAnswer(AnswerTest.A2);
        //when
        Q2.deleteByUser(UserTest.SANJIGI);
        //then
        assertThat(Q2.isDeleted()).isTrue();
        assertThat(AnswerTest.A2.isDeleted()).isTrue();

    }

}
