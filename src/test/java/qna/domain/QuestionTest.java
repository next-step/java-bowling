package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 질문을_삭제한다() throws CannotDeleteException {
        //given
        //when
        Q1.delete(UserTest.JAVAJIGI);
        //then
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 로그인_사용자와_질문자가_동일하지_않으면_질문을_삭제할_수_없다() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 본인의_답글만_있으면_질문을_삭제할_수_있다() throws CannotDeleteException {
        //given
        //when
        Q1.addAnswer(new Answer(UserTest.JAVAJIGI, Q1, "안녕하세요"));
        Q1.delete(UserTest.JAVAJIGI);
        //then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(Q1.getAnswers()).allSatisfy(answer -> {
            if (!answer.isDeleted()) {
                throw new IllegalArgumentException("답글 삭제 오류");
            }
        });
    }

    @Test
    void 다른_사용자의_답글이_있으면_질문을_삭제할_수_없다() {
        //given
        //when
        Q1.addAnswer(new Answer(UserTest.SANJIGI, Q1, "안녕하세요"));
        //then
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
