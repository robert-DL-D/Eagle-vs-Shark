package view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.StringText;

class TimePanel
        extends JPanel {

    private Timer currentTimer = new Timer();
    private final JLabel timerLabel;
    private boolean eagleTurn;
    private static final String TURN_TIMER_LIMIT = "30";
    private int turnTime;

    TimePanel() {

        JLabel turnTimerLabel = new JLabel("Turn Timer:");
        turnTimerLabel.setPreferredSize(new Dimension(100, 20));
        turnTimerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(turnTimerLabel);

        timerLabel = new JLabel(TURN_TIMER_LIMIT);
        turnTime = Integer.parseInt(TURN_TIMER_LIMIT);
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
                timerLabel.setText(String.valueOf((turnTime--)));

                if (turnTime < 0) {
                    currentTimer.cancel();
                    System.out.println(!eagleTurn ? StringText.EAGLE_WON : StringText.SHARK_WON);
                }

            }
        }, 0, 1000);
    }

    void resetTimer() {
        currentTimer.cancel();

        Timer newTimer = new Timer();
        setCountdownTimer(newTimer);

        currentTimer = newTimer;
        turnTime = Integer.parseInt(TURN_TIMER_LIMIT);

    }

    int getTurnTime() {
        return turnTime;
    }

    void setEagleTurn(boolean eagleTurn) {
        this.eagleTurn = eagleTurn;
    }

    void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }
}