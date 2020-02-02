package frc.robot.subsystem;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Logger;
import frc.robot.OI;
import frc.robot.subsystem.led.Led;
import frc.robot.subsystem.pwm.PWM;
import frc.robot.subsystem.telemetry.Telemetry;
import frc.robot.OzoneException;

public class SubsystemFactory {

    private static SubsystemFactory me;

    static Logger logger = Logger.getLogger(SubsystemFactory.class.getName());

    //private static String botMacAddress;
    private static String botName; 

    // private String footballMacAddress = "00:80:2F:17:D7:4B";
    private HashMap<String,String> allMACs; // will contain mapping of MACs to Bot Names
    private static DisplayManager displayManager;

    /**
     * keep all available subsystem declarations here.
     */

    private Telemetry telemetry;
    private PWM pwm;
    private Led led;

    private SubsystemFactory() {
        // private constructor to enforce Singleton pattern
        botName = "unknown";
        allMACs = new HashMap<>();  
        // add all the mappings from MACs to names here
        // as you add mappings here:
        //    1) update the select statement in the init method 
        //    2) add the init method for that robot
        allMACs.put("00:80:2F:17:BD:76","zombie"); //usb0
        allMACs.put("00:80:2F:17:BD:75","zombie"); //eth0
    }

    public static SubsystemFactory getInstance(boolean b) {

        if (me == null) {
            me = new SubsystemFactory();
        }

        return me;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public PWM getPWM() {
        return pwm;
    }

    public Led getLed() {
        return led;
    }

    public void init(DisplayManager dm, PortMan portMan) throws Exception {

        logger.info("initializing");

        //botMacAddress = InetAddress.getLocalHost().getHostAddress().trim();
        //logger.info("IP address here is "+botMacAddress);
        //InetAddress addr = InetAddress.getLocalHost();
        Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
        String activeMACs = "";
        for (NetworkInterface net : Collections.list(networks)) {
            String mac = formatMacAddress(net.getHardwareAddress());
            activeMACs += (mac+" ");
            logger.info("Network #"+net.getIndex()+" "+net.getName()+" "+mac);
            if (allMACs.containsKey(mac)) {
                botName = allMACs.get(mac);
                logger.info("   this MAC is for "+botName);
            }
        }

        logger.info("Running on "+botName);

        displayManager = dm;

        try {
            
            switch (botName) {
                case "football": initFootball(portMan); break;
                case "unknown": initZombie(portMan); break;
                case "zombie": initZombie(portMan); break;
                default: throw new Exception("Unrecognized MAC Address [" + activeMACs + "]");
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
        if (hardwareAddress == null || hardwareAddress.equals("")) {
            return "";
        }
        StringBuilder mac = new StringBuilder(); // StringBuilder is a premature optimization here, but done as best practice
        for (int k=0;k<hardwareAddress.length;k++) {
            int i = hardwareAddress[k] & 0xFF;  // unsigned integer from byte
            String hex = Integer.toString(i,16);
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
         * Normally we'd expect these sort of things to be in separate initializations per robot
         * But for our roboRIOTester, we want to be able to test any RIO so we do it here
         * All of the Telemetery Stuff goes here
         */
        telemetry = new Telemetry();
        telemetry.init(portMan);
        displayManager.addTelemetry(telemetry);
        
    }

    private void initZombie(PortMan portMan) throws OzoneException {
        logger.info("Initializing Zombie");
        pwm = new PWM();
        pwm.init(portMan);
        displayManager.addPWM(pwm);

        led = new Led();
        led.init(portMan);
        displayManager.addLed(led);
    }

    /**
     * init subsystems specific to Football
     */

    private void initFootball(PortMan portMan) throws Exception {
        logger.info("Initializing Football");

    }
}