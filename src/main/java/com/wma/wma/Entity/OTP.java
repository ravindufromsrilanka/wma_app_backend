package com.wma.wma.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "otp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OTP {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String otp;

    private boolean state;

    @ManyToOne
    @JoinColumn(name = "general_user_profile")
    private GeneralUserProfile generalUserProfile;

}
