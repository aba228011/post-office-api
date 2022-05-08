package com.dar.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "service_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypeEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, length = 10)
    private String code;

    @Column(length = 100)
    private String name_ru;

    @Column(nullable = false, length = 100)
    private String name_en;
}