public class Scoreboard extends HBox {
    private int wins;
    private int losses;
    private Label winsLabel;
    private Label lossesLabel;

    public Scoreboard() {
        wins = 0;
        losses = 0;
        winsLabel = new Label("Wins: " + wins); // displays the number of wins
        lossesLabel = new Label("Losses: " + losses); // displays the number of losses
        getChildren().addAll(winsLabel, lossesLabel); // labels for the Scoreboard layout
    }

    public void update(boolean isWin) {
        if (isWin) {
            wins++; // Increment the number of wins
        } else {
            losses++; // Increment the number of losses
        }
        updateLabels(); // Update the labels with the new counts
    }

    private void updateLabels() {
        winsLabel.setText("Wins: " + wins); // Update the wins label with the new count
        lossesLabel.setText("Losses: " + losses); // Update the losses label with the new count
    }
}
