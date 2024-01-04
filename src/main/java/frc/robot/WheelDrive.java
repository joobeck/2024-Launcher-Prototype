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
import edu.wpi.first.wpilibj.AnalogEncoder;
/* import com.ctre.phoenix.sensors.CANCoder;

import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.CANCoderStatusFrame;*/

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;


/** Add your docs here. */
public class WheelDrive {
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    private RelativeEncoder angleEncoder;

    private SparkMaxPIDController m_pidController;
    private DutyCycleEncoder driveEncoder;
    private AnalogEncoder absoluteEncoder;


    // PID coefficients
    public double kP = 0.1;
    public double kI = 1;
    public double kD = 1;
    public double kIz = 0;
    public double kFF = 0;
    public double kMaxOutput = 1;
    public double kMinOutput = -1;
    public double PositionConversionFactor = 1;
   
    
    //consider changing variable names
    public WheelDrive (int angleMotor, int speedMotor, int encoderNumber) {
        
        this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
        this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);
       
        m_pidController = this.angleMotor.getPIDController();

        this.angleEncoder = this.angleMotor.getEncoder();
        this.angleEncoder.setPositionConversionFactor(7.0/96);
        //this.absoluteEncoder = new AnalogEncoder(encoderNumber);
        this.driveEncoder = new DutyCycleEncoder(encoderNumber);

        // set PID coefficients
        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setIZone(kIz);
        m_pidController.setFF(kFF);
        m_pidController.setOutputRange(kMinOutput, kMaxOutput);
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
    public void drive (double speed, double angle){
        // Sets the speed on the speed motor.
        speedMotor.set(speed);
        angle = angle *1;
        m_pidController.setReference(angle, CANSparkMax.ControlType.kPosition);


    }

    public void zeroEncoders(){
        // private final FeedbackDevice absoluteEncoder = new FeedbackD
        
        //angleEncoder.setPosition(absoluteEncoder.getAbsolutePosition());
        angleEncoder.setPosition(driveEncoder.getAbsolutePosition());
    }   
    
    public double returnRelative(){
        return angleEncoder.getPosition();
    }

    public double returnabsolute(){
        return driveEncoder.getAbsolutePosition();
        //return absoluteEncoder.getAbsolutePosition();
        
    }

}
