package com.wma.wma.Repositories;

import com.wma.wma.Entity.GeneralUserProfile;
import com.wma.wma.Entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin , Long> {
    UserLogin getByGeneralUserProfile(GeneralUserProfile gup);

    UserLogin getAllByTelephone(String telephone);
}
