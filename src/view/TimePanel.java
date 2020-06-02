package view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

class TimePanel
        extends JPanel {

    private GameView gameView;
    private Timer currentTimer;
    private final JLabel timerLabel;
    private boolean eagleTurn;
    private String turnLimit;
    private int turnTime;

    TimePanel(GameView gameView) {

        this.gameView = gameView;
        JLabel turnTimerLabel = new JLabel("Turn Timer:");
        turnTimerLabel.setPreferredSize(new Dimension(100, 20));
        turnTimerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(turnTimerLabel);

        timerLabel = new JLabel();
        timerLabel.setPreferredSize(new Dimension(20, 20));
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
                    TemplateFrame frame = new TemplateFrame();
                    gameView.dispose();

                    if (eagleTurn) {
                        frame.showEndView( "Shark");
                    } else {
                        frame.showEndView( "Eagle");
                    }
//                    System.out.println(eagleTurn ? StringText.SHARK_WON : StringText.EAGLE_WON);
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

    String getTurnLimit() {
        return turnLimit;
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