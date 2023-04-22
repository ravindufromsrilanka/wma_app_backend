package com.wma.wma.Repositories;

import com.wma.wma.Entity.GeneralUserProfile;
import com.wma.wma.Entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OTP , Long> {
    OTP getAllByGeneralUserProfile(GeneralUserProfile gup);
}
