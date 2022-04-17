package countries;

import brickset.LegoSetRepository;
import repository.Repository;

/**
 * Represents a repository of {@code Country} objects.
 */
public class CountryRepository extends Repository<Country> {

    public CountryRepository() {
        super(Country.class, "countries.json");
    }


    public static void main(String[] args) {

        var countries = new CountryRepository().getAll();

        countries.stream().map(Country::getName).forEach(System.out::println);

    }


}

