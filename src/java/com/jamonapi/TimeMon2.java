package com.jamonapi;


final class TimeMon2 extends TimeMon {
    
    public TimeMon2() {
    	super(new MonKeyImp("timer","ms."),new MonInternals());
    	monData.setActivityStats(new ActivityStats());
    	monData.isTimeMonitor=true;
    }

  
    
    public String toString() {
        return getLastValue()+" ms.";
    }
    
    
    private static void testDisplay(String name, JAMonListener listener) {
       System.out.print("\n\n***"+name);
       Object[][] data=((JAMonBufferListener)listener).getDetailData().getData();
       for (int i=0;i<data.length;i++) {
    	   System.out.println();
    	   for (int j=0;j<data[i].length;j++)
    		   System.out.print(data[i][j]+", ");
       }
    }
    
	public static void main(String[] args) throws Exception {
		TimeMon mon=new TimeMon2();
		mon.addListener("value",new JAMonBufferListener("first"));
		mon.addListener("value",new JAMonBufferListener("second"));
		
		mon.removeListener("value", "first");
		mon.removeListener("value", "second");
		
		mon.add(40);
		System.out.println(mon);

		mon.addListener("value",new JAMonBufferListener("1"));
		mon.addListener("max",new JAMonBufferListener("2"));
		mon.addListener("min",new JAMonBufferListener("3"));
		mon.addListener("maxactive",new JAMonBufferListener("4"));
		
		for (int i=0;i<50;i++) {
			mon.start();
			Thread.sleep(i);
			mon.stop();
		}
		
		mon.start().start().start().stop().stop().stop();

		for (int i=0;i<50;i++) {
			mon.start();
			Thread.sleep(i);
			mon.stop();
		}

		testDisplay("value", mon.getListenerType("value").getListener());
		testDisplay("max", mon.getListenerType("max").getListener());
		testDisplay("min", mon.getListenerType("min").getListener());
		testDisplay("maxactive", mon.getListenerType("maxactive").getListener());

	}
	

    
}
