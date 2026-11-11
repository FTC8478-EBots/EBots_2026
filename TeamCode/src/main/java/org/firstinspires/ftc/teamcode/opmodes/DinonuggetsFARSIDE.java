package org.firstinspires.ftc.teamcode.opmodes;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.paths.FARSIDEADDITION;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Launch;


import java.util.List;


@Autonomous(name="FINALAutonRight DN", preselectTeleOp="TestTeleop")
public class DinonuggetsFARSIDE extends  OpMode {

    RobotHardware robot;
    Follower follower;

    Command autoRoute = sequential(

    );
    Launch launcher;
    private final PoseFactory poseFactory = PoseFactory.degrees();
    FARSIDEADDITION autoAttempt4 = new FARSIDEADDITION();

    private final Command autoRoutine() {

        return sequential(

               //  waitMs(robot.autonDelay),
              //  robot.launch.setVelocity(LAUNCH_VELOCITY),
                robot.launch.AutoLaunch(),
                follow(follower, autoAttempt4.path_1()),
                follow(follower, autoAttempt4.path_2()),
                follow(follower, autoAttempt4.path_3())

               // waitMs(1200),
                // Add mechanism commands here.
                //follow(follower, park())
        );
    }

    @Override
    public void init() {
        Scheduler.
                reset();
        robot = RobotHardware.getRobot();
        robot.init(hardwareMap, gamepad1, gamepad2, telemetry);
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(autoAttempt4.start);
        follower.update();
        // limelight = hardwareMap.get(Limelight3A.class, "limelight");

        telemetry.setMsTransmissionInterval(11);

        // limelight.pipelineSwitch (0);
        //limelight.start();

    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
        // add your other methods needed in the loop here

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());

        telemetry.update();

            /*LLResult result = null //limelight.getLatestResult();
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
               /* List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
                for (LLResultTypes.FiducialResult fr : fiducialResults) {
                    telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
                }

            } else {
                telemetry.addData("limelight", "No data available");
            }

            telemetry.update();
        }
// Noah also has a pet fish named rumtumtumtum who is 444 (i saw this luka :) )
}
*/


    }

}