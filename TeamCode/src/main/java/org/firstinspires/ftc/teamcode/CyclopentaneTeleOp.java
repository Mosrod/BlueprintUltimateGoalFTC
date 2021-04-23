package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.blueprint.ftc.core.AbstractLinearOpMode;
import org.blueprint.ftc.core.GamepadDriver;
import org.blueprint.ftc.core.IntakeSystem;
import org.blueprint.ftc.core.controllers.IMUController;


/**
 * Gamepad1 Controls:
 * <p>
 * left_stick_y:   drive forward / reverse
 * left_stick_x:   strafe left / right
 * right_stick_x:  turn left / right
 * X:  Put robot in forward, regular drive mode
 * B:  Put robot in reverse drive mode
 * left_trigger:  Foundation system up
 * right_trigger:  Foundation system down
 * left_bumper:  Intake system reverse
 * right_bumper:  Intake system forward
 * A:  Intake system off
 * <p>
 * <p>
 * Gamepad2 Controls:
 * <p>
 * left_stick_y:   Drive lift system up and down
 * right_stick_y:  Drive lift system arm up and down
 * Y:  Lift system automode to pickup block from storage
 * A:  Lift system automode to go back to base
 * dpad_left:   Open lift system gripper
 * dpad_right:  Close lift system gripper
 * <p>
 * Multi-threading based on this example:
 * https://stemrobotics.cs.pdx.edu/node/5184
 */

@TeleOp(name = "SkystoneTele")
//  @Disabled
public class CyclopentaneTeleOp extends AbstractLinearOpMode {

//    private MotorControllerEx motor;
    private IMUController imu;

//    private Driver driver;
//    private FoundationSystem foundationSystem;
    private IntakeSystem intakeSystem;
//    private LiftSstem liftSystem;
    private GamepadDriver gpd;
    private IntakeSystem intake;
    private boolean intakeToggle;

    @Override
    public void initOpMode() throws InterruptedException {

        telemetry.addData("WAIT FOR INITIALIZATION. OR ELSE...:  "," DON'T PRESS PLAY!");
        telemetry.update();

        this.initRosie();

        this.imu = this.rosie.getIMUController();
        this.imu.resetAngle();

        this.gpd = this.rosie.getGamepadDriver();

        this.intake = this.rosie.getIntakeSystem();

        telemetry.addData("INITIALIZATION DONE. OR IS IT?...","  PRESS PLAY WHEN READY.");
        telemetry.update();
    }

    //  Post start init logic;  robot can only expand after start of game or after press start
    public void postStartSetup() throws InterruptedException {
        //  Change driving direction
        this.gpd.putInReverse(gamepad1.b);
        this.gpd.putInDrive(gamepad1.x);

        this.intake.setDCMotorsPower(1);
    }

    @Override
    public void stopOpMode() {

        this.stop();

        this.stopDriving();
        this.intakeSystem.stop();
//        this.foundationSystem.triggerUp(true);
//        this.liftSystem.moveBackSlide();
    }

    private void addGamepadTelemetry() {
        telemetry.addData("Gamepad1.LeftStickY", gamepad1.left_stick_y);
        telemetry.addData("Gamepad1.LeftStickX", gamepad1.left_stick_x);
        telemetry.addData("Gamepad1.RightStickX", gamepad1.right_stick_x);
        //  telemetry.addData("Gamepad1.B", gamepad1.b);
        //  telemetry.addData("Gamepad1.X", gamepad1.x);
        //  telemetry.addData("Gamepad1.LeftTrigger", gamepad1.left_trigger);
        //  telemetry.addData("Gamepad1.RightTrigger", gamepad1.right_trigger);
        //  telemetry.addData("Gamepad1.LeftBumper", gamepad1.left_bumper);
        //  telemetry.addData("Gamepad1.RightBumper", gamepad1.right_bumper);
        //  telemetry.addData("Gamepad1.A", gamepad1.a);
        telemetry.addData("Gamepad2.LeftStickY", gamepad2.left_stick_y);
        telemetry.addData("Gamepad2.RightStickY", gamepad2.right_stick_y);
        //  telemetry.addData("Gamepad2.Y", gamepad2.y);
        //  telemetry.addData("Gamepad2.A", gamepad2.a);
        //  telemetry.addData("Gamepad2.DpadLeft", gamepad2.dpad_left);
        //  telemetry.addData("Gamepad2.DpadRight", gamepad2.dpad_right);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        this.initOpMode();

        this.waitToPressStart();

//        g1.start();
//        g2.start();

        this.postStartSetup();

        while (opModeIsActive()) {


            this.addGamepadTelemetry();

            this.gpd.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            if (gamepad1.x) {
                this.intake.start();
            }

            if (gamepad1.b) {
                this.intake.stop();
            }




            telemetry.update();
        }

        //  Stop thread;
//        g1.interrupt();
//        g2.interrupt();

        this.stopOpMode();
    }
}
