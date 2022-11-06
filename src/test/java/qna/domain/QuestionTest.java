package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private ApplicationEventPublisher eventPublisher;

    @Test
    void 로그인한_사용자가_질문자가_아닌경우_삭제_불가능() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI, eventPublisher))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 질문_삭제() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI, eventPublisher);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
