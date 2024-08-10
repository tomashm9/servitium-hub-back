package com.thm.gw.utils.datainitializer;

import com.thm.gw.entities.Company;
import com.thm.gw.entities.Owner;
import com.thm.gw.entities.Role;
import com.thm.gw.enums.Gender;
import com.thm.gw.repositories.ICompanyRepository;
import com.thm.gw.repositories.IOwnerRepository;
import com.thm.gw.repositories.IRoleRepository;
import com.thm.gw.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(3)
public class OwnerInit implements CommandLineRunner {

    private final IOwnerRepository ownerRepository;
    private final ICompanyRepository companyRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        long ownerCount = ownerRepository.count();

        if (ownerCount == 0) {
            List<Company> companies = companyRepository.findAll();
            List<Role> userRoles = roleRepository.findAll();

            if (companies.size() < 2) {
                throw new IllegalStateException("Not enough companies found to create owners.");
            }

            Role ownerRole = userRoles.stream()
                    .filter(role -> role.getName().equals(Constants.OWNER_ROLE))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Owner role not found."));

            Owner owner1 = new Owner();
            owner1.setEmail("owner1@example.com");
            owner1.setPassword(passwordEncoder.encode("securepassword123"));
            owner1.setFirstName("Alice");
            owner1.setLastName("Johnson");
            owner1.setPhoneNumber("+1234567890");
            owner1.setContactEmail("contact1@example.com");
            owner1.setGender(Gender.F);
            owner1.setBirthDate(LocalDate.of(1985, 2, 14));
            owner1.setCreatedAt(LocalDateTime.now());
            owner1.setUpdatedAt(LocalDateTime.now());
            owner1.setCompany(companies.get(1));
            owner1.setRoles(Set.of(ownerRole));

            Owner owner2 = new Owner();
            owner2.setEmail("owner2@example.com");
            owner2.setPassword(passwordEncoder.encode("securepassword456"));
            owner2.setFirstName("Bob");
            owner2.setLastName("Brown");
            owner2.setPhoneNumber("+0987654321");
            owner2.setContactEmail("contact2@example.com");
            owner2.setGender(Gender.M);
            owner2.setBirthDate(LocalDate.of(1990, 7, 22));
            owner2.setCreatedAt(LocalDateTime.now());
            owner2.setUpdatedAt(LocalDateTime.now());
            owner2.setCompany(companies.get(0));
            owner2.setRoles(Set.of(ownerRole));

            ownerRepository.saveAll(List.of(owner1, owner2));
        }
    }
}
