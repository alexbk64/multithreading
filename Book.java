public class Book {
    private String id;
    private boolean writeLocked ;
    private int readLocked;

    public Book() {
        writeLocked = false;
        readLocked = 0;
    }



    public synchronized void getWriteLock() throws InterruptedException {
        while (writeLocked || readLocked != 0) {
            wait();
        }
        writeLocked = true;
    }

    public synchronized void getReadLock() throws InterruptedException {
        while (writeLocked) {
            wait() ;
        }
        readLocked++;
    }




    public synchronized void releaseReadLock() {
        readLocked--;
        notifyAll();
    }
    public synchronized void releaseWriteLock() {
        writeLocked = false ;
        notifyAll();
    }
}
