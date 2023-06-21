import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

public class LabFinalProject extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JCheckBox[] optionCheckBoxes;
    private JButton submitButton;
    private JLabel imageLabel;

    private String[] questions = {
        "Which programming language is used for Android app development?",
        "What does HTML stand for?",
        "Which of the following are HTML tags?",
        "What is the purpose of a constructor in Java?"
    };

    private String[][] options = {
        {"Java", "Kotlin", "C#", "Python"},
        {"HyperText Markup Language", "High-level Text Manipulation Language", "Home Tool Markup Language"},
        {"<div>", "<p>", "<span>", "<table>"},
        {"To create objects", "To initialize class variables", "To define methods", "To handle exceptions"}
    };

    private String[] answers = {"Java", "HyperText Markup Language", "<div>", "To create objects"};

    public LabFinalProject() {
        setTitle("Lab Final Project");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField(15);
        JLabel idLabel = new JLabel("Student ID:");
        idTextField = new JTextField(10);

        infoPanel.add(nameLabel);
        infoPanel.add(nameTextField);
        infoPanel.add(idLabel);
        infoPanel.add(idTextField);

        add(infoPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2));

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(0, 1));

        optionCheckBoxes = new JCheckBox[options.length];
        for (int i = 0; i < options.length; i++) {
            JLabel questionLabel = new JLabel(questions[i]);
            questionPanel.add(questionLabel);

            JPanel optionPanel = new JPanel();
            optionPanel.setLayout(new GridLayout(0, 1));
            for (int j = 0; j < options[i].length; j++) {
                optionCheckBoxes[j] = new JCheckBox(options[i][j]);
                optionPanel.add(optionCheckBoxes[j]);
            }
            questionPanel.add(optionPanel);
        }

        JScrollPane questionScrollPane = new JScrollPane(questionPanel);
        contentPanel.add(questionScrollPane);

        // Load image from URL
        ImageIcon imageIcon = null;
        try {
            URL imageURL = new URL("https://images.unsplash.com/photo-1610563166150-b34df4f3bcd6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=776&q=80");
            imageIcon = new ImageIcon(imageURL);
            Image image = imageIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageLabel = new JLabel(imageIcon);
        contentPanel.add(imageLabel);

        add(contentPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder selectedAnswers = new StringBuilder();
                StringBuilder allAnswers = new StringBuilder();
                for (int i = 0; i < optionCheckBoxes.length; i++) {
                    if (optionCheckBoxes[i].isSelected()) {
                        selectedAnswers.append(questions[i]).append(": ").append(optionCheckBoxes[i].getText()).append("\n");
                    }
                    allAnswers.append(questions[i]).append(": ").append(answers[i]).append("\n");
                }

                String name = nameTextField.getText();
                String id = idTextField.getText();

                String message = "Name: " + name + "\nStudent ID: " + id + "\nSelected Answers:\n" + selectedAnswers.toString() + "\nAll Answers:\n" + allAnswers.toString();
                JOptionPane.showMessageDialog(null, message);
            }
        });
        add(submitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LabFinalProject();
            }
        });
    }
}
