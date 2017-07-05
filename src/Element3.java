	public class Element3 {
	       private int id;
	       private int time;
	       private double  price;
	       private double volume;
	
	   // constructor
	   public Element3(int id, int time, double price, double volume) {
		  this.time=time;
	      this.id= id;
	      this.price= price;
	      this.volume= volume;
	   }

       // getter
       public int getTime() { return time; }
       public int getId() { return id; }
       public double getPrice() { return price; }
       public double getVolume() { return volume; }
       
       // setter
       public void setTime(int time) { this.time = time; }
       public void setId(int id) { this.id = id; }
       public void setVolume(double volume) { this.volume = volume; }
       public void setPrice(double price) { this.price = price; }
       
       @Override
       public boolean equals(Object other){
    	   if (!(other instanceof Element3))return false;
    	   Element3 otherElement = (Element3)other;
           return (this.id==otherElement.id);
       }
       
       
       
    }