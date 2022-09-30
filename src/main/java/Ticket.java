public class Ticket implements Comparable<Ticket> {

    protected int id;
    protected int price;
    protected String departureAirport;
    protected String arrivalAirport;
    protected int travelTime;


    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }

    public String getDepartureAirport() {
        return departureAirport;
    }

//    public void setDepartureAirport(String departureAirport) {
//        this.departureAirport = departureAirport;
//    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

//    public void setArrivalAirport(String arrivalAirport) {
//        this.arrivalAirport = arrivalAirport;
//    }
//
//    public double getTravelTime() {
//        return travelTime;
//    }
//
//    public void setTravelTime(double travelTime) {
//        this.travelTime = travelTime;
//    }


    public Ticket(int id, int price, String departureAirport, String arrivalAirport, int travelTime) {
        this.id = id;
        this.price = price;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.travelTime = travelTime;
    }


    @Override
    public int compareTo(Ticket o) {
        if (this.price < o.price) {
            return -1;
        } else if (this.price > o.price) {
            return 1;
        } else {
            return 0;
        }
    }


}
