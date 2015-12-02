

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class NXTTankOp extends OpMode {

    DcMotor FRM, BRM;
    DcMotor FLM, BLM;
    BaseDriveMotorControl driveScheme;

    public NXTTankOp() {
        
    }


    @Override
    public void init(){
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

    }
    
    @Override
    public void start(){}

    @Override
    public void init_loop(){}

    @Override
    public void loop() {
        driveScheme.tankDrive(gamepad1);
    }

    @Override
    public void stop() {

    }
}