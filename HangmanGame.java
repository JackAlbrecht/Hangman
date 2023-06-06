import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HangmanGame extends Application {
   private String secretWord;
   private StringBuilder guessedWord;
   private int attemptsLeft;
   private String[] wordBank = { "abandon", "action", "alien", "announce", "antenna", "annual", "balance", "bargain", "beacon", "begin", "beneath", "blond", "broken", "canyon", "carbon", "certain", "chance", "change", "channel", "chicken", "clean", "common", "concern", "condition", "cotton", "crown", "danger", "define", "denial", "design", "diamond", "dragon", "dungeon", "earn", "endless", "enjoy", "enter", "evening", "falcon", "frozen", "garden", "given", "green", "heaven", "hidden", "honest", "human", "hunger", "icon", "income", "innocent", "island", "jargon", "journey", "junior", "keen", "kitten", "laden", "latin", "lemon", "listen", "lunar", "mansion", "mention", "million", "moment", "mountain", "nation", "noble", "normal", "notion", "ocean", "open", "origin", "oven", "penguin", "person", "planet", "prison", "queen", "question", "random", "reason", "ribbon", "rhythm", "sudden", "sustain", "tension", "token", "trend", "tunnel", "uncertain", "union", "urban", "vacation", "version", "wander", "winter", "woven", "zebra", "zenith" };

   private Label guessedWordLabel;
   private Label attemptsLeftLabel;
   private TextField guessTextField;
   private Button guessButton;
   private Scoreboard scoreboard;

   // GUI ONLY NO CODE FROM THE GAME HERE
   public void start(Stage primaryStage) {
      selectSecretWord();
      initializeGuessedWord();
      attemptsLeft = 6;
   
      Label titleLabel = new Label("Hangman Game");
      Label wordLabel = new Label("Secret Word: ");
      guessedWordLabel = new Label(guessedWord.toString());
      Label attemptsLabel = new Label("Attempts Left: ");
      attemptsLeftLabel = new Label(String.valueOf(attemptsLeft));
      Label guessLabel = new Label("Enter your guess: ");
      guessTextField = new TextField();
      guessButton = new Button("Guess");
   
      guessButton.setOnAction(e -> makeGuess());
      guessTextField.setOnAction(e -> makeGuess());
   
      
      scoreboard = new Scoreboard();
   
      VBox root = new VBox(10);
      root.setPadding(new Insets(10));
      root.getChildren().addAll(titleLabel, wordLabel, guessedWordLabel, attemptsLabel, attemptsLeftLabel, guessLabel, guessTextField, guessButton, scoreboard);
      
      Button resetButton = new Button("Reset");
    resetButton.setOnAction(e -> resetGame());
    root.getChildren().add(resetButton);
    
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Hangman Game");
      primaryStage.show();
   }

private void resetGame() {
    selectSecretWord();
    initializeGuessedWord();
    attemptsLeft = 6;

    guessedWordLabel.setText(guessedWord.toString());
    attemptsLeftLabel.setText(String.valueOf(attemptsLeft));

    guessTextField.clear();
    guessButton.setDisable(false);
}
   /*-------------------------------------------------------------------
   Beginning of the Game!
   -------------------------------------------------------------------*/
   // Finds a word from the wordbank based on a random number generated.
   private void selectSecretWord() {
      int randomIndex = (int) (Math.random() * wordBank.length);
      secretWord = wordBank[randomIndex];
   }

   // This is where the placeholders are generated! Once you guess the letter, they disappear and the letter fills that spot.
   private void initializeGuessedWord() {
      guessedWord = new StringBuilder();
      for (int i = 0; i < secretWord.length(); i++) {
         guessedWord.append("_");
      }
   }

   // This takes in the guessed character and then decides if it's correct or not. If correct, you get a letter on the screen, if not, attempts are -- until 0, then you lose.
   private void makeGuess() {
      String input = guessTextField.getText();
      if (input.isEmpty()) {
         return; // If input is empty, do nothing
      }
      char guessedChar = guessTextField.getText().charAt(0);
      boolean isCorrectGuess = updateGuessedWord(guessedChar);
   
      if (isCorrectGuess) {
         if (guessedWord.toString().equals(secretWord)) {
            guessedWordLabel.setText("Congratulations! You guessed the word correctly: " + secretWord);
            guessButton.setDisable(true);
            scoreboard.update(true);
         }
      } else {
         attemptsLeft--;
         attemptsLeftLabel.setText(String.valueOf(attemptsLeft));
         if (attemptsLeft == 0) {
            guessedWordLabel.setText("You lost. The word was: " + secretWord);
            guessButton.setDisable(true);
            scoreboard.update(false);
         }
      }
      guessTextField.clear();
   }

   // If a match is found in the secret word based on the guessed word, the guessed word is updated and is set to true,
   // updates the text with the guessed word, and indicates if the guess was correct or not.
   private boolean updateGuessedWord(char guessedChar) {
      boolean isCorrectGuess = false;
      for (int i = 0; i < secretWord.length(); i++) {
         if (secretWord.charAt(i) == guessedChar) {
            guessedWord.setCharAt(i, guessedChar);
            isCorrectGuess = true;
         }
      }
      guessedWordLabel.setText(guessedWord.toString());
      return isCorrectGuess;
   }

   // This is the main method and all we're doing here is calling the launch() method provided in JavaFX.
   public static void main(String[] args) {
      launch(args);
   }
}
