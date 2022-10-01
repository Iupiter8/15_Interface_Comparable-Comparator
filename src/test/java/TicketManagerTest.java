import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class TicketManagerTest {

    TicketByTravelTimeAscComparator travelTimeAscComparator = new TicketByTravelTimeAscComparator();
    

    private TicketRepository repo = new TicketRepository();
    private TicketManager manager = new TicketManager(repo);

    Ticket ticket1 = new Ticket(1, 4_000, "SKX", "LED", 205);
    Ticket ticket2 = new Ticket(2, 15_000, "LED", "RGK", 720);
    Ticket ticket3 = new Ticket(3, 12_000, "RGK", "GDZ", 545);
    Ticket ticket4 = new Ticket(4, 17_000, "GDZ", "VUS", 610);
    Ticket ticket5 = new Ticket(5, 8_000, "VUS", "SKX", 830);
    Ticket ticket6 = new Ticket(6, 5_400, "SKX", "LED", 415);
    Ticket ticket7 = new Ticket(7, 4_300, "SKX", "LED", 205);
    Ticket ticket8 = new Ticket(8, 5_250, "SKX", "LED", 720);
    Ticket ticket9 = new Ticket(9, 11_100, "SKX", "LED", 545);

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
    }



    @Test
    public void shouldAddTicket() {

        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddTwoIdenticalTicket() {

        manager.add(ticket1);

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket1};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldSearchOneTicketFromToAndSort() {


        Ticket[] expected = {ticket1};

        Ticket[] actual = manager.findAll("SKX", "LED");

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldSearchTicketFromToAndSort() {

        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);

        Ticket[] expected = {ticket1, ticket7, ticket8, ticket6, ticket9};

        Ticket[] actual = manager.findAll("SKX", "LED");

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldSearchTicketFromToAndSortComparator() {

        manager.add(ticket6);
        manager.add(ticket8);
        manager.add(ticket9);

        Ticket[] expected = {ticket1, ticket6, ticket9, ticket8};

        Ticket[] actual = manager.findAllSortByTravelTime("SKX", "LED", travelTimeAscComparator);

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldSearchTicketFromToAndSortComparatorIdenticalTime() {

        manager.add(ticket6);
        manager.add(ticket8);
        manager.add(ticket7);
        manager.add(ticket1);
        manager.add(ticket9);

        Ticket[] expected = {ticket1, ticket7, ticket1, ticket6, ticket9, ticket8};

        Ticket[] actual = manager.findAllSortByTravelTime("SKX", "LED", travelTimeAscComparator);

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldSearchTicketFromToAndSortWithIdenticalValue() {

        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket1);

        Ticket[] expected = {ticket1, ticket1, ticket7, ticket8, ticket6, ticket9};

        Ticket[] actual = manager.findAll("SKX", "LED");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSearchTicketInvalidFromOrInvalidTo() {

        Ticket[] expected = {};

        Ticket[] actual = manager.findAll("SKX", "VUS");

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldRemoveById() {

        manager.remove(ticket1);

        Ticket[] expected = {ticket2, ticket3};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }


}
