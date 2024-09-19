import javax.swing.*;      //provides components like buttons, text fields, labels, etc.
import java.awt.*;         //provides classes for GUI layouts, colors, and more.
import java.awt.event.*;   //to handle events like button click.
import java.util.Random;   //used to generate random numbers.

public class NoGuessingGameGUI {

    // GUI Components
    private JFrame frame;  //The main window of your GUI.
    private JTextField guessField;  //A text box where the user can type their guess.
    private JLabel messageLabel, instructionsLabel; //Labels to display messages (like instructions and feedback).
    private JButton guessButton, playAgainButton; // Buttons to make guesses or restart the game.

    // Game Variables
    private int randomno; //The randomly generated number the user needs to guess.
    private int attempts; //The number of attempts the user has made.
    private final int maxAttempts = 5; //The maximum number of guesses allowed (set to 5).

    // Constructor to setup the GUI
    public NoGuessingGameGUI() {
        setupFrame();
        setupLabels();    // It calls methods to set up the frame, labels, text field, buttons, layout, and game logic.
        setupTextField();
        setupButtons();
        setupLayout();
        resetGame();
    }

    // Method to setup the main frame with background color
    private void setupFrame() {
        frame = new JFrame("Number Guessing Game");  //Creates a new window titled "Number Guessing Game."
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ensures the program exits when the window is closed.
        frame.setSize(600, 450); // Start with normal window size
        frame.setLayout(new BorderLayout()); // Sets a layout for arranging components.
        frame.getContentPane().setBackground(new Color(255, 222, 85));  // Yellow background
        
        // Center the frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);    //Centers the window on the screen.
        
        // Notify the user to maximize the window for better experience
        JOptionPane.showMessageDialog(frame, 
            "For the best experience, please maximize the window.");
    
        frame.setVisible(true);  //Makes the window visible.
    }

    // Method to setup the labels with colors and properly structured instructions
    private void setupLabels() {
        instructionsLabel = new JLabel("<html><div style='text-align: left;'>"
                + "<strong><u>Welcome to the Number Guessing Game!</u></strong><br/><br/>"
                + "<strong>Game Instructions:</strong><br/>"
                + "<ul>"
                + "<li>The system has selected a random number between <strong>0</strong> and <strong>100</strong>.</li><br/>"
                + "<li>You have a maximum of <strong>5 attempts</strong> to guess the correct number.</li><br/>"
                + "<li>After each guess, feedback will be provided:</li><br/>"
                + "<ul>"
                + "<li><strong>Too High</strong>: Your guess is higher than the correct number.</li><br/>"
                + "<li><strong>Too Low</strong>: Your guess is lower than the correct number.</li>"
                + "</ul><br/>"
                + "<li>Good luck and enjoy the game!</li>"
                + "</ul><br/></div></html>");
        instructionsLabel.setForeground(Color.BLUE);  // Set text color to blue

        messageLabel = new JLabel("Make your guess!");
        messageLabel.setForeground(Color.RED);  // Set text color to red
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add instructions and message label at the top of the frame
        frame.add(instructionsLabel, BorderLayout.NORTH);
        frame.add(messageLabel, BorderLayout.CENTER);
    }

    // Method to setup the text field and buttons at the bottom with background colors
    private void setupTextField() {
        guessField = new JTextField(10);  // a text field-10 columns wide
        guessField.setBackground(Color.LIGHT_GRAY);  // Set text field background color Gray
    }

    // Method to setup buttons with color
    private void setupButtons() {
        guessButton = new JButton("Guess"); // Creates a button for submitting guesses.
        guessButton.setBackground(Color.GREEN);  // Set button background color - Green
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBackground(Color.YELLOW);  // Set button background color - Yellow
        playAgainButton.setVisible(false);

        // Guess button action listener
        guessButton.addActionListener(new ActionListener() {  //to detect button clicks
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });

        // Play Again button action listener
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }

    // Method to setup the layout and position guess box and buttons at the bottom
    private void setupLayout() {
        JPanel bottomPanel = new JPanel();  //Creates a panel to hold the text field and buttons.
        bottomPanel.setLayout(new FlowLayout());  //Arranges the components in a flow layout.
        bottomPanel.setBackground(new Color(255, 163, 41));  // Sets background color

        bottomPanel.add(new JLabel("Your Guess: ")); //Adds components to the panel (text field, buttons, etc.).
        bottomPanel.add(guessField);
        bottomPanel.add(guessButton);
        bottomPanel.add(playAgainButton);

        // Add bottom panel to the frame at the bottom
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    // Method to process the guess and provide feedback
    private void makeGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == randomno) {
                messageLabel.setText("Congratulations! You guessed it right!");
                guessButton.setEnabled(false);
                playAgainButton.setVisible(true);
            } else if (guess < randomno) {
                messageLabel.setText("Too low! Attempts left: " + (maxAttempts - attempts));
            } else {
                messageLabel.setText("Too high! Attempts left: " + (maxAttempts - attempts));
            }

            if (attempts == maxAttempts && guess != randomno) {
                messageLabel.setText("Game Over! The number was " + randomno);
                guessButton.setEnabled(false);
                playAgainButton.setVisible(true);
            }
        } catch (NumberFormatException ex) {  //Catches any errors if the input is not a number 
            messageLabel.setText("Please enter a valid number.");
        }
    }

    // Method to reset the game
    private void resetGame() {
        randomno = new Random().nextInt(101);  // Generate new random number
        attempts = 0;  // Reset attempts
        guessField.setText("");  // Clear the text field
        messageLabel.setText("Make your guess!");  // Reset message label
        guessButton.setEnabled(true);  // Re-enable the guess button
        playAgainButton.setVisible(false);  // Hide play again button
    }

    // Main method to run the application
    public static void main(String[] args) {
        new NoGuessingGameGUI();
    }
}
