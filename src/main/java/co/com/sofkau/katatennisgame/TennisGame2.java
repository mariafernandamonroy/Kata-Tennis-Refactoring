package co.com.sofkau.katatennisgame;

import java.util.List;

public class TennisGame2 implements TennisGame
{
    private int player1Point = 0;
    private int player2Point = 0;

    private String player1Result = "";
    private String player2Result = "";
    private String player1Name = "player1";
    private String player2Name = "player2";
    private final String loveScore = "Love";
    private final String fifteenScore = "Fifteen";
    private final String thirtyScore = "Thirty";
    private final String fortyScore = "Forty";
    List<String> scoreDescription = List.of(loveScore,fifteenScore,thirtyScore,fortyScore);

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        String score = "";
        score = playerScoreLessThanFour(score);
        score = playerScoreHigherThanTwo(score, player1EqualPlayer2(), player1Point, "Deuce");
        score = playerLead(score, player1Point, player2Point, player1Result, player2Result);
        score = playerLead(score, player2Point, player1Point, player2Result, player1Result);
        score = playerAdvantageWithPointsLessThanFour(score, player1Point,player2Point,player1Result,player2Result);
        score = playerAdvantageWithPointsLessThanFour(score, player2Point,player1Point,player2Result,player1Result);
        score = playerScoreHigherThanTwo(score, player1Point > player2Point, player2Point, "Advantage " + player1Name);
        score = playerScoreHigherThanTwo(score, player2Point > player1Point, player1Point, "Advantage " + player2Name);
        score = winningPlayer(score, player1Point, player2Point, player1Name);
        score = winningPlayer(score, player2Point, player1Point, player2Name);
        return score;
    }

    private boolean player1EqualPlayer2() {
        return player1Point == player2Point;
    }

    private String winningPlayer(String score, int playerLeadPoint, int playerBehindPoint, String playerName) {
        if (greaterAndEqualThan(playerLeadPoint, 4) && greaterAndEqualThan(playerBehindPoint, 0) && greaterAndEqualThan(playerLeadPoint - playerBehindPoint, 2)) {
            score = "Win for " + playerName;
        }
        return score;
    }

    private boolean greaterAndEqualThan(int player, int comparisionValue) {
        return (player) >= comparisionValue;
    }

    private String playerAdvantageWithPointsLessThanFour(String score, int playerLeadPoints, int playerBehindPoints, String playerLeadResult, String playerBehindResult) {
        if (isPlayerLead(playerLeadPoints, playerBehindPoints))
        {
            playerLeadResult = scoreDescription(playerLeadPoints, 2, playerLeadResult, scoreDescription.get(2));
            playerLeadResult = scoreDescription(playerLeadPoints, 3, playerLeadResult, scoreDescription.get(3));
            playerBehindResult = scoreDescription(playerBehindPoints, 2, playerBehindResult, scoreDescription.get(2));
            playerBehindResult = scoreDescription(playerBehindPoints, 1, playerBehindResult, scoreDescription.get(1));
            score = playerLeadResult + "-" + playerBehindResult;
        }
        return score;
    }

    private boolean isPlayerLead(int playerLeadPoints, int playerBehindPoints) {
        return playerLeadPoints > playerBehindPoints && playerLeadPoints < 4;
    }

    private String scoreDescription(int playerPoints, int tempScore, String playerResult, String scoreDescription){
        if(playerPoints == tempScore) playerResult = scoreDescription;
        return playerResult;
    }

    private String playerLead(String score, int playerPointsLead, int playerPointsZero, String playerLeadResult, String playerBehindResult) {
        if (playerLeading(playerPointsLead, playerPointsZero))
        {
            playerLeadResult = scoreDescription(playerPointsLead, 1, playerBehindResult, scoreDescription.get(1));
            playerLeadResult = scoreDescription(playerPointsLead, 2, playerLeadResult, scoreDescription.get(2));
            playerLeadResult = scoreDescription(playerPointsLead, 3, playerLeadResult, scoreDescription.get(3));
            playerBehindResult = loveScore;
            score = playerLeadResult + "-" + playerBehindResult;
        }
        return score;
    }

    private boolean playerLeading(int playerPointsLead, int playerPointsZero) {
        return playerPointsLead > 0 && playerPointsZero == 0;
    }

    private String playerScoreHigherThanTwo(String score, boolean b, int player1Point, String deuce) {
        if (b && greaterAndEqualThan(player1Point, 3))
            score = deuce;
        return score;
    }

    private String playerScoreLessThanFour(String score) {
        if (player1EqualPlayer2() && player1Point < 4)
        {
            if (player1Point==0)
                score = loveScore;
            if (player1Point==1)
                score = fifteenScore;
            if (player1Point==2)
                score = thirtyScore;
            score.concat("-All");
        }
        return score;
    }

    public void setPlayer1Score(int number){
        
        for (int i = 0; i < number; i++)
        {
            player1Score();
        }
            
    }
    
    public void setPlayer2Score(int number){
        
        for (int i = 0; i < number; i++)
        {
            player2Score();
        }
            
    }
    
    public void player1Score(){
        player1Point++;
    }
    
    public void player2Score(){
        player2Point++;
    }

    public void wonPoint(String player) {
        if (player.equalsIgnoreCase(player1Name))
            player1Score();
        else
            player2Score();
    }
}