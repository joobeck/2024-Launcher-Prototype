package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Launcher {
    private CimMotor topLeftMotor = new CimMotor(1, true);
    private CimMotor bottomLeftMotor = new CimMotor(2, true);
    private CimMotor topRightMotor = new CimMotor(3);
    private CimMotor bottomRightMotor = new CimMotor(4);

    private final SendableChooser<String> chooser = new SendableChooser<>();

    private static final String kFullStop = "Full Stop";
    private static final String kRotate = "Rotate";
    private static final String kFullLaunch = "Full Launch";
    private static final String kRollingLaunch = "Rolling Launch";

    public Launcher() {
    }

    public void rotateNote() {
        topLeftMotor.fullReverse();
        bottomLeftMotor.fullReverse();
        topRightMotor.fullForward();
        bottomRightMotor.fullForward();
    }

    public void fullLaunch() {
        topLeftMotor.fullForward();
        bottomLeftMotor.fullForward();
        topRightMotor.fullForward();
        bottomRightMotor.fullForward();
    }

    public void rollingLaunch() {
        topLeftMotor.stop();
        bottomLeftMotor.stop();
        topRightMotor.fullForward();
        bottomRightMotor.fullForward();
    }

    public void stopAll() {
        topLeftMotor.stop();
        bottomLeftMotor.stop();
        topRightMotor.stop();
        bottomRightMotor.stop();
    }

    void addDashboardDisplays() {
        chooser.setDefaultOption(kFullStop, kFullStop);
        chooser.addOption(kRotate, kRotate);
        chooser.addOption(kFullLaunch, kFullLaunch);
        chooser.addOption(kRollingLaunch, kRollingLaunch);
        SmartDashboard.putData("Launcher Options", chooser);
    }

    public void periodicUpdate() {
        topLeftMotor.updateDashboard("topLeftMotor");
        bottomLeftMotor.updateDashboard("bottomLeftMotor");
        topRightMotor.updateDashboard("topRightMotor");
        bottomRightMotor.updateDashboard("bottomRightMotor");

        switch (chooser.getSelected()) {
            case kRotate:
                rotateNote();
                break;
            case kFullLaunch:
                fullLaunch();
                break;
            case kRollingLaunch:
                rollingLaunch();
                break;
            case kFullStop:
            default:
                stopAll();
                break;
        }
    }
}
