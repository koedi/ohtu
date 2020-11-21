package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1;
    private String player2;

    public TennisGame(String player1Name, String player2Name) {
        this.player1 = player1Name;
        this.player2 = player2Name  ;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1))
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String scoreToText(int score) {
        switch(score) {
            case 0:
                return "Love";
            case 1: 
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";
        }
    }

    public boolean isDeuce() {
        return player1Score > 3 && player2Score == player1Score;
    }

    public boolean isLateGame() {
        return player1Score >= 4 || player2Score >= 4;
    }

    public String isGame() {
        int temp = player1Score - player2Score;

        if (temp == 1) {
            return "Advantage " + player1;
        } else if (temp == -1) {
            return "Advantage " + player2;
        } else if (temp >1) {
            return "Win for " + player1;
        } else {
            return "Win for " + player2;
        }
    }

    public String getScore() {
        String score = "";

        if (player1Score==player2Score)
        {
            if (!isDeuce()) {
                score = scoreToText(player1Score) + "-All";
            }
            else {
                score = scoreToText(player1Score);
            }
        }
        else if (isLateGame())
        { 
            score = isGame();
        }
        else
        {
            score = scoreToText(player1Score) + "-" + scoreToText(player2Score);
        }
        return score;
    }
}