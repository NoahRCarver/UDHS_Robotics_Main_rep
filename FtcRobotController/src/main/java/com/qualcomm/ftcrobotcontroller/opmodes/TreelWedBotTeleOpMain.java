
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class TreelWedBotTeleOpMain extends OpMode {

    DcMotor FRM, BRM; //Front Right Motor, Back Right Motor
    DcMotor FLM, BLM; //Front Left Motor, Back Left Motor
    DcMotor TDM, TIM; //Tread Delivery Motor, Tread Intake Motor
    Servo RP, LP, CA; //BTN;  Right Paddle, Left paddle, Climber Arm, Button pusher.

    // position of the DropMen servo.
    double RPClosedPosition = 0.0, LPClosedPosition = 0.0, CAClosedPosition = 0.0;// BTNPosition = 0.0;
    double RPOpenPosition = 0.5, LPOpenPosition = 0.5, CAOpenPosition = 1.0;
    // amount to change the DropMen servo position.
   // double RPDelta = 0.1, LPDelta = 0.1, CADelta = 0.1, BTNDelta = 0.1;


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

        RP = hardwareMap.servo.get("rp"); //port 3
        LP = hardwareMap.servo.get("lp"); //port 1
        CA = hardwareMap.servo.get("ca"); //port 2
       // BTN = hardwareMap.servo.get("btn");
        
        //init driveScheme
        driveScheme = new BaseDriveMotorControl(FRM, BRM, FLM, BLM);
        
        TDM.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        TIM.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        RP.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void start(){}

    @Override
    public void init_loop(){}

    @Override
    public void loop() {
        driveScheme.tankDrive(gamepad1);

        if(gamepad2.dpad_up){
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

        // update the position of the DropMen.
        if (gamepad2.a) {

            RP.setPosition(RPOpenPosition);
            LP.setPosition(LPOpenPosition);
        }else{
            RP.setPosition(RPClosedPosition);
            LP.setPosition(LPClosedPosition);
        }


        if (gamepad2.b) {

            CA.setPosition(CAOpenPosition);
        }
        else{
            CA.setPosition(CAClosedPosition);
        }



      /*  if (gamepad2.x = true){
            BTNPosition += BTNDelta;
        }

        if (gamepad2.x = false){
            BTNPosition -= BTNDelta;
        }
        */
    }

    @Override
    public void stop() {

    }
}