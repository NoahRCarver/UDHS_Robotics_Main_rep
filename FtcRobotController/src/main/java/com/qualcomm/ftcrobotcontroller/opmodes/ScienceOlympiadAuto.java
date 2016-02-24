package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
import java.lang.*;


public class ScienceOlympiadAuto extends OpMode {
    mode runmode = mode.INIT;
    DcMotor left, right;
    int encoderCPR=1440;
    double gearRatio= 0.333;
    int wheelDiameter = 13;
    double distance = 1;
    double circumference;
    double rotations;
    double counts = 0;
    boolean isBeingPressed = false;

    public ScienceOlympiadAuto(){}

    @Override
    public void init() {
        left = hardwareMap.dcMotor.get("left"); //port 1
        right = hardwareMap.dcMotor.get("right"); //port 2
        right.setDirection(DcMotor.Direction.REVERSE);
        left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void start() {


    }
    public void loop(){


        switch(runmode){
            case INIT:

                   if (gamepad1.dpad_up) { //button 1

                       if (!isBeingPressed) {
                           distance += .1;
                           isBeingPressed = true;
                       }
                   } else {
                       isBeingPressed = false;
                   }

                   if (gamepad1.dpad_down) { //button 4
                       if (!isBeingPressed) {
                           distance -= .1;
                           isBeingPressed = true;
                       }
                   } else {
                       isBeingPressed = false;
                   }
                   if (gamepad1.x) { //button 2
                       runmode = mode.MOVE;
                   }

                   telemetry.addData("distance", distance);


                break;
            case MOVE:

                circumference = Math.PI * wheelDiameter;
                rotations = distance*100/circumference;
                counts = encoderCPR * rotations * gearRatio;
                left.setTargetPosition((int)counts);
                right.setTargetPosition((int)counts);

                left.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
                right.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

                left.setPower(.5);
                right.setPower(.5);

                runmode = mode.END;
                break;
            case END:
                telemetry.addData("counts",counts);
                telemetry.addData("rotations",rotations);
                telemetry.addData("distance",distance);
                telemetry.addData("circumference",circumference);
                telemetry.addData("encoderCPR",encoderCPR);
                telemetry.addData("gearRatio",gearRatio);

                break;
        
        }
        telemetry.addData("mode",runmode);
        telemetry.addData("isbeingpressed",isBeingPressed);
        

    }

    private enum mode{INIT,MOVE,END}

}
