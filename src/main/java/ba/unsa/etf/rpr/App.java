package ba.unsa.etf.rpr;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

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

    }
}
