package org.firstinspires.ftc.teamcode.opmodes;
//today noah wasted five minutes
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotHardware;


@TeleOp
public class TestTeleop extends OpMode    {
    RobotHardware robot;

    public void init(){
        robot=new RobotHardware();

        robot.init();
    }
    public void start() {
        robot.launchLight.setWhite();
        stateTimer.reset();
        motorTimer.reset();

    }
    public void loop(){
        robot.launch.processGamepad();
        robot.launchLight.CheckLight();
        robot.launch.motorOnMotorOff();
        double seconds = stateTimer.seconds();
        if (stateTimer.seconds()>10.0){
            stateTimer.reset();
        }
      //mechanismTimer.reset();
    }
    private ElapsedTime stateTimer = new ElapsedTime();
    private ElapsedTime motorTimer = new ElapsedTime();
}
