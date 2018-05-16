import java.util.Random;
//when thread starting generaterandom number


public class Worker implements Runnable {
    protected Book book;
    protected Random random;
    protected boolean writer;
    private String id;
    private volatile double randomNum;
    private boolean running;


    public Worker(String s, Book b) {
        id = s;
        book = b;
        running=true;
        random = new Random();
        randomNum = Math.random();
        if (Math.random()<=0.8)
            writer = true;//writer
        else
            writer = false;//reader
    }
    public void terminate() {
        running = false;
    }


    public void run() {
        try {
            while (!Thread.interrupted() && running) {

                getLock();
                System.out.println(startMessage());
                Thread.sleep((long)randomNum);
                //Thread.sleep(random.nextInt(100));
                System.out.println(waitingMessage());
                Thread.sleep((long)randomNum);
                releaseLock();
                System.out.println(finishedMessage());
            }
        }
        catch (InterruptedException e) {
            releaseLock();
            running = false;
        }
        //System.out.println(entries + "entries in book");
    }//END run

    protected void getLock() throws InterruptedException {
        if (writer) {
            book.getWriteLock();
        }
        else {
            book.getReadLock();
        }

    }

    protected void releaseLock() {
        if (writer) {
            //System.out.println("releasing write lock");
            book.releaseWriteLock();
            //System.out.println("write lock released)");
        }
        else {
            //System.out.println("releasing read lock");
            book.releaseReadLock();
            //System.out.println("read lock released");
        }
    }


    public String startMessage() {
        if (writer)
            return id + " is requesting to write";
        else
            return id + " is requesting to read";
    }

    public String waitingMessage() {
        if (writer)
            return id + " is writing";
        else
            return id + " is reading";
    }

    public String finishedMessage() {
        if (writer)
            return id + " has finished writing";
        else
            return id + " has finished reading";

    }

}
