import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

class Playlist{
    String name;
    List<String> songs;

    Playlist(String name){
        this.name = name;
        this.songs = new ArrayList<>();
    }

    void add(String song){
        this.songs.add(song);
    }

    void display(){
        for (int i=0; i<songs.size(); i++) {
            System.out.println(songs.get(i));
        }
    }
    
    void update(String name){
        String show = this.name;
        this.name = name;
        System.out.println("Renamed successfullly!");
        System.out.println("From - "+show+" to - "+this.name);
    }

    void delete(String song){
        this.songs.remove(song);
        System.out.println("Deleted successfully!");
    }

    public String toCSV() {
        String ans = name;
        for (int i=0; i<this.songs.size(); i++) {
            ans+=","+songs.get(i);
        }
        return ans;
    }
}




class Main {

    public static void saveToCSV(String playlists, ArrayList<Playlist> newPlaylists) {
        try {
            // Read existing playlists
            ArrayList<Playlist> existingPlaylists = readData(playlists);

            // Add new playlists to the existing ones
            existingPlaylists.addAll(newPlaylists);

            // Write all playlists back to the file
            FileWriter writer = new FileWriter(playlists);
            writer.write("Name,Songs\n");
            for (Playlist p : existingPlaylists) {
                writer.write(p.toCSV() + '\n');
            }
            writer.close();
            System.out.println("Data written successfully!");
        } catch (IOException e) {
            System.out.print("Error: " + e.getMessage());
        }
    }

    public static ArrayList<Playlist> readData(String filename){
        ArrayList<Playlist> playlists = new ArrayList<>();

        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            if(scanner.hasNextLine()){
                scanner.nextLine();
            }

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] data = line.split(",");

