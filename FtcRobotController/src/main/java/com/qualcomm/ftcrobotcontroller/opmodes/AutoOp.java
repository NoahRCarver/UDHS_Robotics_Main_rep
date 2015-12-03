

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * AutoOpMode
 * 
 * drives robot in a square
 */
public class AutoOp extends LinearOpMode {
    
    DcMotor FRM, BRM;
    DcMotor FLM, BLM;
    BaseDriveMotorControl driveScheme;

    public AutoOp() {}
    
    @Override
    public void runOpMode() throws InterruptedException{
       /**
        * Hardware Map:
        * fr - front right drive motor
        * fl - front left drive motor
        * br - back right drive motor
        * bl - back left drive motor
        */
       FRM = hardwareMap.dcMotor.get("fr");
       BRM = hardwareMap.dcMotor.get("br");
       FLM = hardwareMap.dcMotor.get("fl");
       BLM = hardwareMap.dcMotor.get("bl");
       
       //init driveScheme
       driveScheme = new BaseDriveMotorControl(FRM, BRM, FLM, BLM);
        
        waitForStart();
        
        for(int i = 0; i < 4; i++){
            driveScheme.tankDrive(1, 1);
            sleep(1000);
            driveScheme.tankDrive(1, -1);
            sleep(1000);
        }
        
        driveScheme.tankDrive(0, 0);
        
    }
    

}