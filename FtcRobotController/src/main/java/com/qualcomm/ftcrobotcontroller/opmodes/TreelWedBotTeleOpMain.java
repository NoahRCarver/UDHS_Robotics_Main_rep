
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class TreelWedBotTeleOpMain extends OpMode {

    DcMotor FRM, BRM; //Front Right Motor, Back Right Motor
    DcMotor FLM, BLM; //Front Left Motor, Back Left Motor
    DcMotor TDM, TIM; //Tread Delivery Motor, Tread Intake Motor
    Servo RP, LP, CA, BTN; // Right Paddle, Left paddle, Climber Arm, Button pusher.


    BaseDriveMotorControl driveScheme;

    public TreelWedBotTeleOpMain() {}


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

        TDM = hardwareMap.dcMotor.get("out");
        TIM = hardwareMap.dcMotor.get("in");

        RP = hardwareMap.servo.get("rp");
        LP = hardwareMap.servo.get("lp");
        CA = hardwareMap.servo.get("ca");
        BTN = hardwareMap.servo.get("btn");
        
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

        if(gamepad2.a){
            TIM.setPower(1);
        }else{
            TIM.setPower(0);
        }

        if(gamepad2.dpad_left){
            TDM.setPower(.25);
        }else if(gamepad2.dpad_right){
            TDM.setPower(-.25);
        }else{
            TDM.setPower(0);
        }



    }

    @Override
    public void stop() {

    }
}