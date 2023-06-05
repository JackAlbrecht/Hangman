class Scoreboard extends HBox {
    private int wins;
    private int losses;
    private Label winsLabel;
    private Label lossesLabel;

    public Scoreboard() {
        wins = 0;
        losses = 0;
        winsLabel = new Label("Wins: " + wins);
        lossesLabel = new Label("Losses: " + losses);
        getChildren().addAll(winsLabel, lossesLabel);
    }

    public void update(boolean isWin) {
        if (isWin) {
            wins++;
        } else {
            losses++;
        }
        updateLabels();
    }

    private void updateLabels() {
        winsLabel.setText("Wins: " + wins);
        lossesLabel.setText("Losses: " + losses);
    }
}
