package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
}
