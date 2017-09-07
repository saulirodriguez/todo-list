package com.agilesolutions.test.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity
public class ToDo extends AbstractBaseEntity {
    public ToDo(Long id, String name, String description) {
        super(id, name, description);
    }
}
