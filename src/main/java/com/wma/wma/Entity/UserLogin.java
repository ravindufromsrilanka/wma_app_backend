package com.wma.wma.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_login")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String telephone;

    private String password;

    private boolean verification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_type")
    private MemberType type;

    @OneToOne
    @JoinColumn(name = "general_user_profile")
    private GeneralUserProfile generalUserProfile;
}
