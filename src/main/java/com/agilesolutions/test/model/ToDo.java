package com.agilesolutions.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToDo extends AbstractBaseEntity {
    @OneToMany
    private List<Task> tasks = new ArrayList<>();
}
