/**
 * two lidars
 * one boolean to see if the robot is parallel at a certain distance
 * 
 * boolean isSquare(double distance)
 * 
 */

package frc.robot.subsystem.telemetry;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OzoneException;
import frc.robot.subsystem.PortMan;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Telemetry extends SubsystemBase {

    private static Logger logger = Logger.getLogger(Telemetry.class.getName());

    private final Timer m_timer = new Timer();
    private Counter counter = new Counter(Counter.Mode.kSemiperiod);
    private int dioPort;

    public Telemetry() {

    }

    public void init(PortMan portMan) throws OzoneException {
        logger.entering(Telemetry.class.getName(), "init()");

        m_timer.reset();
        m_timer.start();
    
        // Set up the input channel for the counter
        dioPort = portMan.acquirePort(PortMan.digital0_label, "Telemetry.counter");
        counter.setUpSource(dioPort);

        // Set the encoder to count pulse duration from rising edge to falling edge
        counter.setSemiPeriodMode(true);

        logger.exiting(Telemetry.class.getName(), "init()");
    }

    public int getDioPort() {
        return dioPort;
    }

    public void setDioPort(int dport) {
        // Normally on a real robot we'd first acquire the port, but on the real robot we're not swapping wires around 
        // while the robot is running!
        logger.log(Level.INFO,"Changing DIO port from [{0}] to [{1}]", new Object[]{dioPort, dport});
        dioPort = dport;
        counter.setUpSource(dioPort);       // not DRY, but nonetheless...
        counter.setSemiPeriodMode(true);
    }

    public double getPeriodms() {
        return counter.getPeriod()*1000;
    }

}
