import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessGame extends JFrame {
    // 各個變數
    private int secretNumber; // 存目標數字
    private int attempts;     // 記錄猜測次數
    private JTextField guessField; // 玩家輸入猜測的輸入框
    private JTextArea infoArea;    // 顯示提示訊息的框框
    private int bestScore;  // 記錄最佳猜中次數

    public NumberGuessGame() {
        super("Guess-Number-Game"); // 應用程式標題

        // 初始化
        secretNumber = generateRandomNumber(); // 生成隨機目標數字
        attempts = 0;  // 初始化猜測次數
        bestScore = 0; // 初始化最佳猜中次數

        // 創建界面元素
        guessField = new JTextField(10); // 輸入猜測的輸入框
        JButton guessButton = new JButton("Guess"); // 猜測按鈕
        infoArea = new JTextArea(10, 30); // 顯示提示信息的框框
        infoArea.setEditable(false); // 設置這個區域不可編輯

        // 設置猜測按鈕的監聽器
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess(); // 當按鈕被點擊時執行 handleGuess 方法
            }
        });

        // 設置窗口布局
        setLayout(new BorderLayout());
        add(new JLabel("Enter your guess:"), BorderLayout.NORTH); // 加標籤
        add(guessField, BorderLayout.CENTER); // 加輸入框
        add(guessButton, BorderLayout.SOUTH); // 加按鈕
        add(new JScrollPane(infoArea), BorderLayout.EAST); // 加帶滾動條的區域

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設置關閉操作
        pack(); // 調整視窗大小以適應內容
        setLocationRelativeTo(null); // 將窗口置中
        setVisible(true); // 顯示窗口
    }

    // 生成隨機數字
    private int generateRandomNumber() {
        Random random = new Random();
        int lowerBound = 1;
        int upperBound = 100;
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    // 處理猜測
    private void handleGuess() {
        int guess = Integer.parseInt(guessField.getText()); // 從輸入框中獲取猜測
        attempts++; // 增加猜測次數

        // 判斷猜測結果
        if (Math.abs(guess - secretNumber) <= 1) {
            // 玩家猜中的數字與目標數字相差1以內
            infoArea.append("Congratulations! You've guessed the number in " + attempts + " attempts.\n");
            if (bestScore < attempts) {
                bestScore = attempts; // 更新最佳猜中次數
            }
            guessField.setEnabled(false); // 禁用輸入框
        } else if (guess < secretNumber) {
            infoArea.append("Attempt #" + attempts + ": Too low! Try again.\n");
        } else {
            infoArea.append("Attempt #" + attempts + ": Too high! Try again.\n");
        }

        guessField.setText(""); // 清空輸入框
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessGame(); // 創建遊戲界面
            }
        });
    }
}