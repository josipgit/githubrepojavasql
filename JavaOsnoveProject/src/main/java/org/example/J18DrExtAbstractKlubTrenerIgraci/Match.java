package J18DrExtAbstractKlubTrenerIgraci;

import java.util.ArrayList;
import java.util.List;

class Match {
    private static int idCounter = 1;
    public int id;
    private Club homeClub;
    private Club awayClub;
    private int homeResult;
    private int awayResult;
    private List<Player> homeScorers;
    private List<Player> awayScorers;

    public Match(Club homeClub, Club awayClub) {
        this.id = idCounter++;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.homeScorers = new ArrayList<>();
        this.awayScorers = new ArrayList<>();
    }

    public void addScorer(Player player, boolean isHomeTeam) {
        if (isHomeTeam) {
            homeScorers.add(player);
        } else {
            awayScorers.add(player);
        }
    }

    public void setResult() {
        if (homeScorers.isEmpty() && awayScorers.isEmpty()) {
            throw new IllegalStateException("Cannot set result without scorers");
        }
        homeResult = homeScorers.size();
        awayResult = awayScorers.size();
    }

    @Override
    public String toString() {
        return "Match " + id + ": " + homeClub.getName() + " " + homeResult + " - " + awayResult + " " + awayClub.getName()
                + "\n" + "Strijelac za domacine: " + homeScorers
                + "\n" + "Strijelci za goste: " + awayScorers;
    }
}
