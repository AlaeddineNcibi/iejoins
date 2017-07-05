	public class Element {
	       private int id;
	       private double  price;
	       private double volume;
	
	   // constructor
	   public Element(int id, double price, double volume) {
	      this.id= id;
	      this.price= price;
	      this.volume= volume;
	   }

       // getter
       public int getId() { return id; }
       public double getPrice() { return price; }
       public double getVolume() { return volume; }
       
       // setter
       public void setId(int id) { this.id = id; }
       public void setVolume(double volume) { this.volume = volume; }
       public void setPrice(double price) { this.price = price; }
       
       @Override
       public boolean equals(Object other){
    	   if (!(other instanceof Element))return false;
    	   Element otherElement = (Element)other;
           return (this.id==otherElement.id);
       }
       
    }