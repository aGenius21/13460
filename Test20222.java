package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test20222", group = "")
public class Test20222 extends LinearOpMode {

  private DcMotor newdrive;
  private DcMotor olddrive;
  private DcMotor spinner;

  @Override
  public void runOpMode() {
    newdrive = hardwareMap.dcMotor.get("newdrive");
    olddrive = hardwareMap.dcMotor.get("olddrive");
    spinner = hardwareMap.dcMotor.get("spinner");

    waitForStart();
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        newdrive.setPower(-gamepad1.left_stick_y);
        olddrive.setPower(-gamepad1.right_stick_y);
        spinner.setPower(-gamepad1.left_trigger);
        olddrive.setPower(-gamepad2.right_stick_y);
        spinner.setPower(-gamepad1.right_trigger);
        telemetry.addData("Left Pow", newdrive.getPower());
        telemetry.addData("Right Pow", olddrive.getPower());
        telemetry.update();
      }
    }
  }
}
