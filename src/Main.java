public class Main {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(Main::increment);
        Thread thread2 = new Thread(Main::increment);

        thread1.setName("Поток №1: ");
        thread2.setName("Поток №2: ");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Finish");
    }

    public static synchronized void increment() {
        while (i <= 10000) {
            try {
                System.out.println(Thread.currentThread().getName() + i++);
                Main.class.notifyAll();
                Main.class.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Main.class.notifyAll();
        Thread.currentThread().interrupt();
    }
}