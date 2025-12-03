package com.mycompany.myapp.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ClassRoom} entity.
 */
@Schema(description = "The ClassRoom entity.\n@author A true hipster")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClassRoomDTO implements Serializable {

    private Long id;

    @Schema(description = "fieldName")
    private String className;

    private String department;

    private Integer year;

    private Long teacherId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClassRoomDTO)) {
            return false;
        }

        ClassRoomDTO classRoomDTO = (ClassRoomDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, classRoomDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClassRoomDTO{" +
            "id=" + getId() +
            ", className='" + getClassName() + "'" +
            ", department='" + getDepartment() + "'" +
            ", year=" + getYear() +
            ", teacherId=" + getTeacherId() +
            "}";
    }
}
