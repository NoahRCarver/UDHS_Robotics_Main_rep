

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
    boolean encodersEnabled = false;
    private final double frontRatio = 1.00;
    private final double frontCirc = 0;
    private final double backCirc = 0;
    private final int pulsePerRot = 1440;

    /**
     * Constructor:
     * @params: FrontRight, backRight, frontLeft, backLeft: motors, must be initialized and bound
     * before this can be constructed
     */
    public BaseDriveMotorControl(DcMotor frontRight, DcMotor backRight, DcMotor frontLeft, DcMotor backLeft) {
        
        rightMotorf = frontRight;
        rightMotorb = backRight;
        leftMotorf = frontLeft;
        leftMotorb = backLeft;
        
        rightMotorf.setDirection(DcMotor.Direction.REVERSE);
        rightMotorb.setDirection(DcMotor.Direction.REVERSE);
       
    }
    
    /**
     * if val is true, enables encoders, else disables them
     * 
     */
    public void enableEncoders(boolean val){
        if(val){
            leftMotorf.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            leftMotorb.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            rightMotorf.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            rightMotorb.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            encodersEnabled = true;
        }else{
           leftMotorf.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
           leftMotorb.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
           rightMotorf.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
           rightMotorb.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
           encodersEnabled = false;
        }
    }
    
    
    
    /**
     * This method does all of the things required to make each side of 
     * the robot move forward by the given amounts. 
     * Seriously, It changes the channelMode, It calculates the pulse counts
     * Everything!
     * If you understand java and can't use this, you don't deserve to!
     */
    public void measuredDrive(double Rdist, double Ldist){
        
        int frrot = Rdist/frontCirc;
        int brrot = Rdist/backCirc;
        int flrot = Ldist/frontCirc;
        int blrot = Ldist/backcirc;
        
        leftMotorf.setTargetPosition(flrot*pulsePerRot);
        leftMotorb.setTargetPosition(blrot*pulsePerRot);
        rightMotorf.setTargetPosition(frrot*pulsePerRot);
        rightMotorb.setTargetPosition(brrot*pulsePerRot);
        
        if(!encodersEnabled){
         enableEncoders(true);   
        }
        
        int rPow = 1, lPow = 1;
        if(Rdist<0){
            rPow = -1;
        }else if(Rdist == o){
            rPow = 0;
        }
        if(Ldist<0){
            lPow = -1;
        }else if(Ldist == o){
            lPow = 0;
        }
        
        leftMotorf.setPower(frontRatio * lPow);
        leftMotorb.setPower(lPow);
        rightMotorf.setPower(frontRatio * rPow);
        rightMotorb.setPower(rPow);
    }
    
    
    /**
     * This method just sets the power of the Left and right motors
     * to the provided values
     * It also scales the power to the front motors.
     */
    public void tankDrive (double leftVal, double rightVal){
         
        if(encodersEnabled){
            enableEncoders(false);
        }
        
        leftMotorf.setPower(frontRatio * leftVal);
        leftMotorb.setPower(leftVal);
        rightMotorf.setPower(frontRatio * rightVal);
        rightMotorb.setPower(rightVal);
    }
    
    /**
     * This method automatically collects data from the Gamepad and 
     * scales it for tank drive
     * To use, just put drivecontroller.tankDrive(gamepad1); into the 
     * loop function of your tele-op
     */
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
        int mult = 6;
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