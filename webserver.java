class webserver extends Thread
  {										//Web server removes elements from the buffer
    private int id;
    private int num_elements;
    private static Buffer buf;
    semaphore mutex;
    semaphore space_avail;
    semaphore item_avail;
    int n = 1;

    /**
     * Server thread which removes items from buffer
     * @param i Id of server thread
     * @param el Number of threads this server has to remove
     * @param b Instance of buffer
     * @param mutex Semaphore for mutual exclusion inside critical section
     * @param space_avail Semaphore maintains number of empty spaces in buffer
     * @param item_avail Semaphore maintains number of items in buffer
     */
    public webserver(int i, int el, Buffer b, semaphore mutex, semaphore space_avail, semaphore item_avail)
    {
      id = i;
      num_elements = el;
      buf = b;
      this.mutex = mutex;
      this.space_avail = space_avail;
      this.item_avail = item_avail;
    }

    public void run() {
      while (num_elements > 0) {
        try {
          item_avail.P(); //Items in buffer++
          mutex.P();
          buf.remove(n); //Remove element from buffer
          num_elements--;
          mutex.V();
          space_avail.V(); //emptySpace++
        }
        catch(InterruptedException interruptedException){
          interruptedException.printStackTrace();
        }
      }
    }

    @Override
    public long getId() {
      return id;
    }
  }