package com.topas.air.repository.oracle;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Dir {
    @Id
    private Long id;

    @Column
    private String name;

//    @OneToOne(mappedBy="id", cascade= CascadeType.ALL)
//    private Dir parent;

    //This field is a table column
    // It identifies the parent of the current row
    // It it will be written as the type of dirId
    // By default this relationship will be eagerly fetched
    // , which you may or may not want
    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Dir parent;

    //This field is not a table column
    // It is a collection of those Dir rows that have this row as a parent.
    // This is the other side of the relationship defined by the parent field.
    @OneToMany(mappedBy="parent")
    private Set<Dir> children;
}