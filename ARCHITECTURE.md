# MyTasks App Architecture

## Overview
MyTasks is built using the MVVM (Model-View-ViewModel) architecture pattern with Repository pattern for data management.

## Architecture Layers

### 1. Data Layer (`data/`)
**Purpose**: Defines data models and database operations

- **Task.kt**: Core data model with Priority and TaskStatus enums
  - Fields: id, title, description, priority, status, dates
  - Priority levels: HIGH (Red), MEDIUM (Orange), LOW (Green)
  - Status types: PENDING, IN_PROGRESS, COMPLETED

- **TaskDao.kt**: Data Access Object for database operations
  - CRUD operations for tasks
  - Query methods for filtering by status/priority
  - Flow-based reactive queries

- **TaskDatabase.kt**: Room database singleton
  - Single source of truth for task data
  - Thread-safe database access

- **Converters.kt**: Type converters for Room
  - Converts enums to/from database format

### 2. Repository Layer (`repository/`)
**Purpose**: Abstracts data sources and provides clean API

- **TaskRepository.kt**: Mediates between ViewModels and data sources
  - Exposes Flow streams for reactive UI updates
  - Handles all database operations
  - Single source of data for the app

### 3. ViewModel Layer (`viewmodel/`)
**Purpose**: Manages UI-related data and business logic

- **TaskViewModel.kt**: 
  - Observes task data from repository
  - Provides LiveData for UI observation
  - Handles user actions (create, update, delete)
  - Manages app state across configuration changes

### 4. UI Layer (`ui/`)
**Purpose**: Presents data and handles user interactions

- **MainActivity.kt**: Home screen with summary and task list
  - Displays task statistics (pending, in-progress, completed)
  - Tab-based filtering
  - RecyclerView with task cards
  
- **TaskDetailActivity.kt**: Create/edit task screen
  - Form for task details
  - Priority and status selection
  - Save/delete operations

- **DailyBriefActivity.kt**: Daily brief input screen
  - Text input for daily brief
  - Task extraction on submit

- **TaskAdapter.kt**: RecyclerView adapter
  - Displays task items with color-coded priorities
  - Handles item click events

### 5. Notification Layer (`notification/`)
**Purpose**: Manages scheduled reminders

- **DailyBriefWorker.kt**: WorkManager worker for scheduled tasks
  - Runs daily at 7 PM
  - Shows notification to prompt daily brief

- **DailyBriefReminderReceiver.kt**: BroadcastReceiver for alarms
  - Handles alarm intents
  - Displays notifications

### 6. Utility Layer (`utils/`)
**Purpose**: Helper classes and utilities

- **TaskExtractor.kt**: Natural language processing for tasks
  - Extracts tasks from free-form text
  - Identifies priority keywords (urgent, important, etc.)
  - Parses temporal references (tomorrow, next week, etc.)
  - Creates Task objects automatically

### 7. Application Class
- **TaskApplication.kt**: 
  - Initializes app-wide components
  - Schedules daily brief reminders on app startup

## Data Flow

### Reading Data:
```
Database (Room) 
  → TaskDao (Flow)
    → TaskRepository (Flow)
      → TaskViewModel (LiveData)
        → UI (Observer)
```

### Writing Data:
```
UI Action 
  → ViewModel (Coroutine)
    → Repository (suspend fun)
      → TaskDao (suspend fun)
        → Database
          → Flow emission
            → UI Update
```

## Key Features Implementation

### 1. Summary Dashboard
- Uses LiveData to observe task counts by status
- Real-time updates when tasks change
- Color-coded cards for visual clarity

### 2. Task Filtering
- Tab-based filtering using different Flow streams
- Each tab observes a different query result
- Efficient filtering at database level

### 3. Priority Color Coding
- Enum-based priority system
- Each priority has associated color code
- Applied in UI layer via TaskAdapter

### 4. Daily Brief & Task Extraction
- WorkManager schedules daily notification at 7 PM
- TaskExtractor analyzes text for:
  - Action verbs (need to, must, should, etc.)
  - Priority keywords (urgent, important, etc.)
  - Date references (tomorrow, next week, etc.)
- Creates tasks with appropriate priority and dates

### 5. Persistence
- Room database ensures data survives app restarts
- All operations are asynchronous (Coroutines)
- Database migrations handled automatically

## Threading Model
- **Main Thread**: UI operations only
- **IO Thread**: All database operations (via Coroutines)
- **WorkManager Thread**: Background notification tasks
- Flow and LiveData handle thread switching automatically

## Dependencies
- AndroidX Core & AppCompat
- Material Design Components
- Room Database
- Lifecycle Components (ViewModel, LiveData)
- Kotlin Coroutines & Flow
- WorkManager

## Design Patterns Used
1. **MVVM**: Separation of UI and business logic
2. **Repository Pattern**: Abstraction of data sources
3. **Observer Pattern**: LiveData and Flow for reactive updates
4. **Singleton Pattern**: Database instance
5. **Factory Pattern**: ViewModel creation
6. **Adapter Pattern**: RecyclerView adapter
