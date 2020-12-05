package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        Matcher m1 = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );
        for (Player player : stats.matches(m1)) {
            System.out.println(player);
        }

        System.out.println("---");

        Matcher m2 = new And( new Not( new HasAtLeast(1, "goals") ), 
                              new PlaysIn("NYR")
        );
        for (Player player : stats.matches(m2)) {
            System.out.println(player);
        }

        System.out.println("---");

        Matcher m3 = new And( new HasFewerThan(1, "goals"), 
                              new PlaysIn("NYR")
        );
        for (Player player : stats.matches(m3)) {
            System.out.println(player);
        }

        System.out.println("---");

        Matcher m4 = new Or( new HasAtLeast(40, "goals"),
                    new HasAtLeast(60, "assists")
        ); 
        for (Player player : stats.matches(m4)) {
            System.out.println(player);
        }

        System.out.println("---");

        Matcher m5 = new And( new HasAtLeast(50, "points"),
                              new Or( new PlaysIn("NYR"),
                                      new PlaysIn("NYI"),
                                      new PlaysIn("BOS")
                                    )
        ); 
        for (Player player : stats.matches(m5)) {
            System.out.println(player);
        }




    }
}
