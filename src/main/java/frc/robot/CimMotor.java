package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CimMotor {

    private CANSparkMax motor;

    public CimMotor(int id, boolean isInverted) {
        motor = new CANSparkMax(id, MotorType.kBrushed);
        motor.setInverted(isInverted);
    }

    public CimMotor(int id) {
        motor = new CANSparkMax(id, MotorType.kBrushed);
        motor.setInverted(false);
    }

    public void fullForward() {
        motor.set(1.0);
    }

    public void fullReverse() {
        motor.set(-1.0);
    }

    public void stop() {
        motor.set(0.0);
    }

    public void updateDashboard(String motorDescription) {
        SmartDashboard.putNumber(motorDescription.concat(" set speed"), motor.get());
    }
}
