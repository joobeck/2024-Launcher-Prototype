package frc.robot;

public class Launcher {
    private CimMotor topLeftMotor = new CimMotor(1, true);
    private CimMotor bottomLeftMotor = new CimMotor(2, true);
    private CimMotor topRightMotor = new CimMotor(3);
    private CimMotor bottomRightMotor = new CimMotor(4);

    public Launcher() {
    }
    public void periodicUpdate() {
        topLeftMotor.updateDashboard("topLeftMotor");
        bottomLeftMotor.updateDashboard("bottomLeftMotor");
        topRightMotor.updateDashboard("topRightMotor");
        bottomRightMotor.updateDashboard("bottomRightMotor");
    }
}
