
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;
    private int penalties;
    private int games;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGames() {
        return games;
    }


    @Override
    public int compareTo(Player p) {
        if (p.goals + p.assists == this.goals + this.assists) {
            return p.goals - this.goals;
        }
        else {
            return  (p.goals + p.assists) - (this.goals + this.assists);
        } 
    }

    /*
    @Override
    public String toString() {
        return name + " " + nationality  
        + " team " + team 
        + " goals " + goals 
        + " assists " + assists
        + " penalties " + penalties
        + " games " + games;
    }
    */

    @Override
    public String toString() {
        return String.format("%-20s %s   %2d + %2d = %2d", name, team, goals, assists, (goals+assists));
    }







}
