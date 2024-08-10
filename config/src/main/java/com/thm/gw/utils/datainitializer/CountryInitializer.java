package com.thm.gw.utils.datainitializer;

import com.thm.gw.entities.Country;
import com.thm.gw.repositories.ICountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(6)
public class CountryInitializer implements CommandLineRunner {

    private final ICountryRepository countryRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeCountries();
    }

    private void initializeCountries() {
        if (countryRepository.count() == 0) {
            List<Country> countries = List.of(
                    new Country("Albania"),
                    new Country("Andorra"),
                    new Country("Armenia"),
                    new Country("Austria"),
                    new Country("Azerbaijan"),
                    new Country("Belarus"),
                    new Country("Belgium"),
                    new Country("Bosnia and Herzegovina"),
                    new Country("Bulgaria"),
                    new Country("Croatia"),
                    new Country("Cyprus"),
                    new Country("Czech Republic"),
                    new Country("Denmark"),
                    new Country("Estonia"),
                    new Country("Finland"),
                    new Country("France"),
                    new Country("Georgia"),
                    new Country("Germany"),
                    new Country("Greece"),
                    new Country("Hungary"),
                    new Country("Iceland"),
                    new Country("Ireland"),
                    new Country("Italy"),
                    new Country("Kazakhstan"),
                    new Country("Kosovo"),
                    new Country("Latvia"),
                    new Country("Liechtenstein"),
                    new Country("Lithuania"),
                    new Country("Luxembourg"),
                    new Country("Malta"),
                    new Country("Moldova"),
                    new Country("Monaco"),
                    new Country("Montenegro"),
                    new Country("Netherlands"),
                    new Country("North Macedonia"),
                    new Country("Norway"),
                    new Country("Poland"),
                    new Country("Portugal"),
                    new Country("Romania"),
                    new Country("Russia"),
                    new Country("San Marino"),
                    new Country("Serbia"),
                    new Country("Slovakia"),
                    new Country("Slovenia"),
                    new Country("Spain"),
                    new Country("Sweden"),
                    new Country("Switzerland"),
                    new Country("Turkey"),
                    new Country("Ukraine"),
                    new Country("United Kingdom"),
                    new Country("Vatican City")
            );
            countryRepository.saveAll(countries);
        }
    }
}
