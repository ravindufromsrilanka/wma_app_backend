package com.wma.wma.Repositories;

import com.wma.wma.Entity.GeneralUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralUserProfileRepository extends JpaRepository<GeneralUserProfile , Long> {
    GeneralUserProfile getAllByTelephone(String telephone);
}
