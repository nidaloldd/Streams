package brickset;


import repository.Repository;

import java.util.Comparator;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {
    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    public static void main(String[] args) {
        //printLegoSetsWithTag();
        //printOldestLegoSet();
        //printSmallLegoSets();
        //printNumberOfLegoCitySet();
        printLegoSetsWith_K();
        

    }
    public static void printLegoSetsWithTag() {
        var brickset = new LegoSetRepository().getAll();
        brickset.stream().
                filter(x -> x.getTags() != null).
                forEach(System.out::println);
    }
    public static void printOldestLegoSet() {
        final var brickset = new LegoSetRepository().getAll();
        brickset.stream().
                min(Comparator.comparing(LegoSet::getYear));
        System.out.println(brickset);
    }

    public static void printSmallLegoSets() {
        final var brickset = new LegoSetRepository().getAll();

        brickset.stream().
                filter(p -> p.getPieces()<10).
                map(LegoSet::getPieces).
                forEach(System.out::println);
    }
    public static void printNumberOfLegoCitySet() {
        final var brickset = new LegoSetRepository().getAll();

        brickset.stream().
                filter(p -> p.getTheme()=="City").
                map(LegoSet::getName);
        System.out.println(brickset.stream().count());
    }
    public static void printLegoSetsWith_K() {
        final var brickset = new LegoSetRepository().getAll();

        brickset.stream().
                filter(x -> x.getName().charAt(0)=='K').
                forEach(System.out::println);

    }

}


