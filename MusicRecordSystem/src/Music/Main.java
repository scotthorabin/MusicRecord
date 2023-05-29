package Music;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Record> records = loadRecords();

    // This is the main menu
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Hello and Welcome");
            System.out.println("Please enter one of the options");
            System.out.println("1: Create a new music record");
            System.out.println("2: Edit a music record");
            System.out.println("3: Delete a record");
            System.out.println("4: Look at current records");
            System.out.println("0: End Project");

            // Switch statement that allows the user to navigate through the different methods
            switch (scanner.nextInt()) {

                case 0:
                    System.out.println("Exiting...");
                    return;

                case 1:
                    Record newRecord = createMusicRecord();
                    records.add(newRecord);
                    saveRecords(records);
                    break;

                case 2:

                    editMusicRecord();
                    break;

                case 3:
                    deleteMusicRecord();
                    break;


                case 4:
                    SongNameComparator songNameComparator = new SongNameComparator();
                    Collections.sort(records,songNameComparator);
                    for (Record record: records
                         ) {
                        System.out.println(record);

                    }

                    break;

                default:
                    System.out.println("Invalid input, please try again");
            }
        }
    }


    static Record createMusicRecord() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please add the song name");
                String userSongName = scanner.nextLine();
                System.out.println("Please add the song artist");
                String userSongArtist = scanner.nextLine();
                System.out.println("Please add the date that the song was released");
                int userDateReleased = scanner.nextInt();
                System.out.println("This is what you put");
                Record newRecord = new Record(userSongName, userSongArtist, userDateReleased);
                System.out.println(newRecord);
                return newRecord;
            } catch (InputMismatchException i ) {
                System.out.println("Wrong input, please try again");
                return createMusicRecord();
            }
        }
    }

    // This will allow a user to edit a specific Record
    static Record editMusicRecord() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("What record do you want to change? (Starts from 0 for record 1)");
                Record recordBeingEdited = records.get(scanner.nextInt());
                System.out.println(recordBeingEdited);
                System.out.println("Is this the record you want to edit? (Enter 1 to continue, Enter 2 to return, Enter 3 to return to main menu)");
                int yesOrNo = scanner.nextInt();
                // If YES (1) continue through the method
                // If NO (2) Then break and return to the beginning of editMusicRecord method
                // If user enters (3) then they will return to the main menu
                if (yesOrNo == 1){
                } else if (yesOrNo == 2) {
                    break;
                } else if (yesOrNo == 3) {
                    return recordBeingEdited;
                } else {
                    System.out.println("Please try again");
                    break;
                }


                System.out.println("What part of the record do you want to edit?");
                System.out.println("1: Song Name");
                System.out.println("2: Song Artist");
                System.out.println("3: Date Released");
                switch (scanner.nextInt()) {
                    case 1: {
                        System.out.println(recordBeingEdited);
                        System.out.println("Please enter the change");
                        scanner.nextLine();
                        recordBeingEdited.setSongName(scanner.nextLine());
                        System.out.println("This is the change you made " + recordBeingEdited);
                        saveRecords(records);
                        return recordBeingEdited;
                    }

                    case 2: {
                        System.out.println(recordBeingEdited);
                        System.out.println("Please enter the change");
                        scanner.nextLine();
                        recordBeingEdited.setSongArtist(scanner.nextLine());
                        System.out.println("This is the change you made " + recordBeingEdited);
                        saveRecords(records);
                        return recordBeingEdited;
                    }

                    case 3: {
                        System.out.println(recordBeingEdited);
                        System.out.println("Please enter the change");
                        scanner.nextLine();
                        recordBeingEdited.setDateReleased(scanner.nextInt());
                        System.out.println("This is the change you made " + recordBeingEdited);
                        saveRecords(records);
                        return recordBeingEdited;
                    }
                    default:
                        System.out.println("Invalid Input");
                }
                return recordBeingEdited;
            } catch (InputMismatchException i ){
                System.out.println("Wrong input, please try again");
                return editMusicRecord();
            } catch (IndexOutOfBoundsException i){
                System.out.println("This is currently out of bounds, Please try again");
                break;
            }
        }
        return editMusicRecord();
    }

    // This will delete a record that the user wants to get rid of
    static Record deleteMusicRecord() {
        while (true) {
            Record userInputBeingRead = null;
            try {
                Scanner scanner = new Scanner(System.in);
                SongNameComparator songNameComparator = new SongNameComparator();
                Collections.sort(records, songNameComparator);
                for (Record record : records
                ) {
                    System.out.println(record);

                }
                System.out.println("What record do you want to delete? (Enter 0 for record 1 etc)");
                userInputBeingRead = records.get(scanner.nextInt());
                System.out.println(userInputBeingRead);
                System.out.println("Are you sure you want this record to be deleted? (Enter 1 for yes, Enter 2 for no, Enter 3 to return to main menu)");

                switch (scanner.nextInt()) {
                    case 1: {
                        System.out.println("Deleting...");
                        Object processOfDeletion = userInputBeingRead;
                        records.remove(processOfDeletion);
                        System.out.println("Deleted!");
                        saveRecords(records);
                        return userInputBeingRead;
                    }

                    case 2: {
                        return deleteMusicRecord();
                    }

                    case 3: {
                        System.out.println("Returning to main menu...");
                        return userInputBeingRead;
                    }

                    default: {
                        System.out.println("Please Try Again");
                        break;
                    }
                }
            } catch (InputMismatchException i) {
                System.out.println("Please Try Again");
                return deleteMusicRecord();
            } catch (IndexOutOfBoundsException i) {
                System.out.println("Record number you have entered is not here, please try again");
                return deleteMusicRecord();
            }
        }
    }




    // This saves the records that the user has created
    public static void saveRecords(ArrayList<Record> newRecord){
        // Try-Catch method is used in case the record.dat file is not located
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("record.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(newRecord);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to create object output stream: " + e.getMessage() );
        }

    }


    // This loads a record that the user has previously created
    public static ArrayList<Record> loadRecords(){
        // Try-Catch method is used in case the record.dat file is not located
        try {
            FileInputStream fileInputStream = new FileInputStream("record.dat");
           ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Record> records = (ArrayList<Record>) objectInputStream.readObject();
            return records;

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error converting data to object " + e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("Can't find class representing saved object: " + e.getMessage());
        }
            return new ArrayList<>();
    }
}


