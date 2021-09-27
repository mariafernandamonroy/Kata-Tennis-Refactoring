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
        if (scorePlayer1==scorePlayer2)
        {
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
        }
        else if (scorePlayer1>=4 || scorePlayer2>=4)
        {
            int minusResult = scorePlayer1-scorePlayer2;
            if (minusResult==1) score ="Advantage " +  player1Name;
            else if (minusResult ==-1) score ="Advantage " + player2Name;
            else if (minusResult>=2) score = "Win for " + player1Name;
            else score ="Win for " + player2Name;
        }
        else
        {
            for (int i=1; i<3; i++)
            {
                score = getResult(score, i);
            }
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
                score +="Love";
                break;
            case 1:
                score +="Fifteen";
                break;
            case 2:
                score +="Thirty";
                break;
            case 3:
                score +="Forty";
                break;
        }
        return score;
    }


}
