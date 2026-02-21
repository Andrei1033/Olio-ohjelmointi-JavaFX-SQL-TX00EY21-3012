package module6_3.Model;

public class EiLemmkiModel {
    private double x;
    private double y;

    public  EiLemmkiModel(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
