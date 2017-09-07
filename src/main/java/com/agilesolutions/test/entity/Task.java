package com.agilesolutions.test.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
public class Task extends AbstractBaseEntity {
    @ManyToOne
    private ToDo owner;

    @JsonProperty("todo_id")
    public Long getOwner() {
        return this.owner.getId();
    }
}
