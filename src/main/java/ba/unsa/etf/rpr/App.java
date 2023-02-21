package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.business.TourManager;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Tour;
import org.apache.commons.cli.*;

import java.io.PrintWriter;

/**
 * CLI (Command Line Interface) implementation
 */
public class App {

    private static final Option addCustomer = new Option("ac", "add-customer", false, "Adding a new customer to the database");
    private static final Option deleteCustomer = new Option("dc", "delete-customer", false, "Deleting a customer from the database");
    private static final Option addTour = new Option("at", "add-tour", false, "Adding a new tour to the database");
    private static final Option deleteTour = new Option("dt", "delete-tour", false, "Deleting a tour from the database");
    private static final Option getCustomers = new Option("getC", "get-customers", false, "Printing all customers from the database");
    private static final Option getTours = new Option("getT", "get-tours", false, "Printing all tours from the database");

    /**
     * Print formatted options.
     *
     * @param options the options
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar rpr-projekat-cli-jar-with-dependencies.jar [option] (parameters)");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    /**
     * Add options.
     *
     * @return the options
     */
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addCustomer);
        options.addOption(deleteCustomer);
        options.addOption(addTour);
        options.addOption(deleteTour);
        options.addOption(getCustomers);
        options.addOption(getTours);

        return options;
    }

    public static void main(String[] args) throws Exception {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, args);
        CustomerManager customerManager = new CustomerManager();
        TourManager tourManager = new TourManager();

        if(commandLine.hasOption(addCustomer.getOpt()) || commandLine.hasOption(addCustomer.getLongOpt())){
            try{
                Customer customer = new Customer();
                customer.setFirst_name(commandLine.getArgList().get(0));
                customer.setLast_name(commandLine.getArgList().get(1));
                customer.setGender(commandLine.getArgList().get(2));
                customer.setPhone_number(commandLine.getArgList().get(3));
                customer.setEmail(commandLine.getArgList().get(4));
                customer.setPassword(commandLine.getArgList().get(5));
                customerManager.add(customer);
                System.out.println("Customer successfully added to the database!");
            }catch(Exception e){
                System.out.println("Error! Invalid parameters!");
                printFormattedOptions(options);
                System.exit(-1);
            }
        }
        else if(commandLine.hasOption(deleteCustomer.getOpt()) || commandLine.hasOption(deleteCustomer.getLongOpt())){
            try{
                Customer customer = new Customer();
                customer = customerManager.getByEmail(commandLine.getArgList().get(0));
                customerManager.delete(customer.getId());
                System.out.println("Customer successfully deleted from the database");
            }catch(Exception e){
                System.out.println("Customer with given email does not exist!");
                System.exit(-1);
            }
        }
        else if(commandLine.hasOption(getCustomers.getOpt()) || commandLine.hasOption(getCustomers.getLongOpt())){
            customerManager.getAll().forEach(f -> System.out.println(f.getEmail()));
        }
        else if(commandLine.hasOption(addTour.getOpt()) || commandLine.hasOption(addTour.getLongOpt())){
            try{
                Tour tour = new Tour();
                tour.setDestination(commandLine.getArgList().get(0));
                tour.setPrice(Double.valueOf(commandLine.getArgList().get(1)));
                tourManager.add(tour);
                System.out.println("Tour successfully added to the database!");
            }catch(Exception e){
                System.out.println("Error! Invalid parameters!");
                printFormattedOptions(options);
                System.exit(-1);
            }
        }
        else if(commandLine.hasOption(deleteTour.getOpt()) || commandLine.hasOption(deleteTour.getLongOpt())){
            try{
                Tour tour = new Tour();
                tour = tourManager.searchByDestination(commandLine.getArgList().get(0));
                tourManager.delete(tour.getId());
            }catch(Exception e){
                System.out.println("Tour with given destination does not exist!");
                System.exit(-1);
            }
        }
        else if(commandLine.hasOption(getTours.getOpt()) || commandLine.hasOption(getTours.getLongOpt())){
            tourManager.getAll().forEach(f -> System.out.println(f.getDestination()));
        }
        else{
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}
