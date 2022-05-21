package qna.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.user.User;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AbstractEntity() {
    }

    public AbstractEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public AbstractEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public DeleteHistory createDeleteHistory(ContentType contentType, User writer) {
        return new DeleteHistory(
                contentType, this.id, writer, LocalDateTime.now()
        );
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}
