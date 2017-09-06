package com.agilesolutions.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task extends AbstractBaseEntity {
    @ManyToOne
    private ToDo owner;
}
