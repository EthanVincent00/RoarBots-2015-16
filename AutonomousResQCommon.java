package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

/**
 * Created by Roarbots on 11/20/2015.
 */
public class AutonomousResQCommon extends AutonomousCommon {

    // Servo testServo;

    // double servoPosition = 0;
    String motor1 = "motor1";//left
    String motor3 = "motor2";//left
    String motor2 = "motor3";//right
    String motor4 = "motor4";//right
    String motor5 = "motor5";//extender lifter upper and lower downer
    String motor6 = "motor6";//extender

    String servo1 = "servo1";//The one that flips
    String servo2 = "servo2";// L Moustache
    String servo3 = "servo3";// R Moustache

    public DcMotor tapeAngleMotor;
    public DcMotor tapePowerMotor;

    public Servo theOneThatFlips;//Climber placer
    public Servo ElMoustachio; //left Moustache
    public Servo OurMoustache; //right Moustache

    private final double wheelSize = 6.0;
    private final double driveTrainRatio = 40.0 / 1.0;


    @Override
    public void runOpMode() {
        leftMotors = new ArrayList<DcMotor>();
        rightMotors = new ArrayList<DcMotor>();
        super.runOpMode();
        leftMotors.add(hardwareMap.dcMotor.get(motor2));
        leftMotors.add(hardwareMap.dcMotor.get(motor4));
        rightMotors.add(hardwareMap.dcMotor.get(motor1));
        rightMotors.add(hardwareMap.dcMotor.get(motor3));
        tapeAngleMotor = hardwareMap.dcMotor.get(motor5);
        tapePowerMotor = hardwareMap.dcMotor.get(motor6);

        theOneThatFlips = hardwareMap.servo.get(servo1);
        ElMoustachio = hardwareMap.servo.get(servo2);
        OurMoustache = hardwareMap.servo.get(servo3);

//        int position = leftMotors.get(0).getCurrentPosition();

        for (DcMotor singleMotor : rightMotors) {
            singleMotor.setDirection(DcMotor.Direction.FORWARD);
            singleMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        for (DcMotor singleMotor : leftMotors) {
            singleMotor.setDirection(DcMotor.Direction.REVERSE);
            singleMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }

        tapeAngleMotor.setDirection(DcMotor.Direction.REVERSE);
        tapeAngleMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        tapePowerMotor.setDirection(DcMotor.Direction.FORWARD);
        tapePowerMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    public void setLeftPower(double leftMotorPower) {super.setLeftPower(leftMotorPower);}
    public void setRightPower(double rightMotorPower) {super.setRightPower(rightMotorPower);}

    public void setTapeAnglePower(double power){tapeAngleMotor.setPower(power);}
    public void setTapeMotorPower(double power) {tapePowerMotor.setPower((power));}

    public void reset () {
        for (int i = 0; i < rightMotors.size(); i++) {
            rightMotors.get(i).setMode(DcMotorController.RunMode.RESET_ENCODERS);
            leftMotors.get(i).setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
        tapeAngleMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        tapePowerMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
}
