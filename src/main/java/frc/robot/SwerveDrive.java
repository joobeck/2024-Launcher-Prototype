// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// convert angle here to angle elsewhere
// check SparkMax CAN numbers

/** Add your docs here. */
public class SwerveDrive {
    private WheelDrive backRight;
    private WheelDrive backLeft;
    private WheelDrive frontRight;
    private WheelDrive frontLeft;
    //dimensions between wheels center-to-center
    public final double length = 27.4;
    public final double width = 12.25;

    // SwerveDrive constructor
    public SwerveDrive (WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft){
        this.backRight = backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
    }
    // drive method
    public void drive (double x1, double y1, double x2) {
        double rotation = Math.sqrt((length * length) + (width * width));
        y1 *= -1;
        

        double a = x1 - x2 * (length / rotation); //back horizontal
        double b = x1 + x2 * (length / rotation); //front horizontal
        double c = y1 - x2 * (width / rotation);  //left vertical
        double d = y1 + x2 * (width / rotation);  //right vertical

        /*We switched left and right(we could also have switched front and back)
        * this change should turn the wheels the right way when the robot is trying to rotate
        */
        //Speed Values
        double backRightSpeed = Math.sqrt ((a * a) + (c * c));
        double backLeftSpeed = Math.sqrt ((a * a) + (d * d));
        double frontRightSpeed = Math.sqrt ((b * b) + (c * c));
        double frontLeftSpeed = Math.sqrt ((b * b) + (d * d));
        //Angle Values
        double backRightAngle = (Math.atan2(a, c) / Math.PI / 2); 
        double backLeftAngle = (Math.atan2(a, d) / Math.PI / 2);
        double frontRightAngle = (Math.atan2(b, c) / Math.PI / 2);
        double frontLeftAngle = (Math.atan2(b, d) / Math.PI / 2);
        


        backRight.drive(backRightSpeed, backRightAngle);
        backLeft.drive(backLeftSpeed, backLeftAngle);
        frontRight.drive(frontRightSpeed, frontRightAngle);
        frontLeft.drive(frontLeftSpeed, frontLeftAngle);
    }
    
}

