package com.noon.guestparking.repostotries;

import com.noon.guestparking.models.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepo extends JpaRepository<Resident, Integer> {
}
