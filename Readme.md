# WatchSGS

WatchSGS is an android app that demonstrates the use of Jetpack Compose, MVVM, RxKotlin along with Watchmode API
to recommend Movies & TV shows.

## Features
- Allows the user to view Movie and TV show recommendations with the help of [Watchmode API](https://api.watchmode.com/).
- Built on Material 3 UI which is a Google’s open-source design system.
- Allows users to see the some basic details such as Poster, Plot overview and also the trailer if available. 

## Architecture & Technologies
- **MVVM (Model-View-ViewModel) Architecture** for clean and maintainable code.
- **Hilt** A dependency injection library for Android.
- **Jetpack Compose** A toolkit for building native Android UI in a declarative way.
- **Retrofit** for efficient API communication and networking.
- **RxKotlin** for handling multiple API calls concurrently and results efficiently.

## Challenges Faced
- The **Watchmode API** provides two endpoints: `list-titles` and `releases`.
    - The `releases` API includes a **poster URL** in its response but lacks a **types** parameter, making it unsuitable for distinguishing between Movies and TV Shows.
    - The `list-titles` API provides **title IDs and basic information (name, year)** but does not include **poster URLs**.
- To handle this, I assumed that the **titles API** should be used since separate tabs for **Movies and TV Shows** were required, along with separate API calls for them.

## Assumptions Made
- Since **poster URLs** were missing in the `list-titles` API, I implemented a placeholder image on the home screen cards to maintain UI consistency and for future improvement purpose.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Add your [Watchmode API Key](https://api.watchmode.com/) in com.daniel.watchsgs.util.constants
4. Build and run the application.

## Sample Video
https://github.com/user-attachments/assets/4b818dee-aaa0-4afe-995a-ddd9b2406995

## Credits
- Vectors and icons by [SVG Repo](https://www.svgrepo.com)
---
