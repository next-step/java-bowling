package qna.domain;


import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;
import qna.domain.entity.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.fail;


public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete_성공() {
        try {
            List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);

            assertThat(Q1.isDeleted()).isTrue();
            assertThat(deleteHistories.size()).isEqualTo(1);
        } catch (CannotDeleteException e) {
            fail("delete 과정에서 CannotDeleteException 발생.");
        }
    }

    @Test
    void delete_다른_사람이_쓴_글(){
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q2.delete(UserTest.JAVAJIGI))
                .withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }
}
