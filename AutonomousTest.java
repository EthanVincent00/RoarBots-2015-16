package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Roarbots on 10/18/2015.
 */
public class AutonomousTest extends AutonomousResQCommon {

    @Override
    public void runOpMode() {
        super.runOpMode();
        reset();
        //REMINDER: drive(motor power, distance(inches));
        setupRobotParameters(6, 1, 40);
        //drive(.75, 12);
        for (DcMotor singleMotor : rightMotors) {
            singleMotor.setTargetPosition(100);
        }for (DcMotor singleMotor : leftMotors) {
            singleMotor.setTargetPosition(100);
        }
    }
}