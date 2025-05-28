package J18DrExtAbstractKlubTrenerIgraci;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

class League {
    private List<Club> clubs; // liga ima listu klubova
    private List<Match> matches; // liga ima listu matcheva

    public League() {
        clubs = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public void addClub(Club club) {
        clubs.add(club);
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public void printActiveClubs() {
        out.println("Active Clubs:");
        for (Club club : clubs) {
            out.println(club);
        }
    }

    public void printMatches() {
        out.println("Match Results:");
        for (Match match : matches) {
            out.println(match);
        }
    }

    public void printClubById(int clubId) {
        for (Club club : clubs) {
            if (club.getId() == clubId) {
                out.println(club);
                return;
            }
        }
        out.println("Club with ID " + clubId + " not found.");
    }

    public void printMatchById(int matchId) {
        for (Match match : matches) {
            if (match.id == matchId) {
                out.println(match);
                return;
            }
        }
        out.println("Match with ID " + matchId + " not found.");
    }
}
