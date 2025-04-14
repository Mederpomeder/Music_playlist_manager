# Music_playlist_manager

README.md
Project Title: Music Playlist Manager
Student Name: [Your Name]
Description
The Music Playlist Manager is a command-line application designed to manage music playlists. It allows users to create, retrieve, update, delete, and manipulate playlists and their songs. The application supports various operations such as sorting, merging playlists, marking favorite songs, and generating reports. Data is stored persistently in a CSV file to ensure it is retained between sessions.

Objectives
Provide a user-friendly command-line interface for managing music playlists.
Implement CRUD (Create, Read, Update, Delete) operations for playlists and songs.
Enable advanced features like sorting, shuffling, merging, and removing duplicates.
Ensure data persistence using a CSV file.
Generate reports to provide insights into playlist data.
Follow modular and clean coding practices for maintainability.
Project Requirement List
Create a new playlist and add songs to it.
Retrieve and display all playlists and their songs.
Update the name of an existing playlist.
Delete a playlist by its name.
Sort playlists alphabetically or by the number of songs.
Shuffle playlists and display them in random order.
Mark a song as a favorite in a playlist, ensuring it always appears first.
Search for a playlist by its name.
Merge two playlists into a new playlist.
Remove duplicate songs from a playlist.
Generate a report of the most popular song across all playlists.
Documentation
Algorithms and Data Structures
Playlist Class:

Represents a playlist with a name and a list of songs.
Methods: add(), display(), update(), delete(), toCSV().
Data Persistence:

Playlists are stored in a CSV file (playlists.csv).
saveToCSV() writes playlists to the file.
readData() reads playlists from the file.
Sorting:

Uses Comparator to sort playlists alphabetically or by the number of songs.
Shuffling:

Uses Collections.shuffle() to randomize the order of playlists.
Removing Duplicates:

Uses a LinkedHashSet to remove duplicate songs while maintaining order.
Report Generation:

Uses a HashMap to count the frequency of each song and identify the most popular one.
Challenges Faced
Handling edge cases such as invalid inputs, empty playlists, and duplicate playlist names.
Ensuring data consistency when modifying playlists.
Maintaining a clean and modular code structure while implementing multiple features.