                String name = data[0];
                Playlist playlist = new Playlist(name);
                for(int i=0;i<data.length-1;i++){
                   playlist.add(data[i+1]); 
                }
                playlists.add(playlist);
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.print("Error: File not found");
            e.printStackTrace();
        }
        return playlists;
    }

    public static void sleepMillis(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
            System.out.print("\033[H\033[2J");
            clearConsole();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        //String os = System.getProperty("os.name").toLowerCase();
        try {
            // if (os.contains("win")) {
            //     new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            // } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            //}
        } catch (Exception e) {
            System.out.println("Could not clear console.");
        }
    }

    public static void menu(int choice, String[] args){
        Scanner input = new Scanner(System.in);
        if(choice==1){
            System.out.print("Type the name of the playlist: ");
            String name = input.nextLine();
            Playlist playlist = new Playlist(name);
            System.out.print("New playlist was created successfully!\nWould you like to add songs?(1 for yes and any natural number for no): ");
            int ans = 0;
            try{
                ans = input.nextInt();
            }
            catch(InputMismatchException e){
                System.out.print("Invalid input!");
                sleepMillis(1000);
                menu(choice, args);
            }
            if(ans!=1){
                System.out.println("Playlist was created successfully!");
                sleepMillis(1000);
                main(args);
            }
            boolean stop = false;
            while(stop!=true){
                System.out.print("Name of the song: ");
                String sname = input.nextLine();
                playlist.add(sname);
                ArrayList<Playlist> jams = new ArrayList<>();
                jams.add(playlist);
                saveToCSV("playlists.csv", jams);
                System.out.print("Song is added. Add more or stop(type 1 for more/any natural number to stop)?: ");
                int anss = input.nextInt();
                if(anss==1){
                    continue;
                }
                else {
                    stop=true;
                }
            }
        }
        else if(choice==2){
            ArrayList<Playlist> playlists = readData("playlists.csv");
            for(Playlist playlist : playlists){
                System.out.println("Playlist name: "+playlist.name);
                System.out.println("Songs: ");
                playlist.display();
                System.out.println();
            }
            sleepMillis(1000);
            main(args);
        }
        else if(choice==3){
            System.out.print("Type the name of the playlist you want to update: ");
            String name = input.nextLine();
            ArrayList<Playlist> playlists = readData("playlists.csv");
            for(Playlist playlist : playlists){
                if(playlist.name.equals(name)){
                    System.out.print("Type the new name of the playlist: ");
                    String newname = input.nextLine();
                    playlist.update(newname);
                    saveToCSV("playlists.csv", playlists);
                    break;
                }
            }
        }
        else if(choice==4){
            System.out.print("Type the name of the playlist you want to delete: ");
            String name = input.nextLine();
            ArrayList<Playlist> playlists = readData("playlists.csv");
            for(Playlist playlist : playlists){
                if(playlist.name.equals(name)){
                    playlists.remove(playlist);
                    saveToCSV("playlists.csv", playlists);
                    break;
                }
            }
        }
        else if(choice==5){
            System.out.println("Sort playlists by:");
            System.out.println("1) Alphabetical order");
            System.out.println("2) Number of songs");
            int sortChoice = input.nextInt();
            ArrayList<Playlist> playlists = readData("playlists.csv");

            if(sortChoice == 1){
                playlists.sort(Comparator.comparing(p -> p.name.toLowerCase()));
                System.out.println("Playlists sorted alphabetically:");
            } else if(sortChoice == 2){
                playlists.sort(Comparator.comparingInt(p -> p.songs.size()));
                System.out.println("Playlists sorted by the number of songs:");
            } else {
                System.out.println("Invalid choice! Returning to the main menu.");
                sleepMillis(1000);
                main(args);
                return;
            }

            for(Playlist playlist : playlists){
                System.out.println("Playlist name: " + playlist.name);
                System.out.println("Number of songs: " + playlist.songs.size());
                playlist.display();
                System.out.println();
            }
            sleepMillis(1000);
            main(args);
        }
        else if(choice == 6){
            ArrayList<Playlist> playlists = readData("playlists.csv");
            Collections.shuffle(playlists); // Shuffle the playlists
            System.out.println("Playlists retrieved in random order:");
            for(Playlist playlist : playlists){
                System.out.println("Playlist name: " + playlist.name);
                System.out.println("Songs: ");
                playlist.display();
                System.out.println();
            }
            sleepMillis(1000);
            main(args);
        }
        else if(choice == 7){
            System.out.print("Type the name of the playlist to mark a favorite song: ");
            String playlistName = input.nextLine();
            ArrayList<Playlist> playlists = readData("playlists.csv");

            boolean found = false;
            for(Playlist playlist : playlists){
                if(playlist.name.equals(playlistName)){
                    found = true;
                    System.out.println("Current songs in the playlist:");
                    playlist.display();
                    System.out.print("Type the name of the song to mark as favorite: ");
                    String favoriteSong = input.nextLine();

                    if(playlist.songs.contains(favoriteSong)){
                        playlist.songs.remove(favoriteSong); // Remove the song from its current position
                        playlist.songs.add(0, favoriteSong); // Add it to the first position
                        System.out.println("Song marked as favorite successfully!");
                        saveToCSV("playlists.csv", playlists); // Save the updated playlists
                    } else {
                        System.out.println("Song not found in the playlist!");
                    }
                    break;
                }
            }

            if(!found){
                System.out.println("Playlist not found!");
            }

            sleepMillis(1000);
            main(args);
        }
        else if(choice == 8){
            System.out.print("Enter the name of the playlist to search: ");
            String searchName = input.nextLine();
            ArrayList<Playlist> playlists = readData("playlists.csv");

            boolean found = false;
            for(Playlist playlist : playlists){
                if(playlist.name.equalsIgnoreCase(searchName)){
                    found = true;
                    System.out.println("Playlist found!");
                    System.out.println("Playlist name: " + playlist.name);
                    System.out.println("Songs: ");
                    playlist.display();
                    break;
                }
            }

            if(!found){
                System.out.println("Playlist not found!");
            }

            sleepMillis(1000);
            main(args);
        }
        else if(choice == 9){
            System.out.print("Enter the name of the first playlist to merge: ");
            String firstPlaylistName = input.nextLine();
            System.out.print("Enter the name of the second playlist to merge: ");
            String secondPlaylistName = input.nextLine();
            ArrayList<Playlist> playlists = readData("playlists.csv");

            Playlist firstPlaylist = null;
            Playlist secondPlaylist = null;

            for(Playlist playlist : playlists){
                if(playlist.name.equalsIgnoreCase(firstPlaylistName)){
                    firstPlaylist = playlist;
                }
                if(playlist.name.equalsIgnoreCase(secondPlaylistName)){
                    secondPlaylist = playlist;
                }
            }

            if(firstPlaylist == null || secondPlaylist == null){
                System.out.println("One or both playlists not found!");
            } else {
                System.out.print("Enter the name of the new merged playlist: ");
                String newPlaylistName = input.nextLine();
                Playlist mergedPlaylist = new Playlist(newPlaylistName);

                mergedPlaylist.songs.addAll(firstPlaylist.songs);
                mergedPlaylist.songs.addAll(secondPlaylist.songs);

                playlists.add(mergedPlaylist);
                saveToCSV("playlists.csv", playlists);

                System.out.println("Playlists merged successfully into: " + newPlaylistName);
            }

            sleepMillis(1000);
            main(args);
        }
        else if(choice == 10){
            System.out.print("Enter the name of the playlist to remove duplicate songs: ");
            String playlistName = input.nextLine();
            ArrayList<Playlist> playlists = readData("playlists.csv");

            boolean found = false;
            for(Playlist playlist : playlists){
                if(playlist.name.equalsIgnoreCase(playlistName)){
                    found = true;
                    System.out.println("Original playlist songs:");
                    playlist.display();

                    // Remove duplicate songs
                    Set<String> uniqueSongs = new LinkedHashSet<>(playlist.songs);
                    playlist.songs = new ArrayList<>(uniqueSongs);

                    System.out.println("Updated playlist after removing duplicates:");
                    playlist.display();

                    // Save the updated playlist
                    saveToCSV("playlists.csv", playlists);
                    System.out.println("Duplicates removed successfully!");
                    break;
                }
            }

            if(!found){
                System.out.println("Playlist not found!");
            }

            sleepMillis(1000);
            main(args);
        }
        else if(choice == 11){
            ArrayList<Playlist> playlists = readData("playlists.csv");
            Map<String, Integer> songFrequency = new HashMap<>();

            // Count the frequency of each song across all playlists
            for(Playlist playlist : playlists){
                for(String song : playlist.songs){
                    songFrequency.put(song, songFrequency.getOrDefault(song, 0) + 1);
                }
            }

            // Find the most popular song
            String mostPopularSong = null;
            int maxFrequency = 0;
            for(Map.Entry<String, Integer> entry : songFrequency.entrySet()){
                if(entry.getValue() > maxFrequency){
                    mostPopularSong = entry.getKey();
                    maxFrequency = entry.getValue();
                }
            }

            if(mostPopularSong != null){
                System.out.println("Report of the Most Popular Song:");
                System.out.println("The most popular song is: " + mostPopularSong + " (appears " + maxFrequency + " times)");
            } else {
                System.out.println("No songs found in the playlists.");
            }

            sleepMillis(1000);
            main(args);
        }
        else{
            System.out.println("Invalid input! Try again");
            sleepMillis(1000);
            main(args);
        }
        System.out.println("Do you want to go back to the main menu?(1 for yes and any natural number for no): ");
        int ans = 0;
        try{
            ans = input.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input! Try again");
            sleepMillis(1000);
            main(args);
        }
        if(ans==1){
            sleepMillis(1000);
            main(args);
        }
        else{
            System.out.println("Goodbye!");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Main menu (type the number of the action to choose): ");
            System.out.println("1) Create playlist");
            System.out.println("2) Retrieve playlists");
            System.out.println("3) Update playlist");
            System.out.println("4) Delete playlist");
            System.out.println("5) Sort playlists");
            System.out.println("6) Shuffle and retrieve playlists");
            System.out.println("7) Mark a favorite song in a playlist");
            System.out.println("8) Search for a playlist by name");
            System.out.println("9) Merge two playlists into one");
            System.out.println("10) Remove duplicate songs from a playlist");
            System.out.println("11) Report of the most popular song");
            System.out.println("0) Exit");

            int choice = 0;
            try {
                choice = input.nextInt();
                input.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again.");
                input.nextLine(); // Clear invalid input
                continue;
            }

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            } else if (choice == 1) {
                System.out.print("Type the name of the playlist: ");
                String name = input.nextLine();
                Playlist playlist = new Playlist(name);
                System.out.print("Would you like to add songs? (1 for yes, any other number for no): ");
                int ans = input.nextInt();
                input.nextLine(); // Consume newline
                if (ans == 1) {
                    boolean stop = false;
                    while (!stop) {
                        System.out.print("Name of the song: ");
                        String songName = input.nextLine();
                        playlist.add(songName);
                        System.out.print("Add more songs? (1 for yes, any other number to stop): ");
                        int more = input.nextInt();
                        input.nextLine(); // Consume newline
                        if (more != 1) stop = true;
                    }
                }
                ArrayList<Playlist> playlists = new ArrayList<>();
                playlists.add(playlist);
                saveToCSV("playlists.csv", playlists);
                System.out.println("Playlist created successfully!");
            } else if (choice == 2) {
                ArrayList<Playlist> playlists = readData("playlists.csv");
                for (Playlist playlist : playlists) {
                    System.out.println("Playlist name: " + playlist.name);
                    System.out.println("Songs: ");
                    playlist.display();
                    System.out.println();
                }
            } else if (choice == 3) {
                System.out.print("Type the name of the playlist you want to update: ");
                String name = input.nextLine();
                ArrayList<Playlist> playlists = readData("playlists.csv");
                for (Playlist playlist : playlists) {
                    if (playlist.name.equals(name)) {
                        System.out.print("Type the new name of the playlist: ");
                        String newName = input.nextLine();
                        playlist.update(newName);
                        saveToCSV("playlists.csv", playlists);
                        System.out.println("Playlist updated successfully!");
                        break;
                    }
                }
            } else if (choice == 4) {
                System.out.print("Type the name of the playlist you want to delete: ");
                String name = input.nextLine();
                ArrayList<Playlist> playlists = readData("playlists.csv");
                playlists.removeIf(playlist -> playlist.name.equals(name));
                saveToCSV("playlists.csv", playlists);
                System.out.println("Playlist deleted successfully!");
            } else if (choice == 5) {
                System.out.println("Sort playlists by:");
                System.out.println("1) Alphabetical order");
                System.out.println("2) Number of songs");
                int sortChoice = input.nextInt();
                input.nextLine(); // Consume newline
                ArrayList<Playlist> playlists = readData("playlists.csv");
                if (sortChoice == 1) {
                    playlists.sort(Comparator.comparing(p -> p.name.toLowerCase()));
                    System.out.println("Playlists sorted alphabetically:");
                } else if (sortChoice == 2) {
                    playlists.sort(Comparator.comparingInt(p -> p.songs.size()));
                    System.out.println("Playlists sorted by the number of songs:");
                } else {
                    System.out.println("Invalid choice!");
                    continue;
                }
                for (Playlist playlist : playlists) {
                    System.out.println("Playlist name: " + playlist.name);
                    System.out.println("Number of songs: " + playlist.songs.size());
                    playlist.display();
                    System.out.println();
                }
            } else if (choice == 6) {
                ArrayList<Playlist> playlists = readData("playlists.csv");
                Collections.shuffle(playlists);
                System.out.println("Playlists retrieved in random order:");
                for (Playlist playlist : playlists) {
                    System.out.println("Playlist name: " + playlist.name);
                    System.out.println("Songs: ");
                    playlist.display();
                    System.out.println();
                }
            } else if (choice == 7) {
                System.out.print("Type the name of the playlist to mark a favorite song: ");
                String playlistName = input.nextLine();
                ArrayList<Playlist> playlists = readData("playlists.csv");
                for (Playlist playlist : playlists) {
                    if (playlist.name.equals(playlistName)) {
                        System.out.println("Current songs in the playlist:");
                        playlist.display();
                        System.out.print("Type the name of the song to mark as favorite: ");
                        String favoriteSong = input.nextLine();
                        if (playlist.songs.contains(favoriteSong)) {
                            playlist.songs.remove(favoriteSong);
                            playlist.songs.add(0, favoriteSong);
                            saveToCSV("playlists.csv", playlists);
                            System.out.println("Song marked as favorite successfully!");
                        } else {
                            System.out.println("Song not found in the playlist!");
                        }
                        break;
                    }
                }
            } else if (choice == 8) {
                System.out.print("Enter the name of the playlist to search: ");
                String searchName = input.nextLine();
                ArrayList<Playlist> playlists = readData("playlists.csv");
                boolean found = false;
                for (Playlist playlist : playlists) {
                    if (playlist.name.equalsIgnoreCase(searchName)) {
                        found = true;
                        System.out.println("Playlist found!");
                        System.out.println("Playlist name: " + playlist.name);
                        System.out.println("Songs: ");
                        playlist.display();
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Playlist not found!");
                }
            } else if (choice == 9) {
                System.out.print("Enter the name of the first playlist to merge: ");
                String firstPlaylistName = input.nextLine();
                System.out.print("Enter the name of the second playlist to merge: ");
                String secondPlaylistName = input.nextLine();
                ArrayList<Playlist> playlists = readData("playlists.csv");
                Playlist firstPlaylist = null, secondPlaylist = null;
                for (Playlist playlist : playlists) {
                    if (playlist.name.equalsIgnoreCase(firstPlaylistName)) {
                        firstPlaylist = playlist;
                    }
                    if (playlist.name.equalsIgnoreCase(secondPlaylistName)) {
                        secondPlaylist = playlist;
                    }
                }
                if (firstPlaylist == null || secondPlaylist == null) {
                    System.out.println("One or both playlists not found!");
                } else {
                    System.out.print("Enter the name of the new merged playlist: ");
                    String newPlaylistName = input.nextLine();
                    Playlist mergedPlaylist = new Playlist(newPlaylistName);
                    mergedPlaylist.songs.addAll(firstPlaylist.songs);
                    mergedPlaylist.songs.addAll(secondPlaylist.songs);
                    playlists.add(mergedPlaylist);
                    saveToCSV("playlists.csv", playlists);
                    System.out.println("Playlists merged successfully into: " + newPlaylistName);
                }
            } else if (choice == 10) {
                System.out.print("Enter the name of the playlist to remove duplicate songs: ");
                String playlistName = input.nextLine();
                ArrayList<Playlist> playlists = readData("playlists.csv");
                for (Playlist playlist : playlists) {
                    if (playlist.name.equalsIgnoreCase(playlistName)) {
                        System.out.println("Original playlist songs:");
                        playlist.display();
                        Set<String> uniqueSongs = new LinkedHashSet<>(playlist.songs);
                        playlist.songs = new ArrayList<>(uniqueSongs);
                        saveToCSV("playlists.csv", playlists);
                        System.out.println("Duplicates removed successfully!");
                        break;
                    }
                }
            } else if (choice == 11) {
                ArrayList<Playlist> playlists = readData("playlists.csv");
                Map<String, Integer> songFrequency = new HashMap<>();
                for (Playlist playlist : playlists) {
                    for (String song : playlist.songs) {
                        songFrequency.put(song, songFrequency.getOrDefault(song, 0) + 1);
                    }
                }
                String mostPopularSong = null;
                int maxFrequency = 0;
                for (Map.Entry<String, Integer> entry : songFrequency.entrySet()) {
                    if (entry.getValue() > maxFrequency) {
                        mostPopularSong = entry.getKey();
                        maxFrequency = entry.getValue();
                    }
                }
                if (mostPopularSong != null) {
                    System.out.println("The most popular song is: " + mostPopularSong + " (appears " + maxFrequency + " times)");
                } else {
                    System.out.println("No songs found in the playlists.");
                }
            } else {
                System.out.println("Invalid input! Try again.");
            }
        }
    }
}