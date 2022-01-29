package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "DriveMode")
public class DriveMode extends LinearOpMode {

  private DcMotor rearleft;
  private DcMotor frontright;
  private DcMotor armMotor;
  private DcMotor frontleft;
  private DcMotor rearright;
  private DcMotor fingerMotor;
  private DcMotor wheelMotor;

  @Override
  public void runOpMode() {
    double drivePower;
    double forward;
    float strafe;
    double turn;

    rearleft = hardwareMap.get(DcMotor.class, "rearleft");
    frontright = hardwareMap.get(DcMotor.class, "frontright");
    armMotor = hardwareMap.get(DcMotor.class, "armMotor");
    frontleft = hardwareMap.get(DcMotor.class, "frontleft");
    rearright = hardwareMap.get(DcMotor.class, "rearright");
    fingerMotor = hardwareMap.get(DcMotor.class, "fingerMotor");
    wheelMotor = hardwareMap.get(DcMotor.class, "wheelMotor");

    drivePower = 0.6;
    rearleft.setDirection(DcMotorSimple.Direction.REVERSE);
    frontright.setDirection(DcMotorSimple.Direction.REVERSE);
    armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    waitForStart();
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        forward = gamepad1.left_stick_y * 0.4;
        strafe = -gamepad1.right_stick_y;
        turn = gamepad1.left_stick_x * 0.55;
        frontleft.setPower(forward + strafe + turn);
        rearright.setPower((forward - strafe) + turn);
        rearleft.setPower((forward + strafe) - turn);
        frontright.setPower((forward - strafe) - turn);
        armMotor.setPower(-(0.5 * gamepad2.right_stick_y));
        if (gamepad1.dpad_up) {
          if (drivePower < 1) {
            drivePower += 0.1;
          }
        } else if (gamepad2.x) {
          fingerMotor.setPower(0.5);
        } else if (gamepad2.b) {
          fingerMotor.setPower(-0.2);
        } else if (gamepad1.dpad_down) {
          if (drivePower > 0.1) {
            drivePower += -0.1;
          }
        } else if (gamepad1.y) {
          drivePower = 1;
        } else if (gamepad1.a) {
          drivePower = 0.8;
        } else if (gamepad2.right_bumper) {
          wheelMotor.setPower(0.7);
          sleep(1300);
          wheelMotor.setPower(0.9);
          sleep(1500);
          wheelMotor.setPower(0);
        } else if (gamepad2.left_bumper) {
          wheelMotor.setPower(-0.7);
          sleep(1300);
          wheelMotor.setPower(-0.9);
          sleep(1500);
          wheelMotor.setPower(0);
        }
        wheelMotor.setPower(-gamepad2.right_trigger);
        wheelMotor.setPower(gamepad2.left_trigger);
        telemetry.update();
        telemetry.addData("Drive Factor", drivePower * 100);
      }
    }
  }
}
