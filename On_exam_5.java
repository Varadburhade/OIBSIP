




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class On_exam_5 {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private int currentQuestionIndex = 0;
    private Timer timer;
    private int timeLeft = 30; // Timer for each question
    private static final String[][] questions = {
        {"Which component is used to store electrical energy?", "Resistor", "Capacitor", "Inductor", "Transistor", "Capacitor"},
        {"What is the main function of a diode?", "To amplify signals", "To store charge", "To allow current to flow in one direction only", "To resist current flow", "To allow current to flow in one direction only"},
        {"What does LED stand for?", "Light Emitting Diode", "Low Energy Device", "Light Energy Diode", "Long Emission Diode", "Light Emitting Diode"},
        {"In a circuit, the point at which the current splits is called a:", "Node", "Branch", "Loop", "Junction", "Junction"},
        {"What is the process of adding impurities to a pure semiconductor called?", "Doping", "Etching", "Diffusion", "Oxidation", "Doping"}
    };
    private int score = 0;
    private JLabel questionLabel, timerLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton nextButton, submitButton;
    String newUsername="user";
    String newPassword="pass";

    public On_exam_5() {
        frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createLoginPanel(), "LoginPanel");
        cardPanel.add(createUpdateProfilePanel(), "UpdateProfilePanel");
        cardPanel.add(createQuizPanel(), "QuizPanel");
        cardPanel.add(createResultPanel(), "ResultPanel");

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
    JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));  // Added spacing between rows and columns
    JTextField usernameField = new JTextField(10);  // Set smaller width for the text field
    JPasswordField passwordField = new JPasswordField(10);  // Set smaller width for the password field
    JButton loginButton = new JButton("Login");

    // Set font for the labels
    JLabel usernameLabel = new JLabel("Username:");
    usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

    // Set font for the text fields
    usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 14));

    // Set the button style (rounded edges)
    loginButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
    loginButton.setFocusPainted(false);  // Remove focus ring
    loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  // Add border
    loginButton.setBorderPainted(true);
    loginButton.setContentAreaFilled(false);
    loginButton.setOpaque(true);
    loginButton.setBackground(new Color(0, 123, 255));  // Background color for the button
    loginButton.setForeground(Color.WHITE);
    loginButton.setPreferredSize(new Dimension(100, 40));  // Smaller button size

    // Add components to the panel
    panel.add(usernameLabel);
    panel.add(usernameField);
    panel.add(passwordLabel);
    panel.add(passwordField);

    // Create a panel to center the login button at the bottom
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(loginButton);

    // Add the button panel to the main panel
    panel.add(new JLabel());  // Empty label for grid layout
    panel.add(buttonPanel);

    loginButton.addActionListener(e -> {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (username.equals(newUsername) && password.equals(newPassword)) {
            int response = JOptionPane.showConfirmDialog(
                frame,
                "Do you want to update your profile and password?",
                "Profile Update",
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                cardLayout.show(cardPanel, "UpdateProfilePanel");
            } else {
                cardLayout.show(cardPanel, "QuizPanel");
                startTimer();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password!");
        }
    });

    return panel;
}


    private JPanel createUpdateProfilePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton updateButton = new JButton("Update");
        updateButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
    updateButton.setFocusPainted(false);  // Remove focus ring
    updateButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  // Add border
    updateButton.setBorderPainted(true);
   updateButton.setContentAreaFilled(false);
    updateButton.setOpaque(true);
    updateButton.setBackground(new Color(0, 123, 255));  // Background color for the button
    updateButton.setForeground(Color.WHITE);
    updateButton.setPreferredSize(new Dimension(100, 40));  // Smaller button size
        JButton backButton = new JButton("Back to Quiz");
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
    backButton.setFocusPainted(false);  // Remove focus ring
    backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  // Add border
    backButton.setBorderPainted(true);
   backButton.setContentAreaFilled(false);
    backButton.setOpaque(true);
    backButton.setBackground(new Color(0, 123, 255));  // Background color for the button
    backButton.setForeground(Color.WHITE);
    backButton.setPreferredSize(new Dimension(100, 40));  // Smaller button size

        JLabel usernamef = new JLabel("New Username:");
        usernamef.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(usernamef);
        panel.add(usernameField);
        JLabel passf = new JLabel("New Password:");
         passf.setFont(new Font("Times New Roman", Font.BOLD, 14));
         panel.add(passf);
        panel.add(passwordField);
        panel.add(updateButton);
        panel.add(backButton);

        updateButton.addActionListener(e -> {
     newUsername = usernameField.getText();
    newPassword = new String(passwordField.getPassword());
    if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Profile updated successfully!");
        // Switch back to login panel after updating profile
        cardLayout.show(cardPanel, "LoginPanel");
    } else {
        JOptionPane.showMessageDialog(frame, "Both fields must be filled!");
    }
});


        backButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "QuizPanel");
            startTimer();
        });

        return panel;
    }

    private JPanel createQuizPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Stack components vertically

    // Set alignment for the panel to center
    panel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Question label
    questionLabel = new JLabel();
    questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));  // Set font for question
    questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the question

    // Timer label
    timerLabel = new JLabel("Time Left: " + timeLeft + "s");
    timerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));  // Set font for timer
    timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the timer

    // Create option buttons and group them
    optionButtons = new JRadioButton[4];
    optionsGroup = new ButtonGroup();

    // Loop through and add option buttons
    for (int i = 0; i < optionButtons.length; i++) {
        optionButtons[i] = new JRadioButton();
        optionButtons[i].setFont(new Font("Times New Roman", Font.PLAIN, 14));  // Set font for options
        optionButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the option buttons
        optionsGroup.add(optionButtons[i]);  // Add to button group
    }

    // Button panel for Next and Submit buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));  // Center the buttons
    nextButton = new JButton("Next");
    nextButton.addActionListener(e -> handleNextQuestion(panel));

    submitButton = new JButton("Submit");
    submitButton.addActionListener(e -> submitQuiz());

    // Add the buttons to the button panel
    nextButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
    submitButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
    buttonPanel.add(nextButton);
    buttonPanel.add(submitButton);

    // Add all components to the main panel
    panel.add(questionLabel);
    panel.add(Box.createVerticalStrut(20));  // Space between question and options
    for (int i = 0; i < optionButtons.length; i++) {
        panel.add(optionButtons[i]);
        panel.add(Box.createVerticalStrut(5));  // Space between option buttons
    }
    panel.add(Box.createVerticalStrut(20));  // Space between options and buttons
    panel.add(timerLabel);
    panel.add(buttonPanel);

    updateQuestion();
    return panel;
}


    private JPanel createResultPanel() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel resultLabel = new JLabel();
    JButton restartButton = new JButton("Restart Quiz");
    restartButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    JButton logoutButton = new JButton("Logout");
    logoutButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    JButton exitButton = new JButton("Exit");
    exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    

    // Action for Restart button
    restartButton.addActionListener(e -> {
        score = 0;
        currentQuestionIndex = 0;
        timeLeft = 30;
        cardLayout.show(cardPanel, "LoginPanel");
    });

    // Action for Logout button
    logoutButton.addActionListener(e -> {
        cardLayout.show(cardPanel, "LoginPanel"); // Take user to the login page
    });
    
    exitButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Perform the exit operation when the button is pressed
        System.exit(0);
    }
});
    

    panel.add(resultLabel);
    panel.add(restartButton);
    panel.add(logoutButton); // Add Logout button to the panel
    panel.add(exitButton); 

    
    panel.setLayout(new FlowLayout(FlowLayout.CENTER));

    return panel;
}


    private void handleNextQuestion(JPanel panel) {
        if (currentQuestionIndex < questions.length - 1) {
            recordAnswer();
            currentQuestionIndex++;
            timeLeft = 30;
            updateQuestion();
        } else {
            submitQuiz();
        }
    }

    private void updateQuestion() {
        optionsGroup.clearSelection();
        questionLabel.setText(questions[currentQuestionIndex][0]);
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(questions[currentQuestionIndex][i + 1]);
        }
    }

    private void recordAnswer() {
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected() && optionButtons[i].getText().equals(questions[currentQuestionIndex][5])) {
                score++;
            }
        }
    }

    private void submitQuiz() {
        recordAnswer();
        timer.cancel();
        JOptionPane.showMessageDialog(frame, "Quiz Submitted! Your score: " + score + "/" + questions.length);
        cardLayout.show(cardPanel, "ResultPanel");
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText("Time Left: " + timeLeft + "s");
                } else {
                    if (currentQuestionIndex < questions.length - 1) {
                        currentQuestionIndex++;
                        timeLeft = 30;
                        updateQuestion();
                    } else {
                        submitQuiz();
                    }
                }
            }
        }, 1000, 1000);
    }

    public static void main(String[] args) {
        new On_exam_5();
    }
}
