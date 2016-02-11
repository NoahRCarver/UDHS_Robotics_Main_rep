package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class ServoOp2 extends OpMode {

   Servo testee;
 boolean RPaddleIsOpen = false;
    double testOpenPosition = .5;
    double testClosedPosition = 0;

    public ServoOp2() {

    }

    /*
     * Code to run when the op mode is first enabled goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void init() {




        testee = hardwareMap.servo.get("test");

        testee.setPosition(0);
    }

    @Override
    public void init_loop() {



    }

    /*
     * This method will be called repeatedly in a loop
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */
    @Override
    public void loop() {
        if (gamepad2.a) {
            if(!RPaddleIsOpen) {
                RPaddleIsOpen = true;
                testee.setPosition(testOpenPosition);
            }

        }else{
            if(RPaddleIsOpen) {
                RPaddleIsOpen = false;
                testee.setPosition(testClosedPosition);
            }

        }




    }



}
