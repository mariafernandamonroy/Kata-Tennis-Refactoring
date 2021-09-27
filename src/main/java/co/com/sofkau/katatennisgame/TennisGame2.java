package co.com.sofkau.katatennisgame;

public class TennisGame2 implements TennisGame
{
    private int player1Point = 0;
    private int player2Point = 0;

    private String player1Result = "";
    private String player2Result = "";
    private String player1Name = "player1";
    private String player2Name = "player2";
    private String fifteenScore = "Fifteen";
    private String thirtyScore = "Thirty";
    private String fortyScore = "Forty";

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        String score = "";
        if (player1Point == player2Point && player1Point < 4)
        {
            if (player1Point==0)
                score = "Love";
            if (player1Point==1)
                score = fifteenScore;
            if (player1Point==2)
                score = thirtyScore;
            score += "-All";
        }
        if (player1Point==player2Point && player1Point>=3)
            score = "Deuce";
        
        if (player1Point > 0 && player2Point==0)
        {
            if (player1Point==1)
                player1Result = fifteenScore;
            if (player1Point==2)
                player1Result = thirtyScore;
            if (player1Point==3)
                player1Result = fortyScore;
            
            player2Result = "Love";
            score = player1Result + "-" + player2Result;
        }
        if (player2Point > 0 && player1Point==0)
        {
            if (player2Point==1)
                player2Result = fifteenScore;
            if (player2Point==2)
                player2Result = thirtyScore;
            if (player2Point==3)
                player2Result = fortyScore;
            
            player1Result = "Love";
            score = player1Result + "-" + player2Result;
        }
        
        if (player1Point>player2Point && player1Point < 4)
        {
            if (player1Point==2)
                player1Result=thirtyScore;
            if (player1Point==3)
                player1Result=fortyScore;
            if (player2Point==1)
                player2Result=fifteenScore;
            if (player2Point==2)
                player2Result=thirtyScore;
            score = player1Result + "-" + player2Result;
        }
        if (player2Point>player1Point && player2Point < 4)
        {
            if (player2Point==2)
                player2Result=thirtyScore;
            if (player2Point==3)
                player2Result=fortyScore;
            if (player1Point==1)
                player1Result=fifteenScore;
            if (player1Point==2)
                player1Result=thirtyScore;
            score = player1Result + "-" + player2Result;
        }
        
        if (player1Point > player2Point && player2Point >= 3)
        {
            score = "Advantage " + player1Name;
        }
        
        if (player2Point > player1Point && player1Point >= 3)
        {
            score = "Advantage " + player2Name;
        }
        
        if (player1Point>=4 && player2Point>=0 && (player1Point-player2Point)>=2)
        {
            score = "Win for " + player1Name;
        }
        if (player2Point>=4 && player1Point>=0 && (player2Point-player1Point)>=2)
        {
            score = "Win for " + player2Name;
        }
        return score;
    }
    
    public void setplayer1Score(int number){
        
        for (int i = 0; i < number; i++)
        {
            player1Score();
        }
            
    }
    
    public void setplayer2Score(int number){
        
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