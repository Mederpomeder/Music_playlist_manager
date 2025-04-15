# Music_playlist_manager

presentation: https://docs.google.com/presentation/d/1PnJaXhOzP6cWIb7hauCqbF7XqUiW__F3/edit?usp=sharing&ouid=109928450729421069302&rtpof=true&sd=true

---

# 🎵 Music Playlist Manager

## 👤 Student Name
**Sakybaev Meder**

---

## 📄 Description
The **Music Playlist Manager** is a Java-based command-line application that allows users to manage a collection of songs through a series of CRUD (Create, Read, Update, Delete) operations. This system uses file-based persistence to ensure data is saved between sessions and emphasizes modular design, input validation, and usability.

---

## 🎯 Objectives
- Develop a modular and user-friendly command-line application in Java.
- Implement all CRUD functionalities for managing a music playlist.
- Utilize file handling for persistent storage of song data.
- Provide data validation to handle incorrect user input gracefully.
- Export and import playlists through external files.
- Generate reports to summarize the playlist content.
- Ensure the code is clean, structured, and well-documented.
- Design test cases and verify output correctness.
- Demonstrate implementation via screenshots.
- Follow good software engineering practices, including version control and modular architecture.

---

## ✅ Project Requirement List
1. Add a new song to the playlist.
2. View all songs in the playlist.
3. Update song information (title, artist, genre, etc.).
4. Delete a song from the playlist.
5. Search songs by title or artist.
6. Export the playlist to a file.
7. Import playlist from a file.
8. Display playlist statistics (e.g., total songs, genres).
9. Persist data using file storage.
10. Handle invalid inputs with appropriate error messages.
11. Follow modular programming structure.
12. Save and load playlist automatically on startup/exit.

---

## 📚 Documentation

### 🧠 Algorithms & Data Structures
- **Data Structure Used**: `ArrayList<Song>` to store songs dynamically.
- **File Handling**: `BufferedReader` and `BufferedWriter` for reading/writing playlist files.
- **Searching**: Linear search for title/artist matching.
- **Sorting**: Custom comparator and loop-based analytics for reports.

### 🧩 Modules
- `Main.java` - Entry point of the application.
- `Song.java` - Represents the Song object with properties like title, artist, album, and genre.
- `PlaylistManager.java` - Handles core playlist logic (CRUD, file I/O, search, report).
- `InputValidator.java` - Utility class for safe and validated input collection.

### ⚠️ Challenges Faced
- Handling file corruption or missing files gracefully.
- Ensuring modularity without overcomplicating the architecture.
- Building a robust import/export mechanism that supports format consistency.

---

## 🧪 Test Cases and Outputs

### Test Case 1: Add a Song  
**Input:**  
```
Title: Shape of You  
Artist: Ed Sheeran  
Genre: Pop  
```
**Expected Output:**  
```
Song added successfully.
```

---

### Test Case 2: View Playlist  
**Expected Output:**  
```
1. Shape of You - Ed Sheeran [Pop]
```

---

### Test Case 3: Delete Song  
**Input:**  
```
Enter song title to delete: Shape of You  
```
**Expected Output:**  
```
Song deleted successfully.
```

---

### Test Case 4: Export Playlist  
**Input:**  
```
Enter filename to export: myplaylist.txt  
```
**Expected Output:**  
```
Playlist exported to myplaylist.txt
```

---

### 📸 Test Sample Screenshots

#### Add PLaylist  
![Screenshot from 2025-04-16 03-31-15](https://github.com/user-attachments/assets/4c1eb1cb-618f-4969-a67c-d5e99cf056cf)


#### View Playlist  
![Screenshot from 2025-04-16 03-34-16](https://github.com/user-attachments/assets/0f105071-16b8-4379-91f3-bd8482e964cd)


#### Sort playlists by alphabetiacal order  
![Screenshot from 2025-04-16 03-36-07](https://github.com/user-attachments/assets/70011553-2445-4be3-b530-100679c5a569)


---

## 🧼 Code Quality
- Fully functional Java code.
- Follows naming conventions and modular structure.
- Code is clean with minimal inline comments to improve readability.

---

## 📁 Files
- `playlists.csv` – Stores the user’s music playlist persistently.
- `test_samples` – File with input output examples.
- `README.md` - File with documentation definitions and explanation
- All files used for data storage are placed in the root or `/data/` directory.

---

Let me know if you'd like to include GitHub links, license, or contribution guidelines!

