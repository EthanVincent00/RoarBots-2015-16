package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Roarbots on 12/15/2015.
 */
public class BackwardClimbers extends AutonomousResQCommon{
    @Override
    public void runOpMode(){
        try {
            wait(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.drive(-1, 12);//(power, inches)
    }

}
