public class Mobile {      //Mobile class
    String model_name;
    String colour;
    int battery_life;
    int mobile_price;

    void print_details(){         //function for displaying details
        System.out.println("Model:"+model_name);
        System.out.println("Colour:"+colour);
        System.out.println("Battery Life:"+battery_life+" hours");
        System.out.println("Price:$"+mobile_price);
    }
    Mobile(){    //Default Constructor
        model_name = "Google Pixel R9";
        colour = "Vanta Black";
        battery_life = 14;
        mobile_price = 1200;
    }
    Mobile(String mod,String col,int bat,int p){   //Parameterized Constructor
            model_name = mod;
            colour = col;
            battery_life = bat;
            mobile_price = p;
    }

    Mobile(Mobile m){  //Copy Constructor
        model_name = m.model_name;
        colour = m.colour;
        battery_life = m.battery_life;
        mobile_price = m.mobile_price;
    }

    public static void main(String[] args) {      //Main function
        Mobile m1 = new Mobile();  //Default constructor object
        m1.print_details();
        System.out.print("\n");
        Mobile m2 = new Mobile("Iphone","Red",9,2400);  //Parameterized constructor Object
        m2.print_details();
        System.out.print("\n");
        Mobile m3 = new Mobile(m2);   //Copy Constructor
        m3.print_details();
        System.out.print("\n");
        Mobile m4 = new Mobile("Oneplus 10R","Grey",10,2300);
        m4.print_details();

    }

}
