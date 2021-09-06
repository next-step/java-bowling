package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

@SuppressWarnings("NonAsciiCharacters")
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    public void 질문을_삭제할_수_있다() throws CannotDeleteException {
        //given
        //when
        Question result = Q1.delete(JAVAJIGI);
        //then
        assertThat(result.isDeleted()).isTrue();
    }

}
