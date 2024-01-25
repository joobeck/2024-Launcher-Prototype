// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Angle multiplyer 

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;



/** Add your docs here. */
public class WheelDrive {
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    private RelativeEncoder angleEncoder;

    private SparkMaxPIDController m_pidController;
    private DutyCycleEncoder absoluteEncoder;
    


    // PID coefficients
    public double kP = 0.005;
    public double kI = 0;
    public double kD = 0;
    public double kIz = 0;
    public double kFF = 0;
    public double kMaxOutput = 1;
    public double kMinOutput = -1;
    public double PositionConversionFactor = 1;

    // Drive method 
    double setPointAngle;
    double setPointAngleFlipped;
    double currentAngle;
   
    
    //consider changing variable names
    public WheelDrive (int angleMotor, int speedMotor, int encoderNumber) {
        
        this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
        this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

        this.speedMotor.setClosedLoopRampRate(2);
        this.angleMotor.setClosedLoopRampRate(0.3);    
       
        m_pidController = this.angleMotor.getPIDController();

        this.angleEncoder = this.angleMotor.getEncoder();
        this.angleEncoder.setPositionConversionFactor((7.0/96) * 360);
        this.absoluteEncoder = new DutyCycleEncoder(encoderNumber);

        // set PID coefficients
        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setIZone(kIz);
        m_pidController.setFF(kFF);
        m_pidController.setOutputRange(kMinOutput, kMaxOutput);
        
        m_pidController.setPositionPIDWrappingEnabled(true);
        m_pidController.setPositionPIDWrappingMaxInput(180);
        m_pidController.setPositionPIDWrappingMinInput(-180);

       
        //m_pidController.setFeedbackDevice(null);
        //m_pidController.set

        /**this.pidController.setPID(angleMotor, speedMotor, angleEncoder);
        this.pidController.atSetpoint();
        this.pidController.setSetpoint(angle);
        */
                
        //this.angleMotor.getEncoder();        

    }

    // Subtracts two angles
    public double angleSubtractor (double firstAngle, double secondAngle) {
       // 
        double sign = Math.signum(firstAngle -secondAngle);
        double result = (((Math.abs(firstAngle - secondAngle)) + 180)%360) - 180;
        result = result * sign;
        return result;

    }

    public void drive (double speed, double angle){
        currentAngle = angleEncoder.getPosition();
        speed = speed * 0.5;

        setPointAngle = angleSubtractor(currentAngle, angle);
        setPointAngleFlipped = angleSubtractor(currentAngle + 180, angle);

        //m_pidController.setReference(angle, CANSparkMax.ControlType.kPosition);
        //speedMotor.set(speed);

        if (Math.abs(setPointAngle) < Math.abs(setPointAngleFlipped)){
        
            if (Math.abs(speed) < 0.1){
                m_pidController.setReference(currentAngle, CANSparkMax.ControlType.kPosition);
                speedMotor.set(speed);
            } else {
                m_pidController.setReference(angleSubtractor(currentAngle, setPointAngle), CANSparkMax.ControlType.kPosition);
                speedMotor.set(speed);
            }
        } else {
            if (Math.abs(speed) < 0.1){
                m_pidController.setReference(currentAngle, CANSparkMax.ControlType.kPosition);
                speedMotor.set(-1 * speed);
            } else {
                m_pidController.setReference(angleSubtractor(currentAngle, setPointAngleFlipped), CANSparkMax.ControlType.kPosition);
                speedMotor.set(-1 * speed);

            }
        }
        
    }

    public void zeroEncoders(double offset){
        angleEncoder.setPosition(-1 * (absoluteEncoder.getAbsolutePosition() * 360 - offset));  
    }   

    public double returnRelative(){
        return angleEncoder.getPosition()%360;
    }

    public double returnsetPointAngle(){
        return setPointAngle;
    }
    public double returnsetPointAngleFlipped(){
        return setPointAngleFlipped;
    }
     public double returncurrentAngle(){
        return currentAngle;
     }
    public double returnabsolute(){
        return absoluteEncoder.getAbsolutePosition(); 
    }

    public void invertDriveMotor(boolean isInverted){
        speedMotor.setInverted(isInverted);
    }
    public void invertAngleMotor(boolean isInverted){
        angleMotor.setInverted(isInverted);
    }
}
