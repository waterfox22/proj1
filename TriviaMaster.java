/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mein1;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author студент
 */
public class TriviaMaster extends JFrame {

    private String[] triviaQuestions = {
    "What is the default value of a boolean variable in Java?",
    "Which keyword is used to denote a constant in Java?",
    "What does JVM stand for?",
    "Which of the following is a valid declaration of a String in Java?",
    "What is the parent class of all exceptions in Java?"
    };

    private String[][] answerChoices = {
        {"true", "false", "0", "null"},
        {"const", "final", "static", "constant"},
        {"Java Virtual Machine", "Java Variable Machine", "Java Version Management", "Java Visual Machine"},
        {"String s = new String();", "String s = \"Hello\";", "String s = new String(\"Hello\");", "All of the above"},
        {"Throwable", "Error", "Exception", "RuntimeException"}
    };

    private int[] correctAnswers = {1, 1, 0, 3, 0}; // Indexes of the correct answers

    private int currentIndex = 0;
    private int totalScore = 0;

    private JLabel questionLabel;
    private JRadioButton[] choiceButtons;
    private ButtonGroup choicesGroup;
    private JButton nextButton;

    public TriviaMaster() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Trivia Master");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        questionLabel = new JLabel("Question Area");
        questionLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        questionLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        add(questionLabel, BorderLayout.NORTH);

        JPanel choicesPanel = new JPanel();
        choicesPanel.setLayout(new GridLayout(5, 1, 5, 5));
        choicesPanel.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));

        choiceButtons = new JRadioButton[4];
        choicesGroup = new ButtonGroup();

        for (int j = 0; j < 4; j++) {
            choiceButtons[j] = new JRadioButton();
            choicesGroup.add(choiceButtons[j]);
            choicesPanel.add(choiceButtons[j]);
        }

        add(choicesPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next Question");
        nextButton.addActionListener(e -> handleNext());
        add(nextButton, BorderLayout.SOUTH);

        displayQuestion(currentIndex);
    }

    private void displayQuestion(int idx) {
        questionLabel.setText("Q" + (idx + 1) + ": " + triviaQuestions[idx]);
        choicesGroup.clearSelection();
        for (int j = 0; j < 4; j++) {
            choiceButtons[j].setText(answerChoices[idx][j]);
        }
        if (idx == triviaQuestions.length - 1) {
            nextButton.setText("Submit Answers");
        } else {
            nextButton.setText("Next Question");
        }
    }

    private void handleNext() {
        if (isOptionSelected()) {
            validateSelection();
            currentIndex++;
            if (currentIndex < triviaQuestions.length) {
                displayQuestion(currentIndex);
            } else {
                showcaseResult();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an answer to proceed.");
        }
    }

    private boolean isOptionSelected() {
        for (JRadioButton rb : choiceButtons) {
            if (rb.isSelected()) {
                return true;
            }
        }
        return false;
    }

    private void validateSelection() {
        for (int j = 0; j < choiceButtons.length; j++) {
            if (choiceButtons[j].isSelected() && j == correctAnswers[currentIndex]) {
                totalScore++;
            }
        }
    }

    private void showcaseResult() {
        JOptionPane.showMessageDialog(this, "Your total score: " + totalScore + "/" + triviaQuestions.length);
        System.exit(0);
    }
}
