package com.topas.air.repository.maria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    private Long id;

    @Column
    private String name;

}