package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The ClassRoom entity.
 * @author A true hipster
 */
@Entity
@Table(name = "class_room")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClassRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * fieldName
     */
    @Column(name = "class_name")
    private String className;

    @Column(name = "department")
    private String department;

    @Column(name = "year")
    private Integer year;

    @Column(name = "teacher_id")
    private Long teacherId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ClassRoom id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return this.className;
    }

    public ClassRoom className(String className) {
        this.setClassName(className);
        return this;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDepartment() {
        return this.department;
    }

    public ClassRoom department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getYear() {
        return this.year;
    }

    public ClassRoom year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }

    public ClassRoom teacherId(Long teacherId) {
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
        if (!(o instanceof ClassRoom)) {
            return false;
        }
        return getId() != null && getId().equals(((ClassRoom) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClassRoom{" +
            "id=" + getId() +
            ", className='" + getClassName() + "'" +
            ", department='" + getDepartment() + "'" +
            ", year=" + getYear() +
            ", teacherId=" + getTeacherId() +
            "}";
    }
}
