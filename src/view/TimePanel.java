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

    private final JLabel TIMER_LABEL;
    private Timer currentTimer = new Timer();

    private GameController gameController;

    TimePanel() {

        JLabel turnTimerLabel = new JLabel("Turn Timer");
        turnTimerLabel.setPreferredSize(new Dimension(150, 20));
        turnTimerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        turnTimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnTimerLabel);

        TIMER_LABEL = new JLabel();
        TIMER_LABEL.setPreferredSize(new Dimension(150, 20));
        TIMER_LABEL.setFont(new Font("Arial", Font.PLAIN, 18));
        TIMER_LABEL.setHorizontalAlignment(SwingConstants.CENTER);
        add(TIMER_LABEL);

        createNewTimer(currentTimer);

    }

    private void createNewTimer(Timer currentTimer) {
        setCountdownTimer(currentTimer);
    }

    private void setCountdownTimer(Timer currentTimer) {
        currentTimer.scheduleAtFixedRate(new TimerTask() {

            int turnTimerLimit = Integer.parseInt("30");

            public void run() {
                //timerLabel.setText(String.valueOf(turnTimerLimit--));

                if (turnTimerLimit < 0) {
                    gameController.nextTurn();
                }

            }
        }, 0, 1000);
    }

    public void resetTimer() {
        currentTimer.cancel();

        Timer newTimer = new Timer();
        setCountdownTimer(newTimer);

        currentTimer = newTimer;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

}