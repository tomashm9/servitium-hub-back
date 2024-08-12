package com.thm.gw.utils.datainitializer;

import com.thm.gw.entities.*;
import com.thm.gw.repositories.ICompanyRepository;
import com.thm.gw.repositories.ICompanyLocationRepository;
import com.thm.gw.repositories.ICityRepository;
import com.thm.gw.repositories.ICountryRepository;
import com.thm.gw.repositories.IPostalCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Order(2)
public class DataInit implements CommandLineRunner {

    private final ICompanyRepository companyRepository;
    private final ICompanyLocationRepository companyLocationRepository;
    private final ICountryRepository countryRepository;
    private final ICityRepository cityRepository;
    private final IPostalCodeRepository postalCodeRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeCompanyData();
        initializeCompanyLocationData();
    }

    private void initializeCompanyData() {
        if (companyRepository.count() == 0) {
            List<Company> companies = List.of(
                    new Company(
                            "Neighborhood Barber Shop",
                            "The best place for a haircut and quality service.",
                            "https://www.neighborhoodbarbershop.com",
                            LocalDate.of(2010, 3, 15),
                            "Carlos Silva",
                            "+1234567890",
                            true
                    ),
                    new Company(
                            "World Flavors Restaurant",
                            "Offering a variety of international and local dishes.",
                            "https://www.worldflavorsrestaurant.com",
                            LocalDate.of(2015, 7, 22),
                            "Ana Costa",
                            "+0987654321",
                            true
                    )
            );

            companyRepository.saveAll(companies);
        }
    }

    private void initializeCompanyLocationData() {
        if (companyRepository.count() > 0 && companyLocationRepository.count() == 0) {
            List<Company> companies = companyRepository.findAll();

            if (!companies.isEmpty()) {
                Optional<Country> belgiumOpt = countryRepository.findByName("Belgium");
                Country belgium = belgiumOpt.orElseGet(() -> {
                    Country newBelgium = new Country("Belgium");
                    return countryRepository.save(newBelgium);
                });

                City brussels = cityRepository.findByNameAndCountry("Brussels", belgium);
                if (brussels == null) {
                    brussels = new City("Brussels", belgium);
                    cityRepository.save(brussels);
                }

                City antwerp = cityRepository.findByNameAndCountry("Antwerp", belgium);
                if (antwerp == null) {
                    antwerp = new City("Antwerp", belgium);
                    cityRepository.save(antwerp);
                }

                PostalCode postalCodeBrussels = postalCodeRepository.findByCode1AndCity("1000", brussels);
                if (postalCodeBrussels == null) {
                    postalCodeBrussels = new PostalCode("1000", brussels);
                    postalCodeRepository.save(postalCodeBrussels);
                }

                PostalCode postalCodeAntwerp = postalCodeRepository.findByCode1AndCity("2000", antwerp);
                if (postalCodeAntwerp == null) {
                    postalCodeAntwerp = new PostalCode("2000", antwerp);
                    postalCodeRepository.save(postalCodeAntwerp);
                }

                Company barberShop = companies.stream()
                        .filter(c -> c.getName().equals("Neighborhood Barber Shop"))
                        .findFirst().orElse(null);
                Company restaurant = companies.stream()
                        .filter(c -> c.getName().equals("World Flavors Restaurant"))
                        .findFirst().orElse(null);

                if (barberShop != null) {
                    CompanyLocation barberShopLocation = new CompanyLocation(
                            "123 Main St",
                            "Suite 4B",
                            barberShop,
                            belgium,
                            brussels,
                            postalCodeBrussels
                    );

                    List<OpeningHours> barberShopHours = getOpeningHours(barberShopLocation);

                    barberShopLocation.setOpeningHours(barberShopHours);

                    companyLocationRepository.save(barberShopLocation);
                }

                if (restaurant != null) {
                    CompanyLocation restaurantLocation = new CompanyLocation(
                            "456 Elm St",
                            null,
                            restaurant,
                            belgium,
                            antwerp,
                            postalCodeAntwerp
                    );

                    List<OpeningHours> restaurantHours = getHours(restaurantLocation);

                    restaurantLocation.setOpeningHours(restaurantHours);

                    companyLocationRepository.save(restaurantLocation);
                }
            }
        }
    }

    private static List<OpeningHours> getHours(CompanyLocation restaurantLocation) {
        List<OpeningHours> restaurantHours = List.of(
                new OpeningHours("Monday", "11:00", "23:00"),
                new OpeningHours("Tuesday", "11:00", "23:00"),
                new OpeningHours("Wednesday", "11:00", "23:00"),
                new OpeningHours("Thursday", "11:00", "23:00"),
                new OpeningHours("Friday", "11:00", "23:00"),
                new OpeningHours("Saturday", "11:00", "23:00"),
                new OpeningHours("Sunday", "11:00", "22:00")
        );

        restaurantHours.forEach(hour -> hour.setCompanyLocation(restaurantLocation));
        return restaurantHours;
    }

    private static List<OpeningHours> getOpeningHours(CompanyLocation barberShopLocation) {
        List<OpeningHours> barberShopHours = List.of(
                new OpeningHours("Monday", "09:00", "17:00"),
                new OpeningHours("Tuesday", "09:00", "17:00"),
                new OpeningHours("Wednesday", "09:00", "17:00"),
                new OpeningHours("Thursday", "09:00", "17:00"),
                new OpeningHours("Friday", "09:00", "17:00"),
                new OpeningHours("Saturday", "09:00", "15:00"),
                new OpeningHours("Sunday", "Closed", "Closed")
        );

        barberShopHours.forEach(hour -> hour.setCompanyLocation(barberShopLocation));
        return barberShopHours;
    }
}
