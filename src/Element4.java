	public class Element4 {
	       private int id;
	       private int time;
	       private double  price;
	       private double volume;
	       private double rate;
	
	   // constructor
	   public Element4(int id, int time, double price, double volume, int rate) {
		  this.time=time;
	      this.id= id;
	      this.price= price;
	      this.volume= volume;
	      this.rate=rate;
	   }

       // getter
       public int getTime() { return time; }
       public int getId() { return id; }
       public double getPrice() { return price; }
       public double getVolume() { return volume; }
       public double getRate() { return rate; }
       
       // setter
       public void setTime(int time) { this.time = time; }
       public void setId(int id) { this.id = id; }
       public void setVolume(double volume) { this.volume = volume; }
       public void setPrice(double price) { this.price = price; }
       public void setRate(double rate) { this.rate = rate; }
       
       
       @Override
       public boolean equals(Object other){
    	   if (!(other instanceof Element4))return false;
    	   Element4 otherElement = (Element4)other;
           return (this.id==otherElement.id);
       }
       
       
       
    }