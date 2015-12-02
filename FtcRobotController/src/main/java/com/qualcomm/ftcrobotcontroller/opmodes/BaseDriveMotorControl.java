

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;


/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class BaseDriveMotorControl{

    DcMotor rightMotorf, rightMotorb;
    DcMotor leftMotorf, leftMotorb;
    private final double frontRatio = 1.00;

    /**
     * Puppet Constructor
     */
    public BaseDriveMotorControl(DcMotor frontRight, DcMotor backRight, DcMotor frontLeft, DcMotor backLeft) {
        rightMotorf = frontRight;
        rightMotorb = backRight;
        leftMotorf = frontLeft;
        leftMotorb = backLeft;
        
        rightMotorf.setDirection(DcMotor.Direction.REVERSE);
        rightMotorb.setDirection(DcMotor.Direction.REVERSE);
        
        leftMotorf.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        leftMotorb.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotorf.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotorb.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }
    
    
    public void tankDrive (double leftVal, double rightVal){
        leftMotorf.setPower(frontRatio * leftVal);
        leftMotorb.setPower(leftVal);
        rightMotorf.setPower(frontRatio * rightVal);
        rightMotorb.setPower(rightVal);
    }
    
    public void tankDrive (Gamepad gamepad){
        boolean boost = gamepad.left_bumper;
        tankDrive (scaleInput(gamepad.left_stick_y, boost), scaleInput(gamepad.right_stick_y, boost));
    }








    /*
     * This method scales the joystick input so for low joystick values, the 
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    private double scaleInput(double dVal, boolean boost)  {
        int mult = 12;
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
        if(boost){
            mult = 16;
        }


        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * mult);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}