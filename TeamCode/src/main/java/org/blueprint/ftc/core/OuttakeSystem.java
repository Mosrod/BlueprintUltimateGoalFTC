package org.blueprint.ftc.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OuttakeSystem {

    private DcMotor outtakeMotor;

    private boolean isOn;  // on, off switch
    private double intakePower;

    public OuttakeSystem(HardwareMap hardwareMap) {
        this.initDCMotors(hardwareMap);
    }

    private void initDCMotors(HardwareMap hardwareMap) {
        this.outtakeMotor = hardwareMap.dcMotor.get(Constants.INTAKE_MOTOR);
        this.outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.outtakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setDCMotorsPower(double power) {
        this.outtakeMotor.setPower(power);
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
