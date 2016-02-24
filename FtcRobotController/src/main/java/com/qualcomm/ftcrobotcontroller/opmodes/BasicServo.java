package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by UDRI1 on 1/8/2016.
 */
public class BasicServo extends OpMode {
    Servo servo1, servo2, servo3, servo4;
    final double servo1OpenPosition = 1.0, servo1ClosedPostition = 0.0,  servo2OpenPosition = 1.0,  servo2ClosedPosition = 1.0,  servo3OpenPosition = 1.0,  servo3ClosedPosition = 1.0,  servo4OpenPosition = 1.0,  servo4ClosedPosition = 1.0;;

    @Override
    public void init() {
        servo1 = hardwareMap.servo.get("s1");
        servo2 = hardwareMap.servo.get("s2");
        servo3 = hardwareMap.servo.get("s3");
        servo4 = hardwareMap.servo.get("s4");
    }

    public void start(){}


    public void init_loop(){}


    public void loop(){
        if (gamepad2.a){
            servo1.setPosition(servo1OpenPosition);
        }
        else{
            servo1.setPosition(servo1ClosedPostition);
        }

        if (gamepad2.b){
            servo2.setPosition(servo2OpenPosition);
        }
        else{
            servo2.setPosition(servo2ClosedPosition);
        }

        if (gamepad2.x){
            servo3.setPosition(servo3OpenPosition);
        }
        else{
            servo3.setPosition(servo3ClosedPosition);
        }

        if (gamepad2.y){
            servo4.setPosition(servo4OpenPosition);
        }
        else{
            servo4.setPosition(servo4ClosedPosition);
        }
    }
}
