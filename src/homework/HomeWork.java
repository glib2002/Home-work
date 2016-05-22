/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Глеб
 */
public class HomeWork implements UrlOperation, GetUserOperation, PrintExodus {

    /**
     * @param args the command line arguments
     */
    public String userInput = null;
    public Scanner scanner;
    public URL playlistUrl;
    public FileOutputStream fos;
    public File file;
    public String nameFile;

    public void start() {
        getUserInput();
        urlOperation();
        dounloadFile();
        controlFile();
        printExodus();

    }

    @Override
    public void urlOperation() {
        try {
            playlistUrl = new URL(userInput);
        } catch (MalformedURLException ex) {
            Logger.getLogger(HomeWork.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void getUserInput() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.print("enter url here(example http://www.ex.ua/playlist/148363.m3u\"):");
            userInput = scanner.nextLine();
            if (userInput == null) {
                System.err.print("please reenter!!!");
                userInput = scanner.nextLine();
            }
            break;
        }
    }

    @Override
    public void printExodus() {
        //System.out.printf("\rDownloading: [%3d%]", 45);
        System.out.printf("\n\r[%-25s]", "=======>");
    }

    @Override
    public void dounloadFile() {
        try {

            try {
                playlistUrl.openStream();
            } catch (IOException ex) {
                Logger.getLogger(HomeWork.class.getName()).log(Level.SEVERE, null, ex);

            }
            System.out.println("please enter nama of file!!!");
            nameFile = scanner.nextLine();
            fos = new FileOutputStream("c:\\Users\\Глеб\\Documents\\NetBeansProjects\\HomeWork\\src\\Dounloads\\" + nameFile + ".txt");
            byte[] b = userInput.getBytes();
            try {
                fos.write(b);
            } catch (IOException ex) {
                Logger.getLogger(HomeWork.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeWork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void controlFile() {
        file = new File(userInput);
        if (file.exists()) {
            System.err.println("Please reenter name of file");
            nameFile = scanner.nextLine();
        }
    }

}
