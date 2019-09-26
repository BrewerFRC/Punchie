/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;


public class Robot extends TimedRobot {
  private Drive drive = new Drive();
  private Flapper flapper = new Flapper();
  private Xbox xbox = new Xbox(0);
  UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
  
  @Override
  public void robotInit() {

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  
  @Override
  public void autonomousPeriodic() {
    teleopPeriodic();
  }

  @Override
  public void teleopInit() {
  }

  
  @Override
  public void teleopPeriodic() {
    // flapper
    if (xbox.getPressed("a")) {
      flapper.start();
    //} else if (xbox.getPressed("b")) {
    //   flapper.eject();
    } else {
      if (!flapper.ejecting()) {
        flapper.stop();
      }
    } 
    flapper.update();
    // drive
    double speed = -xbox.getY(GenericHID.Hand.kLeft);
    double turn = (-xbox.getX(GenericHID.Hand.kLeft)) * 0.75;
    drive.drive(speed, turn);
  }

                        
  @Override
  public void testPeriodic() {
  }

}

