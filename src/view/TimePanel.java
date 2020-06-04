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
    private String turnLimit;
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

    private void createNewTimer() {

        currentTimer = new Timer();

        setCountdownTimer(currentTimer);
    }

    private void setCountdownTimer(Timer currentTimer) {
        currentTimer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                timerLabel.setText(String.valueOf((turnTime--)));

                if (turnTime < 0) {
                    currentTimer.cancel();
                    GAME_VIEW.dispose();

                    TemplateFrame frame = new TemplateFrame();
                    frame.showEndView(eagleTurn ? StringText.SHARK : StringText.EAGLE);
                }
            }
        }, 0, 1000);
    }

    void resetTimer() {
        currentTimer.cancel();

        Timer newTimer = new Timer();
        setCountdownTimer(newTimer);

        currentTimer = newTimer;
        turnTime = Integer.parseInt(turnLimit);

    }

    int getTurnTime() {
        return turnTime;
    }

    void setEagleTurn(boolean eagleTurn) {
        this.eagleTurn = eagleTurn;
    }

    void setTurnLimit(String turnLimit) {
        this.turnLimit = turnLimit;
        turnTime = Integer.parseInt(turnLimit);

        createNewTimer();
    }

    void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }

}