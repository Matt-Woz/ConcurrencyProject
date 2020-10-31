class webserver extends Thread
  {										//Web server removes elements from the buffer
    private int id;
    private int num_elements;
    private static Buffer buf;

    public webserver(int i, int el, Buffer b)
    {
      id = i;
      num_elements = el;
      buf = b;
    }

    public void run()
    {
      int n = 1;
      while (num_elements > 0)
      {
        try {
            buf.remove(n);
        } catch (InterruptedException e) {e.printStackTrace();}
        num_elements--;
      }
    }

    @Override
    public long getId() {
      return id;
    }
  }