package swap.bot.root.tuneapi.Upgrades;

import java.nio.ByteBuffer;

public class UpgradeApi {

    /**
     *   >ENGINE
     *         -INTAKE
     *         -FUEL SYSTEM
     *         -IGNITION
     *         -EXHAUST
     *         -CAMSHAFT
     *         -VALVES
     *         -DISPLACEMENT
     *         -INTERCOOLER
     *         -OIL COOLING
     *         -FLYWHEEL
     *         -TURBO
     *         -TWIN TURBO
     *         -POSITIVE DISPLACEMENT SUPERCHARGER
     *         -CENTRIFUGAL SUPERCHARGER
     *
     *   >PLATFORM AND HANDLING
     *         -BRAKES
     *         -SPRINGS
     *         -ANTI-ROLL BARS FRONT
     *         -ANTI-ROLL BARS REAR
     *         -ROLL CAGE
     *         -WEIGHT REDUCTION
     *   >------
     *         -
     *         -
     *         -
     *         -
     *         -
     *   >TIRES AND RIMS
     *         -COMPOUND
     *         -FRONT TIRE WIDTH
     *         -REAR TIRE WIDTH
     *         -RIMS
     *         -FRONT RIM SIZE
     *         -REAR RIM SIZE
     *
     */
    ByteBuffer BYTE_FILE;
    //public static short padding;
    //public static Integer ordinal;
    public Integer unknown1;
    public Integer rims;
    public Integer engine;
    public Integer drivetrain;
    public Integer carbody;
    public Integer motor;
    public Integer brakes;
    public Integer springs;
    public Integer antirollbars_front;
    public Integer antirollbars_rear;
    public Integer tire_compound;
    public Integer wing_rear;
    public Integer rim_size_front;
    public Integer rim_size_rear;
    public Integer camshaft;
    public Integer valves;
    public Integer displacement;
    public Integer piston_compression;
    public Integer fuel_system;
    public Integer ignition;
    public Integer exhaust;
    public Integer intake;
    public Integer flywheel;
    public Integer manifold;
    public Integer restrictor_plate;
    public Integer oil_cooling;
    public Integer single_turbo;
    public Integer twin_turbo;
    public Integer quad_turbo;
    public Integer centrifugal_supercharger;
    public Integer positive_displacement_supercharger;
    public Integer intercooler;
    public Integer clutch;
    public Integer transmission;
    public Integer driveline;
    public Integer differential;
    public Integer bumper_front;
    public Integer bumper_rear;
    public Integer hood;
    public Integer side_skirts;
    public Integer tire_width_front;
    public Integer tire_width_rear;
    public Integer weight_reduction;
    public Integer chassis_stiffness;
    public Integer unknown2;
    public Integer track_width_front;
    public Integer track_width_rear;

    public UpgradeApi(ByteBuffer bytefile){
        this.BYTE_FILE = bytefile;
        BYTE_FILE.position(6);

        unknown1 = BYTE_FILE.getInt();
        rims = BYTE_FILE.getInt();
        engine = BYTE_FILE.getInt();
        drivetrain = BYTE_FILE.getInt();
        carbody = BYTE_FILE.getInt();
        motor = BYTE_FILE.getInt();
        brakes = BYTE_FILE.getInt();
        springs = BYTE_FILE.getInt();
        antirollbars_front = BYTE_FILE.getInt();
        antirollbars_rear = BYTE_FILE.getInt();
        tire_compound = BYTE_FILE.getInt();
        wing_rear = BYTE_FILE.getInt();
        rim_size_front = BYTE_FILE.getInt();
        rim_size_rear = BYTE_FILE.getInt();
        camshaft = BYTE_FILE.getInt();
        valves = BYTE_FILE.getInt();
        displacement = BYTE_FILE.getInt();
        piston_compression = BYTE_FILE.getInt();
        fuel_system = BYTE_FILE.getInt();
        ignition = BYTE_FILE.getInt();
        exhaust = BYTE_FILE.getInt();
        intake = BYTE_FILE.getInt();
        flywheel = BYTE_FILE.getInt();
        manifold = BYTE_FILE.getInt();
        restrictor_plate = BYTE_FILE.getInt();
        oil_cooling = BYTE_FILE.getInt();
        single_turbo = BYTE_FILE.getInt();
        twin_turbo = BYTE_FILE.getInt();
        quad_turbo = BYTE_FILE.getInt();
        centrifugal_supercharger = BYTE_FILE.getInt();
        positive_displacement_supercharger = BYTE_FILE.getInt();
        intercooler = BYTE_FILE.getInt();
        clutch = BYTE_FILE.getInt();
        transmission = BYTE_FILE.getInt();
        driveline = BYTE_FILE.getInt();
        differential = BYTE_FILE.getInt();
        bumper_front = BYTE_FILE.getInt();
        bumper_rear = BYTE_FILE.getInt();
        hood = BYTE_FILE.getInt();
        side_skirts = BYTE_FILE.getInt();
        tire_width_front = BYTE_FILE.getInt();
        tire_width_rear = BYTE_FILE.getInt();
        weight_reduction = BYTE_FILE.getInt();
        chassis_stiffness = BYTE_FILE.getInt();
        unknown2 = BYTE_FILE.getInt();
        track_width_front = BYTE_FILE.getInt();
        track_width_rear = BYTE_FILE.getInt();
    }

    public TiresAndRims getTiresAndRims(){
        return new TiresAndRims(this);
    }
    public Appearance getAppearance(){
        return new Appearance(this);
    }

}
