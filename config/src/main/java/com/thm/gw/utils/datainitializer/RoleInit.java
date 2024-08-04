package com.thm.gw.utils.datainitializer;

import com.thm.gw.entities.Role;
import com.thm.gw.repositories.IRoleRepository;
import com.thm.gw.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(0)
public class RoleInit implements CommandLineRunner {

    private final IRoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = List.of(
                new Role(Constants.ADMIN_ROLE, "Administrator"),
                new Role(Constants.OWNER_ROLE, "Owner"),
                new Role(Constants.MANAGER_ROLE, "Manager"),
                new Role(Constants.CLIENT_ROLE, "Client")
        );

        roleRepository.saveAll(roles);
    }
}
