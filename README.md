# HackerNewsApp

HackerNewsApp is designed to bring the latest stories from Hacker News straight to your Android device, showcasing modern development practices with Kotlin and Jetpack Compose.

## Features

- **Latest Stories**: Displays a continuously updated list of stories from Hacker News.
- **Responsive UI**: Adapts smoothly across different Android devices using Jetpack Compose.
- **External Links**: Opens full stories in the default web browser with a single tap.
- **Simple Navigation**: Seamlessly switch between the main story list and the app information screen.

## Architecture

Built on the MVVM architecture pattern, HackerNewsApp emphasizes a clear separation between its UI, logic layer, and data modeling.

- **View**: Rendered with Jetpack Compose, ensuring reactive UI updates.
- **ViewModel**: Manages UI-related data and handles communication between the View and Repository layers.
- **Model**: Defines the structure for Hacker News stories and other relevant data entities.

## Getting Started

To run the HackerNewsApp on your local machine, follow these steps:

1. Ensure you have Android Studio installed. You can download it from [here](https://developer.android.com/studio).
2. Clone this repository to your local machine.
3. Open the project with Android Studio.
4. Wait for Android Studio to download all the necessary Gradle dependencies.
5. Choose an emulator or connect a physical device on which to run the app.
6. Run the application through Android Studio.

## Dependencies

- **Jetpack Compose**: For building the UI.
- **Retrofit**: For network HTTP requests.
- **Jetpack Navigation-compose**: For in-app navigation.
- Additional dependencies for Material icons, ViewModel, LiveData, Coroutines, etc.

## Contributions

Contributions are welcome! If you'd like to contribute, feel free to create a pull request or open an issue.

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Acknowledgments

- Special thanks to the Hacker News API for providing stories data.
- Jetpack Compose for revolutionizing UI development on Android.

## Contact

For any queries, you can reach out to [Github](https://github.com/SanjayKhatiChhetri/HackerNewsApp/pulls) and open a PR.


