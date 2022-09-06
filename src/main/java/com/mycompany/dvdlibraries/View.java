/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dvdlibraries;

import static com.mycompany.dvdlibraries.Controller.addDvd;
import static com.mycompany.dvdlibraries.Controller.display;
import static com.mycompany.dvdlibraries.Controller.editDVD;
import static com.mycompany.dvdlibraries.Controller.exit;
import static com.mycompany.dvdlibraries.Controller.list;
import static com.mycompany.dvdlibraries.Controller.loadDvds;
import static com.mycompany.dvdlibraries.Controller.removeDvd;
import static com.mycompany.dvdlibraries.Controller.save;
import static com.mycompany.dvdlibraries.Controller.search;
import static com.mycompany.dvdlibraries.Controller.setFile;
import java.util.Scanner;

/**
 *@author Kim
 *email: 
 *date: current date
 *purpose: Practicing programming
 */
public class View {
  static boolean using = true;
    
    static String FILE_PATH = "src/DVDLibraries/dvds.txt";

    /**
     * Main menu interface and default location from which user can make decisions.
     */
    public static void menu() {

        System.out.println("=============================================");
        System.out.println("Please choose an action by entering a number:");
        System.out.println("1. Add Movie ");
        System.out.println("2. List Movies");
        System.out.println("3. Save file ");
         System.out.println("4. Load file");
        System.out.println("5. Search for Movie");
        System.out.println("6. Change Save location");
        System.out.println("7. Exit");
        System.out.println("=============================================");

        Scanner in = new Scanner(System.in);

        int choice = in.nextInt();

        switch(choice) {
            case 1:
                addMovie();
                break;
            case 2:
                listMovie();
                break;
            case 3:
                saveFile();
                break;
            case 4:
                loadFile();
                break;
            case 5:
                searchMovie();
                break;
            case 6:
                saveLocation();
            case 7:
                exiting();
                break;
            default:
                menu();
        }
    }

    /**
     * Takes user input to send to the addDvd function in Controller
     */
    public static void addMovie() {

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the movie's title:");
        String title = in.nextLine();
        System.out.println("Please enter the movie's release date (ddmmyyyy):");
        int date = Integer.parseInt(in.nextLine());
        System.out.println("Please enter the movie's MPAA rating:");
        int mpaa = Integer.parseInt(in.nextLine());
        System.out.println("Please enter the movie's director:");
        String director = in.nextLine();
        System.out.println("Please enter the movie's studio:");
        String studio = in.nextLine();
        System.out.println("User ratings:");
        String userRating = in.nextLine();
        addDvd(title, date, mpaa, director, studio, userRating);
        displayMovie(search(title));
    }

    /**
     * Calls removeDvd from Controller and then takes user back to menu
     * @param dvd to remove
     */
    public static void delMovie(DVD dvd) {

        removeDvd(dvd);
        menu();
    }

    /**
     * Takes in new values for a DVD that user enters then takes user to the display page for dvd.
     * @param dvd to be edited
     */
    public static void editMovie(DVD dvd) {

        Scanner in  = new Scanner(System.in);
        System.out.println("Input Ttitle or leave blank to leave unchanged");
        String title = in.nextLine();

        System.out.println("Input Release date or leave blank to leave unchanged");
        String date = in.nextLine();

        System.out.println("Input MPAA or leave blank to leave unchanged");
        String mpaa = in.nextLine();

        System.out.println("Input Director or leave blank to leave unchanged");
        String director = in.nextLine();

        System.out.println("Input Studio or leave blank to leave unchanged");
        String studio = in.nextLine();

        System.out.println("Input User rating or leave blank to leave unchanged");
        String userRating = in.nextLine();

        editDVD(dvd, title, date, mpaa, director, studio, userRating);
        displayMovie(dvd);
    }

    /**
     * calls list method in Controller then takes user back to menu
     */
    public static void listMovie() {

        System.out.println("Here are all movie titles...");
        list();
        menu();
    }

    /**
     * Asks for input to search list for film, then calls the search function and takes user to display page for DVD.
     */
    public static void searchMovie() {

        System.out.println("Enter the movie's title");
        Scanner in = new Scanner(System.in);
        String title = in.nextLine();
        System.out.println(title);
        DVD dvd = new DVD();
        try {
            dvd = search(title);
            displayMovie(dvd);
        } catch (Exception e) {
            System.out.println("Could not find movie");
            menu();
        }

        System.out.println("=============================================");
        menu();
    }

    /**
     * Provides user with options involving the param DVD.
     * @param dvd displayed and DVD which options can be executed on.
     */
    public static void displayMovie(DVD dvd) {
        System.out.println("=============================================");
        display(dvd);

        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("Would you like to edit or delete this movie?");
        System.out.println("1. Edit");
        System.out.println("2. Delete");
        System.out.println("3. Return to menu");
        System.out.println("---------------------------------");

        int choice = in.nextInt();
        switch(choice) {
            case 1:
                editMovie(dvd);
                break;
            case 2:
                delMovie(dvd);
                break;
            case 3:
                menu();

        }

    }

    /**
     * Calls load method in Controller then takes user back to menu
     */
    public static void loadFile() {
        try {
            loadDvds();
        } catch(Exception e) {
            System.out.println("File not found");
        }
        menu();
    }

    /**
     * Calls save method in Controller then takes user back to menu
     */
    public static void saveFile() {
        save();
        menu();
    }

    /**
     * Takes user input to call setFile in Controller. This changes the path which the app saves/loads.
     * Then takes user back to menu.
     */
    public static void saveLocation() {
        System.out.println("Enter new file location");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();

        setFile(path);
        menu();

    }

    /**
     * Calls exit function in Controller
     */
    public static void exiting() {
        System.out.println("Thank you, Goodbye!");
        exit();
    }

}
