package co.com.sofkau.katatennisgame;

public class TennisGame3 implements TennisGame {
    
    private int pointsPlayer2;
    private int pointsPlayer1;
    private String player1Name;
    private String player2Name;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        String selectedString;
        if (scoreOneToOne()) {
            String[] score = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
            selectedString = score[pointsPlayer1];
            return playerScore(selectedString, score);
        } else {
            if (isEqualPoints(pointsPlayer1, pointsPlayer2)) return "Deuce";
            selectedString = isAdvantageOrWinningPlayer(isGreater(), player1Name, player2Name);
            return isAdvantageOrWinningPlayer(isAdvantageOrWinningPoints(), concatenateDeclaration(selectedString, "Advantage "), concatenateDeclaration(selectedString, "Win for "));
        }
    }

    private String concatenateDeclaration(String selectedString, String s) {
        return s + selectedString;
    }

    private String playerScore(String selectedString, String[] score) {
        return isEqualPoints(pointsPlayer1, pointsPlayer2) ? concatenateDeclaration("-All", selectedString) : selectedString + "-" + score[pointsPlayer2];
    }

    private String isAdvantageOrWinningPlayer(boolean advantageOrWinningPoints, String declaration1, String declaration2) {
        return advantageOrWinningPoints ? declaration1 : declaration2;
    }

    private boolean scoreOneToOne() {
        return isLessThan(pointsPlayer1) && isLessThan(pointsPlayer2) && isNotMaxPoints();
    }

    private boolean isNotMaxPoints() {
        return (pointsPlayer1 + pointsPlayer2 != 6);
    }

    private boolean isAdvantageOrWinningPoints() {
        return substractingPlayerPoints() * (substractingPlayerPoints()) == 1;
    }

    private int substractingPlayerPoints() {
        return pointsPlayer1 - pointsPlayer2;
    }

    private boolean isEqualPoints(int pointsPlayer1, int pointsPlayer2) {
        return pointsPlayer1 == pointsPlayer2;
    }

    private boolean isGreater() {
        return pointsPlayer1 > pointsPlayer2;
    }

    private boolean isLessThan(int pointsPlayer1) {
        return pointsPlayer1 < 4;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase("player1"))
            this.pointsPlayer1 += 1;
        else
            this.pointsPlayer2 += 1;
    }
}
