/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dvdlibraries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *@author Kim
 *email: 
 *date: current date
 *purpose: Practicing programming
 */
public class Controller {
static ArrayList<DVD> dvds = new ArrayList<>();

    static String FILE = "src/DVDLibrary/dvds.txt";
    

    public static void main(String args[]) {

        try {
            loadDvds();
        } catch (Exception e) {
            System.out.println("File not loaded");
        }

        View view = new View();

        while(view.using) {
            view.menu();
        }

        list();
        save();

    }

    /**
     *
     * @param dvd Takes in a DVD object and displays all of the DVD's information
     */
    public static void display(DVD dvd) {
        System.out.println("Title: " + dvd.getTitle());
        System.out.println("Release Date: " + dvd.getDate());
        System.out.println("MPAA: " + dvd.getMpaa());
        System.out.println("Director: " + dvd.getDirector());
        System.out.println("Studio: " + dvd.getStudio());
        System.out.println("User Comment: " + dvd.getUserRating());
    }

    /**
     * Lists all DVD's in the library by title.
     */
    public static void list() {

        for (DVD dvd : dvds) {
            System.out.println(dvd.getTitle());
        }
    }

    /**
     *  Searches the list of films for the DVD object with the same title.
     * @param title of the DVD
     * @return the DVD object, or null if there is no film of that title.
     */
    public static DVD search(String title) {
        for (DVD dvd : dvds) {
            if (title.equalsIgnoreCase(dvd.getTitle())) {
                return dvd;
            }
        }

        return null;
    }

    /**
     * Creates a new DVD object with params and adds it to list
     * @param title of the new DVD
     * @param date of the new DVD
     * @param mpaa of the new DVD
     * @param director of the new DVD
     * @param studio of the new DVD
     * @param userRating of the new DVD
     */
    public static void addDvd(String title, int date, int mpaa, String director, String studio, String userRating) {
        if (!dupCheck(title)) {
            DVD dvd = new DVD(title, date, mpaa, director, studio, userRating);
            dvds.add(dvd);
            save();
        } else {
            System.out.println("Movie already in a list");
        }
    }

    /**
     *  Removes DVD object from list of DVD's.
     * @param dvd to remove
     */
    public static void removeDvd(DVD dvd) {
        System.out.println("Deleted " + dvd.getTitle());
        dvds.remove(dvd);
        //save();
    }

    /**
     * Edits DVD information with params. If param is blank, the value is not edited.
     * @param dvd to be edited
     * @param title new title
     * @param date new date
     * @param mpaa new mpaa
     * @param director new director
     * @param studio new studio
     * @param userRating new userRating
     */
    public static void editDVD(DVD dvd, String title, String date, String mpaa, String director, String studio, String userRating) {

        if (!title.equals("")) {
            dvd.setTitle(title);
        }

        if (!date.equals("")) {
            dvd.setDate(Integer.parseInt(date));
        }

        if (!mpaa.equals("")) {
            dvd.setMpaa(Integer.parseInt(mpaa));
        }

        if (!director.equals("")) {
            dvd.setDirector(director);
        }

        if (!studio.equals("")) {
            dvd.setStudio(studio);
        }

        if (!userRating.equals("")) {
            dvd.setUserRating(userRating);
        }
        save();
    }

    /**
     * Loads DVDs from a path defined in a global static variable int a list of DVD objects.
     * @throws FileNotFoundException
     */
    public static void loadDvds() throws FileNotFoundException {
        System.out.println("Loading from " + FILE);
        File txt = new File(FILE);
        //Scanner loader = new Scanner(new FileReader(file));

        Scanner loader = new Scanner(txt);

        ArrayList<DVD> dvdss = new ArrayList<>();

        while (loader.hasNextLine()) {
            String line = loader.nextLine();

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            String title = lineScanner.next();
            int date = lineScanner.nextInt();
            int mpaa = lineScanner.nextInt();
            String director = lineScanner.next();
            String studio = lineScanner.next();
            try {
                String userRating = lineScanner.next();
                DVD dvd = new DVD(title, date, mpaa, director, studio, userRating);
                dvdss.add(dvd);
            } catch(Exception e) {
                DVD dvd = new DVD(title, date, mpaa, director, studio);
                dvdss.add(dvd);
            }
        }

        dvds = dvdss;

    }

    /**
     *  Saves the list of DVDs to the globally defined static file path.
     */
    public static void save() {
        System.out.println("Saved...");
        try {
            //FileWriter writer = new FileWriter("src/DVDsaves/dvd.csv");
            FileWriter writer = new FileWriter(FILE);

            for (DVD dvd : dvds) {
                String inputDvd = dvd.getTitle() + "," + Integer.toString(dvd.getDate()) + "," +
                        Integer.toString(dvd.getMpaa()) + "," + dvd.getDirector() + "," +
                        dvd.getStudio() + "," + dvd.getUserRating();
                writer.write(inputDvd);
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("File not saved");
        }
    }

    /**
     * Checks if there is already a film with param title in list of films
     * @param title of film in question
     * @return true if film exists already, false if not.
     */
    public static boolean dupCheck(String title) {

        for (DVD dvd : dvds) {
            if (dvd.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Manually change save and load location.
     * @param path new save/load location.
     */
    public static void setFile(String path) {
        FILE = path;
    }

    /**
     * Exits the program with exit code 0
     */
    public static void exit() {
        System.exit(0);
    }

}
