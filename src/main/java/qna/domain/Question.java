package qna.domain;

import org.hibernate.annotations.Where;

import qna.CannotDeleteException;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 어노테이션을 필수로 붙여야 합니다.
public class Question extends AbstractEntity {
	
	private static final String CANNOT_DELETE_AUTHORITY = "질문을 삭제할 권한이 없습니다.";
	
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL) // CascadeType.ALL – 모든 Cascade 적용
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public User getWriter() {
        return writer;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

	public DeleteHistory delete(User loginUser) throws CannotDeleteException{
		// 2. 로그인 사용자와 질문한 사용자가 같은 경우 삭제
		if(!isOwner(loginUser)) {
			throw new CannotDeleteException(CANNOT_DELETE_AUTHORITY);
		}
		// 1. soft delete
		this.deleted = true;
		
		DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, this.getId(), this.writer, LocalDateTime.now());
		
		return deleteHistory;
		
		
	}
}
