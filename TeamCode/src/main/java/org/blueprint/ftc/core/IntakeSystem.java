package org.blueprint.ftc.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.blueprint.ftc.core.controllers.CRServoController;

public class IntakeSystem {

    private DcMotor intakeMotor;

    private boolean isOn;  // on, off switch
    private double intakePower;

    public IntakeSystem(HardwareMap hardwareMap) {
        this.initDCMotors(hardwareMap);
    }

    private void initDCMotors(HardwareMap hardwareMap) {
        this.intakeMotor = hardwareMap.dcMotor.get(Constants.INTAKE_MOTOR);
        this.intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setDCMotorsPower(double power) {
        this.intakeMotor.setPower(power);
    }

    public void stopDCMotors() {
        this.setDCMotorsPower(0);
    }

    public void start() {
        this.setDCMotorsPower(1.0);
    }

    public void stop() {
        this.stopDCMotors();
    }

}
