package com.obss.jss.petclinic;

import com.obss.jss.petclinic.model.Admin;
import com.obss.jss.petclinic.model.Role;
import com.obss.jss.petclinic.repository.AdminRepository;
import com.obss.jss.petclinic.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PetClinicApplicationTests {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    void contextLoads() {
//        User user = User.builder().name("hasan")
//                .surname("albayrak").address("manisa").city("ege").build();
//        final Admin savedUser = adminRepository.save(user);
//
//        adminRepository.findById(savedUser.getId());
//
//        adminRepository.findAdminsByNameOrderBySurnameDesc("hasan");
//
//        adminRepository.findAdminByCustomQuery("ege");
        final Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());

        final Role userRole = roleRepository.save(Role.builder().name("USER").build());

        final Role vetRole = roleRepository.save(Role.builder().name("VET").build());

        {
            final Admin admin = Admin.builder().name("mustafa").surname("güney").address("yenişehir").city("istanbul").roles(List.of(adminRole)).build();
            final Admin adminSaved = adminRepository.save(admin);
        }

        {
            final Admin adminEntity = Admin
                    .builder()
                    .name("şerafettin")
                    .surname("kılkuyruk")
                    .address("mars")
                    .city("derin çukur")
                    .roles(List.of(userRole))
                    .build();
            final Admin adminEntitySaved = adminRepository.save(adminEntity);
        }
///////////////////////////////**********************
//        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
//        Role userRole = roleRepository.save(Role.builder().name("USER").build());
//        Role vetRole = roleRepository.save(Role.builder().name("VET").build());
//
//        {
//            Admin admin = Admin.builder()
//                    .name("hasan")
//                    .surname("albayrak")
//                    .address("saruhanlı")
//                    .city("manisa")
//                    .roles(List.of(adminRole)).build();
//            Admin adminSaved = adminRepository.save(admin);
//        }
//        {
//            Admin adminEntity = Admin
//                    .builder()
//                    .name("dummyName")
//                    .surname("dummySurname")
//                    .address("dummyAddress")
//                    .city("dummyCity")
//                    .roles(List.of(userRole))
//                    .build();
//            Admin adminEntitySaved = adminRepository.save(adminEntity);
//        }

        //adminRepository.findAdminsByNameOrderBySurnameDesc("hasan");
        //adminRepository.findAdminByCustomQuery("manisa");

    }

}
