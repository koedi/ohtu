package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          

        /*
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
        */

        /*
        QueryBuilder query = new QueryBuilder();
        Matcher m6 = query.build();
    
        for (Player player : stats.matches(m6)) {
            System.out.println( player );
        }

        System.out.println("---");

        QueryBuilder query2 = new QueryBuilder();
        Matcher m2 = query2.playsIn("NYR").build();
     
        for (Player player : stats.matches(m2)) {
            System.out.println( player );
        }

        System.out.println("---");

        
        QueryBuilder query3 = new QueryBuilder(); 
        Matcher m8 = query3.playsIn("NYR")
                         .hasAtLeast(5, "goals")
                         .hasFewerThan(10, "goals").build();
     
        for (Player player : stats.matches(m8)) {
            System.out.println( player );
        }
        */

        QueryBuilder query = new QueryBuilder();
        Matcher m1 = query.playsIn("PHI")
                    .hasAtLeast(10, "assists")
                    .hasFewerThan(5, "goals").build();

        Matcher m2 = query.playsIn("EDM")
                    .hasAtLeast(40, "points").build();

        Matcher m = query.oneOf(m1, m2).build();

        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }






    }
}
