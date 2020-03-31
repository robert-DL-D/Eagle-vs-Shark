package view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.GameController;

public class TimePanel
        extends JPanel {

    private final JLabel timerLabel;
    private Timer currentTimer = new Timer();

    private GameController gameController;

    TimePanel() {

        setSize(100, 50);

        JLabel turnTimerLabel = new JLabel("Turn Timer");
        turnTimerLabel.setPreferredSize(new Dimension(150, 20));
        turnTimerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        turnTimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnTimerLabel);

        timerLabel = new JLabel();
        timerLabel.setPreferredSize(new Dimension(150, 20));
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timerLabel);

        createNewTimer(currentTimer);

    }

    private void createNewTimer(Timer currentTimer) {
        timer(currentTimer);
    }

    private void timer(Timer currentTimer) {
        currentTimer.scheduleAtFixedRate(new TimerTask() {

            int turnTimerLimit = Integer.parseInt("30");

            public void run() {
                timerLabel.setText(String.valueOf(turnTimerLimit--));

                if (turnTimerLimit < 0) {
                    gameController.nextTurn();
                }

            }
        }, 0, 1000);
    }

    public void resetTimer() {
        currentTimer.cancel();

        Timer newTimer = new Timer();
        timer(newTimer);

        currentTimer = newTimer;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

}