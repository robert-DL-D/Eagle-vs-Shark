package view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

class TimePanel
        extends JPanel {

    private Timer currentTimer = new Timer();
    private final JLabel timerLabel;
    private boolean eagleTurn;
    private int turnTimerLimit = 30;

    TimePanel() {

        JLabel turnTimerLabel = new JLabel("Turn Timer:");
        turnTimerLabel.setPreferredSize(new Dimension(100, 20));
        turnTimerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(turnTimerLabel);

        timerLabel = new JLabel();
        timerLabel.setPreferredSize(new Dimension(20, 20));
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(timerLabel);

        createNewTimer(currentTimer);
    }

    private void createNewTimer(Timer currentTimer) {
        setCountdownTimer(currentTimer);
    }

    private void setCountdownTimer(Timer currentTimer) {
        currentTimer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                timerLabel.setText(String.valueOf((turnTimerLimit--)));

                if (turnTimerLimit < 0) {
                    currentTimer.cancel();
                    System.out.println(eagleTurn ? "Eagle Won" : "Shark Won");
                }

            }
        }, 0, 1000);
    }

    void resetTimer() {
        currentTimer.cancel();

        Timer newTimer = new Timer();
        setCountdownTimer(newTimer);

        currentTimer = newTimer;
    }

    void setEagleTurn(boolean eagleTurn) {
        this.eagleTurn = eagleTurn;
    }

}