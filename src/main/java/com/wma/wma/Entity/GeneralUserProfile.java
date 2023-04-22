package com.wma.wma.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "GeneralUserProfile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeneralUserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String telephone;

}
