package org.firstinspires.ftc.teamcode.opmodes;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.ftccommon.internal.manualcontrol.responses.ImuQuaternionResponse;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import java.util.List;

/*double limelight_aim_proportional()
{
    // kP (constant of proportionality)
    // this is a hand-tuned number that determines the aggressiveness of our proportional control loop
    // if it is too high, the robot will oscillate around.
    // if it is too low, the robot will never reach its target
    // if the robot never turns in the correct direction, kP should be inverted.
    double kP = .035;

    // tx ranges from (-hfov/2) to (hfov/2) in degrees. If your target is on the rightmost edge of
    // your limelight 3 feed, tx should return roughly 31 degrees.
    double targetingAngularVelocity = LimelightHelpers.getTX("limelight") * kP;

    // convert to radians per second for our drive method
    targetingAngularVelocity *= Drivetrain.kMaxAngularSpeed;

    //invert since tx is positive when the target is to the right of the crosshair
    targetingAngularVelocity *= -1.0;

    return targetingAngularVelocity;
}*/
@TeleOp
public class TestTeleop extends OpMode    {

    RobotHardware robot;
    private Follower follower;
    //private Limelight3A limelight;



    public void init(){


        robot=RobotHardware.getRobot();

        robot.init(hardwareMap, gamepad1, gamepad2, telemetry);
        follower = Constants.create(hardwareMap);
       // limelight = hardwareMap.get(Limelight3A.class, "limelight");

        telemetry.setMsTransmissionInterval(11);

        //limelight.pipelineSwitch(0);

        /*
         * Starts polling for data.  If you neglect to call start(), getLatestResult() will return null.
         */
       // limelight.start();
    }
    public void start() {

        robot.launchLight.setWhite();
        //stateTimer.reset();
        robot.motorTimer.reset();
        robot.intakeTimer.reset();

    }
    public void loop(){
        robot.launch.processGamepad();
        robot.intake.processGamepad();
        robot.launch.CheckLight();
        robot.launch.motorOnMotorOff();

        //double seconds = stateTimer.seconds();
        //if (stateTimer.seconds()>10.0){
            //stateTimer.reset();
        //}
      //mechanismTimer.reset();

        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                follower.pose().heading()
        );
        if (gamepad1.right_bumper && gamepad1.left_bumper){
            follower.setHeading(0);

        }

        follower.manual(powers);
        follower.update();



        /*LLResult result = robot.limelight.getLatestResult();
        if (result.isValid()) {
            // Access general information
           // Pose3D botpose = result.getBotpose();
            double captureLatency = result.getCaptureLatency();
            double targetingLatency = result.getTargetingLatency();
            double parseLatency = result.getParseLatency();
           // telemetry.addData("LL Latency", captureLatency + targetingLatency);
           // telemetry.addData("Parse Latency", parseLatency);
            telemetry.addData("PythonOutput", java.util.Arrays.toString(result.getPythonOutput()));

            telemetry.addData("tx", result.getTx());
            telemetry.addData("txnc", result.getTxNC());
            telemetry.addData("ty", result.getTy());
            telemetry.addData("tync", result.getTyNC());

           // telemetry.addData("Botpose", botpose.toString());

            // Access fiducial results
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fr : fiducialResults) {
                telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
            }

        } else {
            telemetry.addData("Limelight", "No data available");
        }
*/
        telemetry.update();
    }
// Noah has a pet fish named rumtumtum who is 4


}

