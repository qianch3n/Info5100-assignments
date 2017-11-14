public class ReverseHello extends Thread {
    private int index;
    Object monitor;

    public ReverseHello(int i, Object monitor) {
        this.index = i;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            if (index < 50) {
                ReverseHello next = new ReverseHello(index + 1, monitor);
                next.start();
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Hello from Thread " + index + "!");
            monitor.notifyAll();
        }


    }

    public static void main(String[] args) {
        Object monitor = new Object();

        ReverseHello ins = new ReverseHello(1, monitor);
        ins.start();
    }
}

