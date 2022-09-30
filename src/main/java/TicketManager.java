import java.util.Arrays;

public class TicketManager {

    private TicketRepository repo;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public Ticket[] add(Ticket ticket) {
        repo.save(ticket);
        return repo.findAll();
    }

    public Ticket[] remove(Ticket ticket) {
        repo.removeById(ticket.id);
        return repo.findAll();
    }


    public Ticket[] findAll(String departureAirport, String arrivalAirport) {
        Ticket[] result = new Ticket[0]; //тут сохранятся подощедшие по запросу
        for (Ticket ticket : repo.findAll()) {
            if (matches(ticket, departureAirport, arrivalAirport)) {
                //добавляем в конец массива result продукт product
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);

                //                for (int i = 0; i < result.length; i++) {
                //                    tmp[i] = result[i];
                //                }

                tmp[tmp.length - 1] = ticket;
                result = tmp;

            }
        }
        Arrays.sort(result);
        return result;
    }

    // метод определения соответствия товара product запросу search

    public boolean matches(Ticket ticket, String searchFrom, String searchTo) {

        if (ticket.getDepartureAirport().contains(searchFrom) && ticket.getArrivalAirport().contains(searchTo)) {
            return true;
        } else {
            return false;
        }
    }


}
