package com.thm.gw.utils.datainitializer;

import com.thm.gw.entities.Company;
import com.thm.gw.entities.Service;
import com.thm.gw.entities.ServiceSubtype;
import com.thm.gw.entities.ServiceType;
import com.thm.gw.repositories.ICompanyRepository;
import com.thm.gw.repositories.IServiceRepository;
import com.thm.gw.repositories.IServiceSubtypeRepository;
import com.thm.gw.repositories.IServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(4)
public class ServiceInit implements CommandLineRunner {

    private final IServiceRepository serviceRepository;
    private final ICompanyRepository companyRepository;
    private final IServiceTypeRepository serviceTypeRepository;
    private final IServiceSubtypeRepository serviceSubtypeRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeServiceTypesAndSubtypes();
        initializeCompanies();
        initializeServices();
    }

    private void initializeServiceTypesAndSubtypes() {
        if (serviceTypeRepository.count() == 0) {
            ServiceType accommodation = new ServiceType("Accommodation");
            ServiceType restaurantsAndCafes = new ServiceType("Restaurants and Coffees");
            ServiceType beautyAndWellness = new ServiceType("Beauty and Wellness");
            ServiceType eventsAndEntertainment = new ServiceType("Events and Entertainment");
            ServiceType transport = new ServiceType("Transport");
            ServiceType outdoorActivitiesAndSports = new ServiceType("Outdoor Activities and Sports");
            ServiceType domesticServices = new ServiceType("Domestic Services");

            serviceTypeRepository.saveAll(List.of(
                    accommodation,
                    restaurantsAndCafes,
                    beautyAndWellness,
                    eventsAndEntertainment,
                    transport,
                    outdoorActivitiesAndSports,
                    domesticServices
            ));

            serviceSubtypeRepository.saveAll(List.of(
                    new ServiceSubtype("Apartment rentals", accommodation),
                    new ServiceSubtype("Hostels and guesthouses", accommodation),
                    new ServiceSubtype("Restaurant table reservations", restaurantsAndCafes),
                    new ServiceSubtype("Catering services", restaurantsAndCafes),
                    new ServiceSubtype("Hair salons", beautyAndWellness),
                    new ServiceSubtype("Barbers", beautyAndWellness),
                    new ServiceSubtype("Spas and massages", beautyAndWellness),
                    new ServiceSubtype("Manicure and pedicure services", beautyAndWellness),
                    new ServiceSubtype("Personal trainers", beautyAndWellness),
                    new ServiceSubtype("Event venue hire", eventsAndEntertainment),
                    new ServiceSubtype("Photography and videography services", eventsAndEntertainment),
                    new ServiceSubtype("DJ services", eventsAndEntertainment),
                    new ServiceSubtype("Event and party planning", eventsAndEntertainment),
                    new ServiceSubtype("Car rentals", transport),
                    new ServiceSubtype("Private transport services", transport),
                    new ServiceSubtype("Tour guides", outdoorActivitiesAndSports),
                    new ServiceSubtype("Adventure activities", outdoorActivitiesAndSports),
                    new ServiceSubtype("Football pitches", outdoorActivitiesAndSports),
                    new ServiceSubtype("Futsal courts", outdoorActivitiesAndSports),
                    new ServiceSubtype("Padel courts", outdoorActivitiesAndSports),
                    new ServiceSubtype("Home cleaning", domesticServices),
                    new ServiceSubtype("Gardening", domesticServices)
            ));
        }
    }

    private void initializeCompanies() {
        if (companyRepository.count() == 0) {
            List<Company> companies = List.of(
                    new Company("Barber Shop"),
                    new Company("Restaurant")
            );
            companyRepository.saveAll(companies);
        }
    }

    private void initializeServices() {
        if (serviceRepository.count() == 0) {
            List<Company> companies = companyRepository.findAll();
            List<ServiceType> serviceTypes = serviceTypeRepository.findAll();
            List<ServiceSubtype> serviceSubtypes = serviceSubtypeRepository.findAll();

            if (!companies.isEmpty() && !serviceTypes.isEmpty() && !serviceSubtypes.isEmpty()) {
                Company barberShop = companies.get(0);
                Company restaurant = companies.get(1);

                ServiceType beautyAndWellness = serviceTypes.stream()
                        .filter(t -> t.getName().equals("Beauty and Wellness"))
                        .findFirst().orElse(null);
                ServiceType dining = serviceTypes.stream()
                        .filter(t -> t.getName().equals("Restaurants and Cafes"))
                        .findFirst().orElse(null);

                ServiceSubtype haircut = serviceSubtypes.stream()
                        .filter(st -> st.getName().equals("Hair salons"))
                        .findFirst().orElse(null);
                ServiceSubtype shaving = serviceSubtypes.stream()
                        .filter(st -> st.getName().equals("Barbers"))
                        .findFirst().orElse(null);
                ServiceSubtype meal = serviceSubtypes.stream()
                        .filter(st -> st.getName().equals("Restaurant table reservations"))
                        .findFirst().orElse(null);

                if (beautyAndWellness != null && haircut != null && shaving != null) {
                    Service haircutService = new Service(
                            "Haircut",
                            "Standard haircut",
                            new BigDecimal("15.00"),
                            30,
                            beautyAndWellness,
                            haircut,
                            barberShop
                    );

                    Service shavingService = new Service(
                            "Shaving",
                            "Classic shaving",
                            new BigDecimal("10.00"),
                            10,
                            beautyAndWellness,
                            shaving,
                            barberShop
                    );

                    serviceRepository.saveAll(List.of(haircutService, shavingService));
                }

                if (dining != null && meal != null) {
                    Service tableReservation = new Service(
                            "Table Reservation",
                            "Reserve a table for dining",
                            new BigDecimal("0.00"),
                            0,
                            dining,
                            meal,
                            restaurant
                    );

                    serviceRepository.save(tableReservation);
                }
            }
        }
    }
}
