package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The Subject entity.
 * @author A true hipster
 */
@Entity
@Table(name = "subject")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * fieldName
     */
    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "credit")
    private Integer credit;

    @Column(name = "teacher_id")
    private Long teacherId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Subject id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return this.subjectName;
    }

    public Subject subjectName(String subjectName) {
        this.setSubjectName(subjectName);
        return this;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getCredit() {
        return this.credit;
    }

    public Subject credit(Integer credit) {
        this.setCredit(credit);
        return this;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }

    public Subject teacherId(Long teacherId) {
        this.setTeacherId(teacherId);
        return this;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subject)) {
            return false;
        }
        return getId() != null && getId().equals(((Subject) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Subject{" +
            "id=" + getId() +
            ", subjectName='" + getSubjectName() + "'" +
            ", credit=" + getCredit() +
            ", teacherId=" + getTeacherId() +
            "}";
    }
}
