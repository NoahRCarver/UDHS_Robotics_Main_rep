

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * AutoOpMode
 * 
 * drives robot in a square
 */
public class AutoOp extends LinearOpMode {
    
    public static final int driveTime = 1;
    public static final int turnTime = 1;

    DcMotor rightMotor1, rightMotor2;
    DcMotor leftMotor1, leftMotor2;
    ElapsedTime timer;
    
    Enum State {Start, Drive, Turn}
    State state;

    /**
     * Puppet Constructor
     */
    public NXTTankOp() {

    }
    
    
    @Override
    public void runOpMode() throws InterruptedException{
        leftMotor1 = hardwareMap.dcMotor.get("lmotor1");
        leftMotor2 = hardwareMap.dcMotor.get("lmotor2");
        rightMotor1 = hardwareMap.dcMotor.get("rmotor1");
        rightMotor2 = hardwareMap.dcMotor.get("rmotor2");
        
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        
        for(int i = 0; i < 4; i++){
            leftMotor1.setPower(1);
            leftMotor2.setPower(.5);
            rightMotor1.getPower(1);
            rightMotor2.setPower(.5);
            sleep(1000);
            leftMotor1.setPower(-1);
            leftMotor2.setPower(-.5);
            rightMotor1.getPower(1);
            rightMotor2.setPower(.5);
            sleep(1000);
        }
        
        leftMotor1.setPowerFloat();
        leftMotor2.setPowerFloat();
        rightMotor1.getPowerFloat();
        rightMotor2.setPowerFloat();
        
    }
    

}