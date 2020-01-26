package frc.robot.subsystem;

import java.net.InetAddress;
import java.util.logging.Logger;

import frc.robot.OI;
import frc.robot.subsystem.telemetry.Telemetry;
import frc.robot.OzoneException;

public class SubsystemFactory {

    private static SubsystemFactory me;

    static Logger logger = Logger.getLogger(SubsystemFactory.class.getName());

    private static String botMacAddress;

    private String footballMacAddress = "00:80:2F:17:D7:4B";

    private static DisplayManager displayManager;

    /**
     * keep all available subsystem declarations here.
     */

    private Telemetry telemetry;
    
    private SubsystemFactory() {
        // private constructor to enforce Singleton pattern
    }

    public static SubsystemFactory getInstance(boolean b) {

        if (me == null) {
            me = new SubsystemFactory();
        }

        return me;
    }

    public void init(DisplayManager dm, PortMan portMan) throws Exception {

        logger.info("initializing");

        botMacAddress = InetAddress.getLocalHost().getHostAddress().trim();
        logger.info("IP address here is "+botMacAddress);
        botMacAddress = formatMacAddress(java.net.NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress());
        logger.info("MAC Address here is: "+botMacAddress);
        botMacAddress = footballMacAddress;

        logger.info("[" + botMacAddress + "]");

        displayManager = dm;

        try {

            if (botMacAddress.equals(footballMacAddress) || botMacAddress == null || botMacAddress.equals("")) {
                initFootball(portMan);
            } else {
                throw new Exception("Unrecognized MAC Address [" + botMacAddress + "]");
            }

            initCommon(portMan);

            // driverfeedback will create a shuffleboard tab that aggregates data from
            // subsystems.

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Formats the byte array representing the mac address as more human-readable form
     * @param hardwareAddress byte array
     * @return string of hex bytes separated by colons
     */
    private String formatMacAddress(byte[] hardwareAddress) {
        StringBuilder mac = new StringBuilder(); // StringBuilder is a premature optimization here, but done as best practice
        for (int k=0;k<hardwareAddress.length;k++) {
            String hex = Integer.toString((int)hardwareAddress[k],16);
            if (hex.length() == 1) {  // we want to make all bytes two hex digits 
                hex = "0"+hex;
            }
            mac.append(hex.toUpperCase());
            mac.append(":");
        }
        mac.setLength(mac.length()-1);  // trim off the trailing colon
        return mac.toString();
    }

    /**
     * 
     * init subsystems that are common to all bots
     * 
     * @throws OzoneException
     * 
     */

    private void initCommon(PortMan portMan) throws OzoneException {
        logger.info("Initializing Common");
        /**
         * All of the Telemery Stuff goes here
         */
        telemetry = new Telemetry();
        telemetry.init(portMan);
        displayManager.addTelemetry(telemetry);
        
    }

    /**

     * init subsystems specific to Football

     */

    private void initFootball(PortMan portMan) throws Exception {
        logger.info("Initializing Football");

    }
}