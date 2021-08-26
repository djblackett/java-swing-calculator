package calculator;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator extends JFrame {
    private static final Map<String, String> map = new HashMap<>();
    private static final String operators = "[\u002B\\-\u00D7\u00F7^]";
    public Calculator() {

        // Main window settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);

        setLocationRelativeTo(this.getContentPane());
        setTitle("Calculator");


        // Layout settings
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel resultLabel = new JLabel();
        JLabel equation = new JLabel();
        equation.setName("EquationLabel");
        resultLabel.setName("ResultLabel");

        equation.setForeground(Color.GREEN.brighter());
        resultLabel.setFont(new Font(null, Font.BOLD, 35));
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;
        add(equation, gbc);
        gbc.gridy = 0;

        add(resultLabel, gbc);

        setBackground(Color.PINK);


        // Button declaration, naming, and size setting -- it's a bit disgusting looking, but it's trivially simple
        // todo refactor code so buttons are in a List. Iterate over list to set button size.
        Dimension buttonSize = new Dimension(60, 30);

        JButton seven = new JButton("7");
        seven.setName("Seven");
        seven.setPreferredSize(buttonSize);
        JButton eight = new JButton("8");
        eight.setName("Eight");
        eight.setPreferredSize(buttonSize);
        JButton nine = new JButton("9");
        nine.setName("Nine");
        nine.setPreferredSize(buttonSize);
        JButton four = new JButton("4");
        four.setName("Four");
        four.setPreferredSize(buttonSize);
        JButton five = new JButton("5");
        five.setName("Five");
        five.setPreferredSize(buttonSize);
        JButton six = new JButton("6");
        six.setName("Six");
        six.setPreferredSize(buttonSize);
        JButton one = new JButton("1");
        one.setName("One");
        one.setPreferredSize(buttonSize);
        JButton two = new JButton("2");
        two.setName("Two");
        two.setPreferredSize(buttonSize);
        JButton three = new JButton("3");
        three.setName("Three");
        three.setPreferredSize(buttonSize);
        JButton zero = new JButton("0");
        zero.setName("Zero");
        zero.setPreferredSize(buttonSize);
        JButton plus = new JButton("+");
        plus.setName("Add");
        plus.setPreferredSize(buttonSize);
        JButton minus = new JButton("-");
        minus.setName("Subtract");
        minus.setPreferredSize(buttonSize);
        JButton divide = new JButton("/");
        divide.setName("Divide");
        divide.setPreferredSize(buttonSize);
        JButton multiply = new JButton("x");
        multiply.setName("Multiply");
        multiply.setPreferredSize(buttonSize);
        JButton equals = new JButton("=");
        equals.setName("Equals");
        equals.setPreferredSize(buttonSize);
        equals.setActionCommand("solve");
        JButton clear = new JButton("C");
        clear.setName("Clear");
        clear.setPreferredSize(buttonSize);
        JButton dot = new JButton(".");
        dot.setName("Dot");
        dot.setPreferredSize(buttonSize);
        JButton delete = new JButton("Del");
        delete.setName("Delete");
        delete.setPreferredSize(buttonSize);
        JButton parentheses = new JButton("( )");
        parentheses.setName("Parentheses");
        parentheses.setPreferredSize(buttonSize);
        JButton squareRoot = new JButton("\u221A");
        squareRoot.setName("SquareRoot");
        squareRoot.setPreferredSize(buttonSize);

        JButton powerTwo = new JButton("x\u00B2");
        powerTwo.setName("PowerTwo");
        powerTwo.setPreferredSize(buttonSize);

        JButton powerY = new JButton("^");
        powerY.setName("PowerY");
        powerY.setPreferredSize(buttonSize);

        JButton plusMinus = new JButton("±");
        plusMinus.setName("PlusMinus");
        plusMinus.setPreferredSize(buttonSize);

        JButton clearEntry = new JButton("CE");
        clearEntry.setName("ClearEntry");
        clearEntry.setPreferredSize(buttonSize);

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridBagLayout());
        GridBagConstraints containerConstraints = new GridBagConstraints();

        GridBagConstraints buttons = new GridBagConstraints();

        JPanel clearAndDelete = new JPanel();
        clearAndDelete.setLayout(new GridBagLayout());

        buttons.gridx = 0;
        clearAndDelete.add(parentheses, buttons);

        buttons.gridx = 1;
        clearAndDelete.add(clearEntry, buttons);
        buttons.gridx = 2;
        clearAndDelete.add(delete, buttons);
        buttons.gridx = 3;
        clearAndDelete.add(clear, buttons);

        // Second Row buttons
        JPanel degreesAndRoots = new JPanel();
        degreesAndRoots.setLayout(new GridBagLayout());
        buttons.gridx = 0;
        degreesAndRoots.add(powerTwo, buttons);

        buttons.gridx = 1;
        degreesAndRoots.add(powerY, buttons);

        buttons.gridx = 2;
        degreesAndRoots.add(squareRoot, buttons);

        buttons.gridx = 3;
        degreesAndRoots.add(divide, buttons);

        JPanel sevenEightNine = new JPanel();
        sevenEightNine.setLayout(new GridBagLayout());

        buttons.gridx = 0;

        sevenEightNine.add(seven, buttons);

        buttons.gridx = 1;
        sevenEightNine.add(eight, buttons);

        buttons.gridx = 2;
        sevenEightNine.add(nine, buttons);

        buttons.gridx = 3;
        sevenEightNine.add(multiply, buttons);

        gbc.gridy  = 3;
        add(sevenEightNine, gbc);

        JPanel fourFiveSix = new JPanel();
        fourFiveSix.setLayout(new GridBagLayout());

        buttons.gridx = 0;
        fourFiveSix.add(four, buttons);

        buttons.gridx = 1;
        fourFiveSix.add(five, buttons);

        buttons.gridx = 2;
        fourFiveSix.add(six, buttons);

        buttons.gridx = 3;
        fourFiveSix.add(minus, buttons);


        JPanel oneTwoThree = new JPanel();
        oneTwoThree.setLayout(new GridBagLayout());

        buttons.gridx = 0;
        oneTwoThree.add(one, buttons);

        buttons.gridx = 1;
        oneTwoThree.add(two, buttons);

        buttons.gridx = 2;
        oneTwoThree.add(three, buttons);

        buttons.gridx = 3;
        oneTwoThree.add(plus, buttons);


        JPanel zeroEqualsMinus = new JPanel();
        zeroEqualsMinus.setLayout(new GridBagLayout());

        buttons.gridx = 0;

        zeroEqualsMinus.add(plusMinus, buttons);

        buttons.gridx = 1;
        zeroEqualsMinus.add(zero, buttons);

        buttons.gridx = 2;
        zeroEqualsMinus.add(dot, buttons);

        buttons.gridx = 3;
        zeroEqualsMinus.add(equals, buttons);



        containerConstraints.gridy = 0;
        containerConstraints.anchor = GridBagConstraints.CENTER;
        buttonContainer.add(clearAndDelete, containerConstraints);

        containerConstraints.gridy = 1;
        buttonContainer.add(degreesAndRoots, containerConstraints);

        containerConstraints.gridy = 2;
        buttonContainer.add(sevenEightNine, containerConstraints);

        containerConstraints.gridy = 3;
        buttonContainer.add(fourFiveSix, containerConstraints);

        containerConstraints.gridy = 4;
        buttonContainer.add(oneTwoThree, containerConstraints);

        containerConstraints.gridy = 5;
        buttonContainer.add(zeroEqualsMinus, containerConstraints);

        gbc.gridy = 4;
        add(buttonContainer, gbc);

        // add button functionality
        seven.addActionListener(e -> equation.setText(equation.getText() + "7"));
        eight.addActionListener(e -> equation.setText(equation.getText() + "8"));
        nine.addActionListener(e -> equation.setText(equation.getText() + "9"));
        four.addActionListener(e -> equation.setText(equation.getText() + "4"));
        five.addActionListener(e -> equation.setText(equation.getText() + "5"));
        six.addActionListener(e -> equation.setText(equation.getText() + "6"));
        one.addActionListener(e -> equation.setText(equation.getText() + "1"));
        two.addActionListener(e -> equation.setText(equation.getText() + "2"));
        three.addActionListener(e -> equation.setText(equation.getText() + "3"));
        zero.addActionListener(e -> equation.setText(equation.getText() + "0"));

        parentheses.addActionListener(e -> {
            String text = equation.getText();
            if (text.length() > 0) {
                String lastCharacter = String.valueOf(text.charAt(text.length() - 1));
                List<String> letterList = Arrays.asList(text.split(""));
                if (Collections.frequency(letterList, "(") == Collections.frequency(letterList, ")")) {
                    equation.setText(text + "(");
                    return;
                }

                if (lastCharacter.matches("\\(") || lastCharacter.matches("[\u002B\\-\u00D7\u00F7]")) {
                    equation.setText(text + "(");
                    return;
                }

                equation.setText(text + ")");
            } else {
                equation.setText("(");
            }
        });

        squareRoot.addActionListener(e -> {
            String text = equation.getText();
            equation.setText(text + "\u221A(");
        });

        powerTwo.addActionListener(e -> {
            String text = equation.getText();
            equation.setText(text + "^(2)");
        });

        powerY.addActionListener(e -> {
            String text = equation.getText();
            equation.setText(text + "^(");
        });

        plusMinus.addActionListener(e -> {
            String text = equation.getText();

            if (text.matches(".*\\(-\\d*\\)?")) {
                text = replaceLast(text, "\\(-", "");
                if (text.endsWith("\\)")) {
                    text = text.substring(0, text.length() - 1);
                }
                equation.setText(text);
                return;
            }

            equation.setText("(-" + text);
        });

        // todo - make a magic constant of all operators in one string to use as a regex

        // operator buttons -- cannot add operator as first button
        divide.addActionListener(e -> {
            String text = equation.getText();

            if (text.matches("\\.\\d+")) {
                text = "0" + text;
            }

            if (text.matches(".\\.")) {
                text = text + "0";
            }

            if (text.length() != 0) {
                if (text.substring(text.length() - 1).matches("[\\u002B\\-\\u00D7\\u00F7]")) {
                    equation.setText(text.substring(0, text.length() - 1) + "÷");
                    return;
                }
                equation.setText(text + "\u00F7");
            }


        });
        multiply.addActionListener(e -> {
            String text = equation.getText();
            if (text.length() != 0) {

                if (text.matches("\\.\\d+")) {
                    text = "0" + text;
                }

                if (text.matches(".\\.")) {
                    text = text + "0";
                }

                if (text.substring(text.length() - 1).matches("[\\u002B\\-\\u00D7\\u00F7]")) {
                    equation.setText(text.substring(0, text.length() - 1) + "\u00D7");
                    return;
                }
                equation.setText(text + "\u00D7");
            }
        });

        plus.addActionListener(e -> {
            String text = equation.getText();
            if (text.length() != 0) {

                if (text.matches("\\.\\d+")) {
                    text = "0" + text;
                }

                if (text.matches(".\\.")) {
                    text = text + "0";
                }

                if (text.substring(text.length() - 1).matches(operators)) {
                    equation.setText(text.substring(0, text.length() - 1) + "\u002B");
                    return;
                }
                equation.setText(text + "\u002B");
            }
        });

        minus.addActionListener(e -> {
            String text = equation.getText();
            if (equation.getText().length() != 0) {

                if (text.matches("\\.\\d+")) {
                    text = "0" + text;
                }

                if (text.matches(".\\.")) {
                    text = text + "0";
                }

                if (text.substring(text.length() - 1).matches(operators)) {
                    equation.setText(text.substring(0, text.length() - 1) + "-");
                    return;
                }

                equation.setText(text + "-");
            }
        });

        clear.addActionListener(e -> {
            equation.setText("");
            resultLabel.setText("");
            map.clear();
        });

        dot.addActionListener(e -> {
            String text = equation.getText();
            equation.setText(text + ".");
        });

        delete.addActionListener(e -> {
            String text = equation.getText();
            if (text.length() > 0) {
                equation.setText(text.substring(0, text.length() - 1));
            }
        });

        // implements solve functionality
        equals.addActionListener(e -> {
            String s = equation.getText().trim();

            if (s.matches(".*[\\u002B\\-\\u00D7\\u00F7]$") || s.contains("\u00F70")) {
                equation.setForeground(Color.RED.darker());
                return;
            }


            System.out.println(s);
            if (s.length() > 0) {
                try {
                    String postFixedInput = infixToPostfix(s);

                    System.out.println("Postfix Notation: " + postFixedInput);

                    double result = evaluatePostfixExpression(postFixedInput);
                    String resultString = String.valueOf(result);

                    if (resultString.matches("-?\\d+\\.0*")) {
                        resultString = resultString.substring(0, resultString.indexOf('.'));
                    }

                    if (resultString.matches("\\.\\d+")) {
                        resultString = "0" + resultString;
                    }

                    // truncate results to fit on layout
                    if (resultString.length() > 13) {
                        resultString = resultString.substring(0, 13);
                    }
                    equation.setForeground(Color.GREEN.brighter());
                    resultLabel.setText(resultString);
                    resultLabel.revalidate();
                } catch (Exception exception) {
                    equation.setForeground(Color.red.darker());
                    exception.printStackTrace();
                }
            }
        });


        setVisible(true);

    }


    static String infixToPostfix(String input) {

        Stack<String> stack = new Stack<>();
        List<String> elements = new ArrayList<>();


        String fixedInput = input.replaceAll("-?\\d+\\.?\\d*", "$0 ");
        System.out.println("Fixed input: " + fixedInput);
        fixedInput = fixedInput.replaceAll("x", "*");
        System.out.println("Fixed input: " + fixedInput);
        //if (fixedInput)
        fixedInput = fixedInput.replaceAll("[+/*^]", "$0 ");
        System.out.println("Fixed input: " + fixedInput);

        fixedInput = fixedInput.replaceAll("[^(]-","$0 ");

        fixedInput = fixedInput.replaceAll("[\\u002B\\u00D7\\u00F7\u221A^]", "$0 ");

        System.out.println("Fixed input: " + fixedInput);

        input = tokenize(fixedInput);
        System.out.println("tokenized: " + input);
        String[] split = input.split(" ");

        for (String s : split) {

            if (precedence(s) > 0) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(s)) {
                    elements.add(stack.pop());
                }
                stack.push(s);
            } else if (s.equals(")")) {
                String popped = stack.pop();
                while (!popped.equals("(")) {
                    elements.add(popped);
                    popped = stack.pop();
                }
            } else if (s.equals("(")) {
                stack.push(s);
            } else {
                elements.add(s);
            }
        }


        while (!stack.isEmpty()) {
            elements.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        sb.append(elements.get(0));
        for (int i = 1; i < elements.size(); i++) {
            sb.append(" ").append(elements.get(i));
        }
        return sb.toString();
    }

    //todo clean up main() by moving some/all of input validation to this function
    static void validateInputs() {
    }
    
    static String tokenize(String input) {

        System.out.println("input: " + input);
        Scanner s = new Scanner(input);
        Pattern p = Pattern.compile("[+\\-\\u00D7\\u00F7()^]");
        Pattern variable = Pattern.compile("[a-zA-Z]+");
        Pattern parentheses = Pattern.compile("(.+)");

        List<String> list = new ArrayList<>();
        while (s.hasNextDouble() || s.hasNext(p) || s.hasNext(variable) || s.hasNext(parentheses)) {
            if (s.hasNext(parentheses)) {
                list.add(s.next(parentheses));
            } else if (s.hasNextDouble()) {
                list.add(String.valueOf(s.nextDouble()));
            } else if (s.hasNext(p)) {
                list.add(s.next(p));
            } else if (s.hasNext(variable)) {
                list.add(s.next(variable));
            }

        }

        StringBuilder sb = new StringBuilder(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            sb.append(" ").append(list.get(i));
        }
        String fixedString = sb.toString();
        fixedString = fixedString.replaceAll("\\(", "( ").replaceAll("\\)", ") ");
        return fixedString;
    }


    static int precedence(String c) {
        switch (c) {
            case "+":
            case "-":
                return 1;
            case "\u00D7": //multiplication
            case "\u00F7": //division
                return 2;
            case "√":
            case "^":
                return 3;
        }
        return -1;
    }


    static double evaluatePostfixExpression(String expression) throws Exception {
        // todo switch from INTEGER to DOUBLE
        Deque<Double> stack = new ArrayDeque<>();
        //expression = tokenize(expression);
        expression = expression.replaceAll("  ", " ");
        String[] postfix = expression.split(" ");

        System.out.println("Postfix array: " + Arrays.toString(postfix));



        if (postfix.length <= 2) {
            System.out.println("Invalid expression");
            throw new Exception();
        }

        for (int i = 0; i < postfix.length; i++) {
            String e = postfix[i];

            if (e.matches("-?[\\d]+\\.?\\d*")) {
                stack.push(Double.parseDouble(e));
                continue;
            }

            if (e.matches("[a-zA-Z]+")) {
                stack.push(Double.parseDouble(map.get(e)));
                continue;
            }

            if (e.matches("[+\\-\\u00D7\\u00F7^]")) {
                double num1 = stack.pop();
                System.out.println("num1: " + num1);
                double num2 = stack.pop();

                System.out.println("num2: " + num2);
                double result;
                switch (e) {
                    case "+":
                        result = num1 + num2;
                        stack.push(result);
                        break;
                    case "-":
                        result = num2 - num1;
                        stack.push(result);
                        break;
                    case "\u00D7":           // multiplication
                        result = num1 * num2;
                        stack.push(result);
                        break;
                    case "\u00F7":           // division
                        result = num2 / num1;
                        stack.push(result);
                        break;
                    case "^":
                        result = Math.pow(num2, num1);
                        stack.push(result);
                }
                continue;
            }
            if (e.matches("\u221A")) {
                double num = stack.pop();
                stack.push(Math.sqrt(num));
            }

        }
        return stack.pop();
    }

    // thank you stackoverflow
    public static String replaceLast(String input, String regex, String replacement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            return input;
        }
        int lastMatchStart=0;
        do {
            lastMatchStart=matcher.start();
        } while (matcher.find());
        matcher.find(lastMatchStart);
        StringBuffer sb = new StringBuffer(input.length());
        matcher.appendReplacement(sb, replacement);
        matcher.appendTail(sb);
        return sb.toString();
    }
}
