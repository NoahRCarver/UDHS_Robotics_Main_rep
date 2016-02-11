
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class TreelWedBotTeleOpMain extends OpMode {

    DcMotor RTM; //Front Right Motor, Back Right Motor
    DcMotor LTM; //Front Left Motor, Back Left Motor
    DcMotor BOM, BIM; //Tread Delivery Motor, Tread Intake Motor
    Servo RP, LP, DM, DH; //BTN;  Right Paddle, Left paddle, Climber Arm
    boolean RPaddleIsOpen=false;
    boolean LPaddleIsOpen=false;
    boolean DebrisHatchIsOpen = false;
    boolean ArmIsUp=false;


    // position of the DropMen servo.
    final double RPClosedPosition = 0.00, LPClosedPosition = .40, CAClosedPosition = 0.0, DHClosedPosition = .5;
    final double RPOpenPosition = 0.40, LPOpenPosition = .00, CAOpenPosition = 1.0, DHOpenPosition = 0;


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
        RTM = hardwareMap.dcMotor.get("RTM");
      //  BRM = hardwareMap.dcMotor.get("br");
        LTM = hardwareMap.dcMotor.get("LTM");
        //BLM = hardwareMap.dcMotor.get("bl");

        BOM = hardwareMap.dcMotor.get("BOM");
        BIM = hardwareMap.dcMotor.get("BIM");

        RP = hardwareMap.servo.get("RP"); //port 1
        LP = hardwareMap.servo.get("LP"); //port 2
        DM = hardwareMap.servo.get("DM"); //port 3
        DH = hardwareMap.servo.get("DH");
        
        //init driveScheme
        driveScheme = new BaseDriveMotorControl(RTM, LTM);
        
        BOM.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BIM.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

    }

    @Override
    public void start(){}

    @Override
    public void init_loop(){}

    @Override
    public void loop() {
        driveScheme.tankDrive(gamepad1);

        if(gamepad2.dpad_up){
            BIM.setPower(.75);
        }else{
            BIM.setPower(0);
        }

        if (gamepad2.x) {
            if(!DebrisHatchIsOpen) {
                DebrisHatchIsOpen = true;
                DH.setPosition(DHOpenPosition);
            }

        }else{
            if(DebrisHatchIsOpen) {
                DebrisHatchIsOpen = false;
                DH.setPosition(DHClosedPosition);
            }

        }

        if(gamepad2.dpad_left){
            BOM.setPower(.25);
        }else if(gamepad2.dpad_right){
            BOM.setPower(-.25);
        }else{
            BOM.setPower(0);
        }

        if (gamepad2.a) {
            if(!RPaddleIsOpen) {
                RPaddleIsOpen = true;
                RP.setPosition(RPOpenPosition);
            }

        }else{
            if(RPaddleIsOpen) {
                RPaddleIsOpen = false;
                RP.setPosition(RPClosedPosition);
            }

        }
        if (gamepad2.y) {
            if(!LPaddleIsOpen) {
                LPaddleIsOpen = true;
                LP.setPosition(LPOpenPosition);
            }

        }else{
            if(LPaddleIsOpen) {
                LPaddleIsOpen = false;
                LP.setPosition(LPClosedPosition);
            }

        }


        if (gamepad2.b) {
            if(!ArmIsUp) {
                ArmIsUp = true;
                DM.setPosition(CAOpenPosition);            }
        }
        else{
            if(ArmIsUp) {
                ArmIsUp = false;
                DM.setPosition(CAClosedPosition);
            }
        }
    }

    @Override
    public void stop() {

    }
}