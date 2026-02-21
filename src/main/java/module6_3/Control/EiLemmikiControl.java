package module6_3.Control;

import javafx.animation.AnimationTimer;

import module6_3.Model.EiLemmkiModel;
import module6_3.View.EiLemmikiView;

public class EiLemmikiControl {
    private EiLemmkiModel eilemkiModel;
    private EiLemmikiView eiLemmikiView;

    private double targetX;
    private double targetY;
    private double speed = 10.0;

    private boolean moving = false;

    public EiLemmikiControl(EiLemmikiView view) {
        this.eiLemmikiView = view;
        this.eilemkiModel = new EiLemmkiModel(200, 200); //start position
    }

    public double getEiLemmikiX() {
        return eilemkiModel.getX();
    }
    public double getEiLemmikiY() {
        return eilemkiModel.getY();
    }

    public void setTarget(double x, double y) {
        this.targetX = x;
        this.targetY = y;
        moving = true;
        animationTimer.start();
    }

    public void stopMooving() {
        moving = false;
    }

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (moving) {
                moveTowardsTarget();
            }
        }
    };

    private void moveTowardsTarget() {
        double eiLemmikiX = eilemkiModel.getX();
        double eiLemmikiY = eilemkiModel.getY();

        double dx = targetX  - eiLemmikiX;
        double dy = targetY - eiLemmikiY;

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= speed) {
            eilemkiModel.setPosition(targetX, targetY);
            moving = false;
            eiLemmikiView.drawEiLemmiki();
            return;
        }

        double directionX = dx / distance;
        double directionY = dy / distance;

        double newX = eiLemmikiX + directionX * speed;
        double newY = eiLemmikiY + directionY  * speed;

        eilemkiModel.setPosition(newX, newY);
        eiLemmikiView.drawEiLemmiki();

    }
}
