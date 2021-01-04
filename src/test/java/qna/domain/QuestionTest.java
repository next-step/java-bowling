package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName(value = "Question을 지우면 Deleted가 true로 변경된다.")
    void delete() throws CannotDeleteException {
        Question Q3 = new Question("title3", "contens3").writeBy(UserTest.SANJIGI);
        assertThat(Q3.isDeleted()).isFalse();

        Q3.delete(UserTest.SANJIGI);
        assertThat(Q3.isDeleted()).isTrue();
    }

    @Test
    @DisplayName(value = "Owner가 아닌 사용자가 질문을 지우려고 하면 CannotDeleteException이 발생함.")
    void deleteByNotOwner() throws CannotDeleteException {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName(value = "답변자중 Owner가 아닌사람이 있으면 true, 없으면 false를 리턴한다.")
    void hasOtherUserAnswer() {
        assertThat(Q2.hasOtherUserAnswer(UserTest.SANJIGI)).isFalse();

        User person = new User(3L, "testUser", "password", "name", "test@slipp.net");
        Answer A3 = new Answer(person, QuestionTest.Q2, "Answers Contents3");
        Q2.addAnswer(A3);

        assertThat(Q2.hasOtherUserAnswer(UserTest.SANJIGI)).isTrue();
    }

    @Test
    @DisplayName(value = "Question을 삭제하면 DeleteHistories 객체를 리턴한다.")
    void delete_shouldReturnDeleteHistories() throws CannotDeleteException {
        assertThat(Q1.delete(UserTest.JAVAJIGI))
                .isEqualTo(DeleteHistories.from(DeleteHistory.of(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now())));
    }


}
