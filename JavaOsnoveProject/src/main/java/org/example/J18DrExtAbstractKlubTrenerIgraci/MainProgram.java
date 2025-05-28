package J18DrExtAbstractKlubTrenerIgraci;

import static java.lang.System.out;

class MainProgram {
    public static void main(String[] args) {
        League league = new League();

        Coach coach1 = new Coach("Fabio", "Cannavaro", 10);
        Coach coach2 = new Coach("Gennaro", "Gattuso", 8);
        Coach coach3 = new Coach("Carlo", "Ancelotti", 30);
        Coach coach4 = new Coach("Josep", "Guardiola", 20);

        Club club1 = new Club("Dinamo", "Zagreb", coach1);
        Club club2 = new Club("Hajduk", "Split", coach2);
        Club club3 = new Club("Real Madrid", "Madrid", coach3);
        Club club4 = new Club("Barcelona", "Barcelona", coach4);

        league.addClub(club1);
        league.addClub(club2);
        league.addClub(club3);
        league.addClub(club4);

        Player player1 = new Player("Luka", "Modric", 10, 42);
        Player player2 = new Player("Ivan", "Perisic", 4, 43);
        Player player3 = new Player("Cristiano", "Ronaldo", 7, 42);
        Player player4 = new Player("Leo", "Messi", 10, 41);
        Player player5 = new Player("Cristiano", "Ronaldo", 7, 42);
        Player player6 = new Player("Leo", "Messi", 10, 41);
        Player player7 = new Player("Cristiano", "Ronaldo", 7, 42);
        Player player8 = new Player("Leo", "Messi", 10, 41);
        Player player9 = new Player("Leo", "Messi", 10, 41);

        Match match1 = new Match(club1, club2);
        Match match2 = new Match(club3, club4);

        match1.addScorer(player1, true);
        match1.addScorer(player2, false);
        match2.addScorer(player3, true);
        match2.addScorer(player4, false);
        match2.addScorer(player5, true);
        match2.addScorer(player6, false);
        match2.addScorer(player7, true);
        match2.addScorer(player8, false);
        match2.addScorer(player9, false);

        match1.setResult();
        match2.setResult();

        league.addMatch(match1);
        league.addMatch(match2);

        out.println("\n-------------------Print clubs: --------------------------");

        league.printActiveClubs();

        out.println("\n---------------Print matches: ------------------------");

        league.printMatches();

        out.println("\n-----------------Print club by ID: ----------------------");

        league.printClubById(1); // Prints club with ID 2

        out.println("\n-----------------Print match by ID: ----------------------");

        league.printMatchById(1); // Example: prints the match with ID 1

    }
}
