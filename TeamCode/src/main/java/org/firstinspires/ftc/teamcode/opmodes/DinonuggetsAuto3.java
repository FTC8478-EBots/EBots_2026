package org.firstinspires.ftc.teamcode.opmodes;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.firstinspires.ftc.teamcode.paths.AutoAttempt4;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Launch;

import static com.pedropathing.api.Paths.*;


@Autonomous
public class DinonuggetsAuto3 extends  OpMode {

    Follower follower;
Launch launcher;
    private final PoseFactory poseFactory = PoseFactory.degrees();
    AutoAttempt4 autoAttempt4 = new AutoAttempt4();

    private final Command autoRoutine () {

            return sequential(
                  // instant(() -> launcher.LaunchAction(1200)),
                    follow(follower,autoAttempt4.path_1())

                    //follow(follower, autoAttempt4.path_2()),
                    //follow(follower, autoAttempt4.path_3())

                    // Add mechanism commands here.
                    // follow(follower, park())
            );
        }
        @Override
        public void init () {
            Scheduler.reset();

            follower = Constants.create(hardwareMap);
            follower.setPose(autoAttempt4.start);
            follower.update();
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
        }
    }

