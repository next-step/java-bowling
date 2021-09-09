package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

@SuppressWarnings("NonAsciiCharacters")
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    public void 질문을_삭제할_수_있다() throws CannotDeleteException {
        //given
        //when
        Question result = new Question("title1", "contents1").writeBy(JAVAJIGI);
        result.delete(JAVAJIGI);
        //then
        assertThat(result.isDeleted()).isTrue();
    }

    @Test
    public void 질문삭제_시_작성자가_다르면_익셉션이_발생한다() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Q1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    public void Question으로_부터_DeleteHistory를_만들_수_있다() throws CannotDeleteException {
        //given
        Question result = new Question("title1", "contents1").writeBy(JAVAJIGI);
        //when
        result.delete(JAVAJIGI);
        DeleteHistory deleteHistory = result.toDeleteHistory();
        //then
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.QUESTION, result.getId(), JAVAJIGI));
    }

    @Test
    public void Question으로_부터_DeleteHistory를_만들_때_삭제된_Question이_아니면_익셉션이_발생한다() {
        //given
        Question result = new Question("title1", "contents1").writeBy(JAVAJIGI);
        //when
        //then
        assertThatThrownBy(result::toDeleteHistory)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("삭제 기록을 만들 수 없습니다.");
    }

}
