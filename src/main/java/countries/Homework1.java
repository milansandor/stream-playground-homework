package countries;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.*;

public class Homework1 {

    private List<Country> countries;

    public Homework1() {
        countries = new CountryRepository().getAll();
    }

    /**
     * Returns whether there is at least one country with the word "island" in its name ignoring case.
     */
    public boolean streamPipeline1() {
        // TODO
        return countries.stream()
                .anyMatch(country -> country.getName().toLowerCase().contains("island"));
    }

    /**
     *  Returns the first country name that contains the word "island" ignoring case.
     */
    public Optional<String> streamPipeline2() {
        // TODO
        return countries.stream()
                .filter(country -> country.getName().toLowerCase().contains("island"))
                .map(Country::getName)
                .findFirst();
    }

    /**
     * Prints each country name in which the first and the last letters are the same ignoring case.
     */
    public void streamPipeline3() {
        // TODO
        countries.stream()
                .filter(country -> country.getName().toLowerCase().charAt(0) == country.getName().charAt(country.getName().length()-1))
                .map(Country::getName)
                .forEach(System.out::println);
    }

    /**
     * Prints the populations of the first ten least populous countries.
     */
    public void streamPipeline4() {
        // TODO
        countries.stream()
                .sorted(Comparator.comparingLong(Country::getPopulation))
                .mapToLong(country -> country.getPopulation())
                .distinct()
                .limit(9)
                .forEach((country) -> {
                    countries.stream()
                            .filter(c -> c.getPopulation() == country)
                            .map(c -> c.getName())
                            .forEach(System.out::println);
                });
    }

    /**
     * Prints the names of the first ten least populous countries.
     */
    public void streamPipeline5() {
        // TODO
        countries.stream()
                .sorted(Comparator.comparingLong(Country::getPopulation))
                .limit(10)
                .map(Country::getName)
                .forEach(System.out::println);
    }

    /**
     * Returns summary statistics about the number of country name translations associated with each country.
     */
    public IntSummaryStatistics streamPipeline6() {
        // TODO
        return countries.stream()
                .mapToInt(country -> country.getTranslations().size())
                .summaryStatistics();
    }

    /**
     * Prints the names of countries in the ascending order of the number of timezones.
     */
    public void streamPipeline7() {
        // TODO
        countries.stream()
                .sorted(Comparator.comparingInt(country -> country.getTimezones().size()))
                .map(country -> country.getName())
                .forEach(System.out::println);
    }

    /**
     * Prints the number of timezones for each country in the form {@code name:timezones}, in the ascending order of the number of timezones.
     */
    public void streamPipeline8() {
        // TODO
        countries.stream()
                .sorted(Comparator.comparingInt(c -> c.getTimezones().size()))
                .collect(LinkedHashMap::new, (map, value) -> map.put(value.getName(), value.getTimezones().size()), HashMap::putAll)
                .forEach((country, value) -> {
                    System.out.println(country + ":" + value);});
    }

    /**
     * Returns the number of countries with no Spanish country name translation (the Spanish language is identifi
ed by the language code "es").
     */
    public long streamPipeline9() {
        // TODO
        return countries.stream()
                .flatMap(country -> country.getTranslations().keySet().stream().filter(c -> c.contains("es")))
                .count();
    }

    /**
     * Prints the names of countries with null area.
     */
    public void streamPipeline10() {
        // TODO
        countries.stream()
                .filter(country -> country.getArea() == null)
                .map(Country::getName)
                .forEach(System.out::println);
    }

    /**
     * Prints all distinct language tags of country name translations sorted in alphabetical order.
     */
    public void streamPipeline11() {
        // TODO
        countries.stream()
                .flatMap(country -> country.getTranslations().keySet().stream())
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * Returns the average length of country names.
     */
    public double streamPipeline12() {
        // TODO
        return countries.stream()
                .mapToInt(country -> country.getName().length())
                .average()
                .getAsDouble();
    }

    /**
     * Prints all distinct regions of the countries with null area.
     */
    public void streamPipeline13() {
        // TODO
        countries.stream()
                .filter(country -> country.getArea() == null)
                .map(Country::getRegion)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * Returns the largest country with non-null area.
     */
    public Optional<Country> streamPipeline14() {
        // TODO
        return countries.stream()
                .filter(country -> country.getArea() != null)
                .max(Comparator.comparing(Country::getArea));
    }

    /**
     * Prints the names of countries with a non-null area below 1.
     */
    public void streamPipeline15() {
        // TODO
        countries.stream()
                .filter(c -> c.getArea() != null)
                .filter(c -> c.getArea().doubleValue() < 1)
                .map(country -> country.getName())
                .forEach(System.out::println);
    }

    /**
     * Prints all distinct timezones of European and Asian countries.
     */
    public void streamPipeline16() {
        // TODO
        countries.stream()
                .filter(c -> c.getRegion() == Region.EUROPE || c.getRegion() == Region.ASIA)
                .flatMap(country -> country.getTimezones().stream())
                .distinct()
                .forEach(System.out::println);
    }

}
