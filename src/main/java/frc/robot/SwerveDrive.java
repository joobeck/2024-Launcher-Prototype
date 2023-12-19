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
        y1 = y1* -1;

        double a = x1 - x2 * (length / rotation);
        double b = x1 + x2 * (length / rotation);
        double c = y1 - x2 * (width / rotation);
        double d = y1 + x2 * (width / rotation);

        double backRightSpeed = Math.sqrt ((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt ((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt ((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt ((b * b) + (c * c));

        double backRightAngle = 180 * (Math.atan2(a, d) / Math.PI);
        double backLeftAngle = 180 * (Math.atan2(a, c) / Math.PI);
        double frontRightAngle = 180 * (Math.atan2(b, d) / Math.PI);
        double frontLeftAngle = 180 * (Math.atan2(b, c) / Math.PI);
        //
        backRight.drive(backRightSpeed, backRightAngle);
        backLeft.drive(backLeftSpeed, backLeftAngle);
        frontRight.drive(frontRightSpeed, frontRightAngle);
        frontLeft.drive(frontLeftSpeed, frontLeftAngle);
    }
    
}
