package brickset;


import repository.Repository;

import java.time.Year;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {
    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    public static void main(String[] args) {

        // System.out.println(isAnyLegoWithZeroPieces());
        //printAllTags();
        //System.out.println(SumOfLegoPieces());
        //System.out.println(getNumberOfPackagingTypeForEachSet());
        System.out.println(getMapOfYearWithTheirLegoSets());

    }
    /**
     * @returns whether there is at least one Lego set with 0 pieces
     * */
    public static boolean isAnyLegoWithZeroPieces() {
        var brickset = new LegoSetRepository().getAll();

        return brickset.stream().anyMatch(LegoSet -> LegoSet.getPieces()==0 );
    }
    /**
     * Print all tags one time in ABC order
     * */
    public static void printAllTags() {
        var brickset = new LegoSetRepository().getAll();

        brickset.stream().filter(LegoSet-> LegoSet.getTags()!=null)
                .flatMap(LegoSet -> LegoSet.getTags().stream())
                .sorted()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     *  The Sum of Lego Pieces with reduce
     *
     * @return the sum of all Lego Pieces
     */
    public static long SumOfLegoPieces() {
        var brickset = new LegoSetRepository().getAll();

        return brickset.stream().map(LegoSet::getPieces)
                .reduce(0, (partialAgeResult, p) -> partialAgeResult + p , Integer::sum);
    }

    /**
     * Returns a Map object, that contains the packaging type and the number of lego set with the same packaging type
     *
     * @return {@code Map<String, Long>} object wrapping how many lego sets have the same packaging type
     */
    public static Map<PackagingType, Long> getNumberOfPackagingTypeForEachSet() {
        var brickset = new LegoSetRepository().getAll();

        return brickset.stream()
                .collect(Collectors.groupingBy(LegoSet::getPackagingType, Collectors.counting()));

    }

    /**
     * Returns a Map object, that contains the year and the lego sets of the year
     *
     * @return {@code Map<Year, Set<String>>} object wrapping the Years and their lego sets name.
     */
    public static Map<Year, Set<String>> getMapOfYearWithTheirLegoSets() {

        var brickset = new LegoSetRepository().getAll();

        return brickset.stream()
                .collect(Collectors.groupingBy(LegoSet::getYear,
                        Collectors.mapping(LegoSet::getName,
                                Collectors.filtering(Objects::nonNull,
                                        Collectors.toSet()))));
    }



}


