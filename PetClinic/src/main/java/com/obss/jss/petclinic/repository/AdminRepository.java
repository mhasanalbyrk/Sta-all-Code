package com.obss.jss.petclinic.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.obss.jss.petclinic.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findAdminsByNameOrderBySurnameDesc(String name, Pageable pageable);

    List<Admin> findAllByNameLike(String name);

    List<Admin> findAllByNameNot(String name);

    @Query(value = "from Admin u where u.city = :city")
    List<Admin> findUserByCity(String city, Pageable pageable);

    @Query(value = "from Admin u join u.roles r where r.name = :roleName")
    List<Admin> findUserByCityNested(String roleName);

//    @Query(value = "select u.name as name, u.surname as surname from Admin u where u.city = :city")
//    List<FullName> findUserByCityProjection(String city, Pageable pageable);

    @Query(value = "SELECT * FROM ADMIN WHERE CITY = :city", nativeQuery = true)
    List<Admin> findUserByCityNative(String city);
}
