package com.thm.gw.utils.datainitializer;

import com.thm.gw.entities.Client;
import com.thm.gw.entities.Role;
import com.thm.gw.enums.Gender;
import com.thm.gw.repositories.IClientRepository;
import com.thm.gw.repositories.IRoleRepository;
import com.thm.gw.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(1)
public class ClientInit implements CommandLineRunner {

    private final IClientRepository clientRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Role> userRoles = roleRepository.findAll();

        Role adminRole = userRoles.stream()
                .filter(role -> role.getName().equals(Constants.ADMIN_ROLE))
                .findFirst()
                .orElseThrow();

        Role clientRole = userRoles.stream()
                .filter(role -> role.getName().equals(Constants.CLIENT_ROLE))
                .findFirst()
                .orElseThrow();

        Client client1 = new Client();

        client1.setEmail("client1@example.com");
        client1.setPassword(passwordEncoder.encode("password123"));
        client1.setFirstName("John");
        client1.setLastName("Doe");
        client1.setPhoneNumber("+1234567890");
        client1.setContactEmail("contact1@example.com");
        client1.setGender(Gender.M);
        client1.setBirthDate(LocalDate.of(1995, 4, 3));
        client1.setRoles(Set.of(adminRole));

        Client client2 = new Client();
        client2.setEmail("client2@example.com");
        client2.setPassword(passwordEncoder.encode("password456"));
        client2.setFirstName("Jane");
        client2.setLastName("Smith");
        client2.setPhoneNumber("+0987654321");
        client2.setContactEmail("contact2@example.com");
        client2.setGender(Gender.F);
        client2.setBirthDate(LocalDate.of(1990, 5, 10));
        client2.setRoles(Set.of(clientRole));

        List<Client> clients = List.of(client1, client2);

        clientRepository.saveAll(clients);
    }
}
