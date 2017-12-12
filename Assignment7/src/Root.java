public class Root { // score 2, keep the variables private and synchronizing object as final.
    public static void main(String[] args) {

        Device device = new Device();
        Sensor heat = new Sensor(device);
        Sensor pressure = new Sensor(device);

        Controller controller = new Controller(device,heat,pressure);

        controller.start();
        pressure.start();
        heat.start();

    }
}

class Device {
    double heat;
    double pressure;

    public void startup() {
        System.out.println("Device started");
    } // print to console that device is starting
    public void shutdown() {
        System.out.println("Device shtting down due to maitenance");
    } // print to console that device is shutting down and exit
}

class Sensor extends Thread {
    private final Device device;
    private double value = 0;
    public Sensor(Device device) {
        this.device = device;
    }
    public double getValue() {
        try {
            Thread.sleep(500);
        } catch(InterruptedException e) {

        }
        return value;
    }
    public void updateValue() {
        double randomValue = Math.random();
        this.value += randomValue; // you check with other values here and see how it works
    }
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {

            }
            updateValue();
            synchronized (device) {
                device.notifyAll();
            }
        }
    }
}

class Controller extends Thread {
    Device device;
    Sensor heat;
    Sensor pressure;

    public Controller(Device device, Sensor heat, Sensor pressure) {
        this.device = device;
        this.heat = heat;
        this.pressure = pressure;
    }

    public void run() {
        device.startup();
        double temp = 0;
        double press = 0;
        while (temp < 70 && press < 100) {
            String notif = String.format("heat -> %.2f, pressure -> %.2f", temp, press);
            System.out.println(notif);
            temp = heat.getValue();
            press= pressure.getValue();
            synchronized (device) {
                try {
                    device.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        heat.interrupt();
        pressure.interrupt();
        device.shutdown();
        System.exit(0);
    }
}
