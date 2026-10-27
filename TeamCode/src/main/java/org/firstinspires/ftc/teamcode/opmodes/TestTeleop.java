package org.firstinspires.ftc.teamcode.opmodes;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp
public class TestTeleop extends OpMode    {
    RobotHardware robot;
    private Follower follower;


    public void init(){
        robot=RobotHardware.getRobot();

        robot.init(hardwareMap, gamepad1, gamepad2, telemetry);
        follower = Constants.create(hardwareMap);

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
        follower.manual(powers);
        follower.update();

    }



}
