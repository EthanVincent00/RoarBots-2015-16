package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Roarbots on 9/20/2015.
 */
public class TeleOp extends TeleOpResQCommonV2 {
    private int numOpLoops = 1;
    private boolean pushOnceRT = false;
    private boolean pushOnceLT = false;
    private boolean pushOnceRB = false;
    private double newClipNum = .75;
    private double L_moustache_distance = 60.0;
    private double R_moustache_distance = 60.0;

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void init() {
        super.init();

        RightMoustache.setPosition(.90);
        LeftMoustache.setPosition(.1);
        theOneThatFlips.setPosition(1);
    }

    @Override
    public void loop () {
    /*
     * Gamepad 1
     *
     * Gamepad 1 controls the motors via the left and right sticks(tank drive), and it controls the wrist/claw via the a,b,
     * x, y buttons
     */
            // throttle:  left_stick_y ranges from -1 to 1, where -1 is full up,  and 1 is full down
            // direction: left_stick_x ranges from -1 to 1, where -1 is full left and 1 is full right

        double left_drive_power = 0.0;
        double right_drive_power = 0.0;
        double tape_angle_power = 0.0;
        double tape_extender_power = 0.0;
        double left_stick = gamepad1.left_stick_y;
        double right_stick = gamepad1.right_stick_y;
        double extension_stick = gamepad2.left_stick_y;
        double angle_stick = gamepad2.right_stick_y;

        boolean confirmButton = gamepad2.back;
        boolean LMButton = gamepad2.x;
        boolean RMButton = gamepad2.b;
        boolean LTrigger = gamepad2.left_bumper;
        boolean RTrigger = gamepad2.right_bumper;

        // clip the right/left values so that the values never exceed +/- 1
        left_drive_power = Range.clip(left_stick, MOTOR_MIN_RANGE, MOTOR_MAX_RANGE);
        right_drive_power = Range.clip(right_stick, MOTOR_MIN_RANGE, MOTOR_MAX_RANGE);
        tape_extender_power = Range.clip(extension_stick, MOTOR_MIN_RANGE, MOTOR_MAX_RANGE);
        tape_angle_power = Range.clip(angle_stick, MOTOR_MIN_RANGE, MOTOR_MAX_RANGE);

//1337

        // write the values to the motors
        if (Math.abs(left_drive_power) < 0.05) {
            left_drive_power = 0.0;}
        setLeftPower(left_drive_power);

        if (Math.abs(right_drive_power) < 0.05) {
            right_drive_power = 0.0;}
        setRightPower(right_drive_power);

        if (Math.abs(tape_extender_power) < 0.05) {
            tape_extender_power = 0.0;}
        setTapeMotorPower(tape_extender_power);

        if (Math.abs(tape_angle_power) < 0.05) {
            tape_angle_power = 0.0;}
        setTapeAnglePower(tape_angle_power);

        //if (confirmButton) {
            if (LMButton) {
                //Left - EL MOUSTACHIO
                LeftMoustache.setPosition(0.9);
            } else if (RMButton) {
                //Right - OUR MOUSTACHE
                RightMoustache.setPosition(0);
            }
        //}

        if (LTrigger) {
            theOneThatFlips.setPosition(0.9);
        } else if (RTrigger) {
            theOneThatFlips.setPosition(0);
        }

        leftMotors.get(1).setMode(DcMotorController.RunMode.RESET_ENCODERS);

        telemetry.addData("Encoder Current Position ", leftMotors.get(1).getCurrentPosition());

        telemetry.addData("left motor ", left_drive_power);
        telemetry.addData("right motor ", right_drive_power);
        telemetry.addData("tape power ", tape_extender_power);
        telemetry.addData("tape angle power ", tape_angle_power);
    }
}
