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
    private static final String LOVESCORE = "Love";
    private static final String FIFTEENSCORE = "Fifteen";
    private static final String THIRTYSCORE = "Thirty";
    private static final String FORTYSCORE = "Forty";
    List<String> scoreDescription = List.of(LOVESCORE,FIFTEENSCORE,THIRTYSCORE,FORTYSCORE);

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        String score = "";
        score = playerScoreLessThanFour(score);
        score = playerScoreHigherThanTwo(score, player1EqualPlayer2(), player1Point, "Deuce");
        score = playerLead(score, player1Point, player2Point);
        score = playerLead(score, player2Point, player1Point);
        score = playerAdvantageWithPointsLessThanFour(score, player1Point,player2Point,player1Result,player2Result);
        score = playerAdvantageWithPointsLessThanFour(score, player2Point,player1Point,player2Result,player1Result);
        score = playerScoreHigherThanTwo(score, isGreater(player1Point, player2Point), player2Point, "Advantage " + player1Name);
        score = playerScoreHigherThanTwo(score, isGreater(player2Point, player1Point), player1Point, "Advantage " + player2Name);
        score = winningPlayer(score, player1Point, player2Point, player1Name);
        score = winningPlayer(score, player2Point, player1Point, player2Name);
        return score;
    }

    private boolean isGreater(int player1Point, int player2Point) {
        return player1Point > player2Point;
    }

    private boolean player1EqualPlayer2() {
        return player1Point == player2Point;
    }

    private String winningPlayer(String score, int playerLeadPoint, int playerBehindPoint, String playerName) {
        if (isPlayerLeadGreaterThanPlayerBehind(playerLeadPoint, playerBehindPoint)) {
            score = "Win for " + playerName;
        }
        return score;
    }

    private boolean isPlayerLeadGreaterThanPlayerBehind(int playerLeadPoint, int playerBehindPoint) {
        return greaterAndEqualThan(playerLeadPoint, 4) && greaterAndEqualThan(playerBehindPoint, 0) && greaterAndEqualThan(playerLeadPoint - playerBehindPoint, 2);
    }

    private boolean greaterAndEqualThan(int playerPoints, int comparisionValue) {
        return (playerPoints) >= comparisionValue;
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
        return isEqualPointsLessThanFour(isGreater(playerLeadPoints, playerBehindPoints), playerLeadPoints);
    }

    private String scoreDescription(int playerPoints, int tempScore, String playerResult, String scoreDescription){
        if(playerPoints == tempScore) playerResult = scoreDescription;
        return playerResult;
    }

    private String playerLead(String score, int playerPointsLead, int playerPointsZero) {
        if (playerLeading(playerPointsLead, playerPointsZero))
        {
            String playerLeadResult ="";
            String playerBehindResult ="";
            playerLeadResult = scoreDescription(playerPointsLead, 1, playerBehindResult, scoreDescription.get(1));
            playerLeadResult = scoreDescription(playerPointsLead, 2, playerLeadResult, scoreDescription.get(2));
            playerLeadResult = scoreDescription(playerPointsLead, 3, playerLeadResult, scoreDescription.get(3));
            playerBehindResult = LOVESCORE;
            score = playerLeadResult + "-" + playerBehindResult;
        }
        return score;
    }

    private boolean playerLeading(int playerPointsLead, int playerPointsZero) {
        return isGreater(playerPointsLead, 0) && playerPointsZero == 0;
    }

    private String playerScoreHigherThanTwo(String score, boolean b, int player1Point, String deuce) {
        if (b && greaterAndEqualThan(player1Point, 3))
            score = deuce;
        return score;
    }

    private String playerScoreLessThanFour(String score) {
        if (isEqualPointsLessThanFour(player1EqualPlayer2(), player1Point))
        {
            score = scoreDescription(player1Point, 0, score, scoreDescription.get(0));
            score = scoreDescription(player1Point, 1, score, scoreDescription.get(1));
            score = scoreDescription(player1Point, 2, score, scoreDescription.get(2));
        }
        return score.concat("-All");
    }

    private boolean isEqualPointsLessThanFour(boolean b, int player1Point) {
        return b && player1Point < 4;
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