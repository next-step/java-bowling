package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Answer extends AbstractEntity {
	
	private static final String CANNOT_DELETE_ANSWERS_BY_OTHERS = "다른 사람의 답변을 삭제 할 수 없습니다.";
	
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Lob // 필드의 길이 제한이 없다. CLOB,BLOB 타입을 매핑할 수 있다.
    private String contents;

    private boolean deleted = false;

    public Answer() {
    }

    public Answer(User writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, User writer, Question question, String contents) {
        super(id);

        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(User writer) {
        return this.writer.equals(writer);
    }

    public User getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

	public DeleteHistory deleteByOthers(User user) throws CannotDeleteException {
		// 6. 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
		if(!isOwner(user)) {
			throw new CannotDeleteException(CANNOT_DELETE_ANSWERS_BY_OTHERS);
		}		
		DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, this.getId(), this.writer, LocalDateTime.now());
		
		return deleteHistory;
	}
    
    
}
