package co.com.sofkau.katatennisgame;

public class TennisGame1 implements TennisGame {
    
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private String player1Name = "player1";
    private String player2Name = "player2";

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase(player1Name))
            scorePlayer1 += 1;
        else
            scorePlayer2 += 1;
    }

    public String getScore() {
        String score = "";
        if (isEqual())  score = getScorePlayer1();
        else if (isUpToFourPoints()) score = getAdvantage();
        else if (isBetweenOneAndThreePoints())
        {
            for (int i=1; i<3; i++)
            {
                score = getResult(score, i);
            }
        }
        return score;
    }

    private boolean isEqual() {
        return scorePlayer1 == scorePlayer2;
    }
    private boolean isBetweenOneAndThreePoints() {
        return scorePlayer1 >= 1 || scorePlayer1 < 3 || scorePlayer2 >= 1 || scorePlayer2 < 3;
    }
    private boolean isUpToFourPoints() {
        return scorePlayer1 >= 4 || scorePlayer2 >= 4;
    }

    private String getAdvantage() {
        String score = "";
        int minusResult = scorePlayer1 - scorePlayer2;
        score = advantagePlayer(score, minusResult, 1, player1Name);
        score = advantagePlayer(score, minusResult, -1, player2Name);
        score = winninPlayer(score, minusResult >= 2, "Win for ", player1Name);
        score = winninPlayer(score, minusResult <= -2, "Win for ", player2Name);
        return score;
    }

    private String winninPlayer(String score, boolean b, String s, String playerName) {
        if (b) score = s + playerName;
        return score;
    }

    private String advantagePlayer(String score, int minusResult, int i, String playerName) {
        score = winninPlayer(score, minusResult == i, "Advantage ", playerName);
        return score;
    }

    private String getScorePlayer1() {
        String score;
        switch (scorePlayer1)
        {
            case 0:
                    score = "Love-All";
                break;
            case 1:
                    score = "Fifteen-All";
                break;
            case 2:
                    score = "Thirty-All";
                break;
            default:
                    score = "Deuce";
                break;

        }
        return score;
    }


    private String getResult(String score, int i) {
        int tempScore;
        if (i ==1) tempScore = scorePlayer1;
        else { score +="-"; tempScore = scorePlayer2;}
        score = getScoreDescription(score, tempScore);
        return score;
    }

    private String getScoreDescription(String score, int tempScore) {
        switch(tempScore)
        {
            case 0:
                score = score.concat("Love");
                break;
            case 1:
                score = score.concat("Fifteen");
                break;
            case 2:
                score = score.concat("Thirty");
                break;
            case 3:
                score =score.concat("Forty");
                break;
        }
        return score;
    }


}
