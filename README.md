# Dragon Ball Encyclopedia

A native Android application built with Jetpack Compose that serves as a comprehensive Dragon Ball character encyclopedia. Browse, search, and compare your favorite Dragon Ball characters.

## Features

- **Character List** - Paginated list of all Dragon Ball characters with infinite scroll
- **Character Detail** - Detailed view with stats, origin planet, and transformations
- **Favorites** - Save your favorite characters for quick access (persisted with Room)
- **Search** - Search characters by name with debounced API calls
- **VS Comparison** - Compare two characters side-by-side with a power level winner indicator
- **Offline Support** - Characters are cached locally; the app works offline with cached data

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose with Material 3
- **Architecture:** MVVM + Clean Architecture (Data / Domain / Presentation layers)
- **Dependency Injection:** Koin
- **Networking:** Retrofit + OkHttp + Gson
- **Local Storage:** Room Database
- **Image Loading:** Coil
- **Navigation:** Jetpack Navigation Compose
- **Async:** Kotlin Coroutines + Flow

## API

Data is sourced from the [Dragon Ball API](https://dragonball-api.com/api).

## Project Structure

```
com.example.dragonballapp_cikicmilos/
├── DragonBallApp.kt              # Application class (Koin init)
├── MainActivity.kt               # Single activity entry point
├── data/
│   ├── local/                    # Room database, entities, DAOs
│   ├── remote/                   # Retrofit API service, DTOs
│   └── repository/               # Repository implementations + mappers
├── domain/
│   ├── model/                    # Domain models
│   └── repository/               # Repository interfaces
├── presentation/
│   ├── navigation/               # NavHost + Screen sealed class
│   ├── theme/                    # Material 3 theme (colors, typography)
│   ├── common/                   # Shared composables (CharacterCard, Loading, Error)
│   ├── characterlist/            # Character list screen + ViewModel
│   ├── characterdetail/          # Character detail screen + ViewModel
│   ├── favorites/                # Favorites screen + ViewModel
│   ├── search/                   # Search screen + ViewModel
│   └── vscomparison/             # VS comparison screen + ViewModel
└── di/                           # Koin dependency injection modules
```

## Build

```bash
./gradlew assembleDebug
```

## Requirements

- Android Studio Hedgehog or newer
- Min SDK 24 (Android 7.0)
- Target SDK 35
