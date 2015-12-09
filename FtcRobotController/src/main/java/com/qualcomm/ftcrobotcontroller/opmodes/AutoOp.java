

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * AutoOpMode
 * 
 * drives robot in a square
 */
public class AutoOp extends LinearOpMode {
    
    DcMotor FRM, BRM;
    DcMotor FLM, BLM;
    ColorSensor colorSensor;
    BaseDriveMotorControl driveScheme;

    public AutoOp() {/*who needs constructors?*/}
    
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
        colorSensor = hardwareMap.colorSensor.get("clr");
        
        //init driveScheme
        driveScheme = new BaseDriveMotorControl(FRM, BRM, FLM, BLM);
        
        
        //////////////// END INIT STEP /////////////////
        
        waitForStart();
        
        
        //telemetry.addData("Red  ", colorSensor.red());
        //telemetry.addData("Green", colorSensor.green());
        //telemetry.addData("Blue ", colorSensor.blue());
        
        for(int i = 0; i < 4; i++){
            driveScheme.tankDrive(1, 1);
            sleep(1000);
            driveScheme.tankDrive(1, -1);
            sleep(1000);
        }
        
        driveScheme.tankDrive(0, 0);
        
    }
    

}