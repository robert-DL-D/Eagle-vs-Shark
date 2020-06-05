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

    private final GameView GAME_VIEW;
    private Timer currentTimer;
    private final JLabel timerLabel;
    private boolean eagleTurn;
    private int turnLimit;
    private int turnTime;

    TimePanel(GameView gameView) {
        GAME_VIEW = gameView;

        JLabel turnTimerLabel = new JLabel("Turn Timer:");
        turnTimerLabel.setPreferredSize(new Dimension(100, 20));
        turnTimerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(turnTimerLabel);

        timerLabel = new JLabel();
        timerLabel.setPreferredSize(new Dimension(40, 20));
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(timerLabel);

    }

    void createNewTimer(int turnTime) {
        if (currentTimer != null) {
            currentTimer.cancel();
        }

        Timer newTimer = new Timer();
        setCountdownTimer(newTimer);

        currentTimer = newTimer;
        this.turnTime = turnTime;
    }

    private void setCountdownTimer(Timer currentTimer) {
        currentTimer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                timerLabel.setText(String.valueOf(turnTime--));

                if (turnTime < 0) {
                    currentTimer.cancel();
                    GAME_VIEW.dispose();

                    new TemplateFrame().showEndView(eagleTurn ? StringText.SHARK : StringText.EAGLE);
                }
            }
        }, 0, 1000);
    }

    int getTurnTime() {
        return turnTime;
    }

    void setEagleTurn(boolean eagleTurn) {
        this.eagleTurn = eagleTurn;
    }

    void setTurnLimit(int turnLimit) {
        this.turnLimit = turnLimit;
        turnTime = turnLimit;

        createNewTimer(turnTime);
    }

    void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }

}