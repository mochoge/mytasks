# MyTasks - Your Digital Personal Assistant

A comprehensive Android task management application that helps you track and organize all your tasks efficiently.

## Features

### 📊 Summary Dashboard
- View quick statistics of all your tasks
- See counts for pending, in-progress, and completed tasks
- Color-coded summary cards for easy visualization

### 📝 Task Management
- **Create, Edit, and Delete Tasks**: Full CRUD operations for task management
- **Task Status Tracking**: 
  - Pending
  - In Progress
  - Completed
- **Priority Levels with Color Coding**:
  - High Priority (Red - #F44336)
  - Medium Priority (Orange - #FF9800)
  - Low Priority (Green - #4CAF50)

### 🔍 Task Views
- **All Tasks**: View all tasks in one place
- **Filtered Views**: Filter tasks by status using tabs
  - Pending tasks
  - In-progress tasks
  - Completed tasks
- **Detailed Task View**: See complete information about each task including title, description, priority, status, and due dates

### 🌙 Daily Brief Feature
- **Evening Reminders**: Receive notifications every evening at 7 PM
- **Natural Language Processing**: Share your daily brief in plain text
- **Automatic Task Extraction**: The app intelligently extracts tasks from your brief
- **Smart Categorization**: Tasks are automatically categorized with:
  - Priority levels based on keywords (urgent, important, etc.)
  - Due dates extracted from temporal references (tomorrow, next week, etc.)
  - Appropriate status assignment

## Technology Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room (SQLite)
- **UI Framework**: Material Design Components
- **Async Operations**: Kotlin Coroutines & Flow
- **Notifications**: WorkManager for scheduled reminders

## Project Structure

```
app/
├── src/main/
│   ├── java/com/mytasks/app/
│   │   ├── data/              # Data models and database
│   │   │   ├── Task.kt
│   │   │   ├── TaskDao.kt
│   │   │   ├── TaskDatabase.kt
│   │   │   └── Converters.kt
│   │   ├── repository/        # Data repository layer
│   │   │   └── TaskRepository.kt
│   │   ├── viewmodel/         # ViewModels for UI
│   │   │   └── TaskViewModel.kt
│   │   ├── ui/                # Activities and adapters
│   │   │   ├── MainActivity.kt
│   │   │   ├── TaskDetailActivity.kt
│   │   │   ├── DailyBriefActivity.kt
│   │   │   └── TaskAdapter.kt
│   │   ├── notification/      # Notification handlers
│   │   │   ├── DailyBriefWorker.kt
│   │   │   └── DailyBriefReminderReceiver.kt
│   │   ├── utils/             # Utility classes
│   │   │   └── TaskExtractor.kt
│   │   └── TaskApplication.kt
│   └── res/                   # Resources (layouts, strings, etc.)
└── build.gradle
```

## Building the App

### Prerequisites
- Android Studio Arctic Fox or newer
- JDK 17 or higher
- Android SDK with API level 34

### Build Steps
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or physical device

```bash
./gradlew assembleDebug
```

## Usage

### Managing Tasks
1. **Add a Task**: Tap the floating action button (+) on the main screen
2. **View Tasks**: Use tabs to filter tasks by status
3. **Edit a Task**: Tap on any task card to view/edit details
4. **Update Status**: Change task status from Pending → In Progress → Completed

### Daily Brief
1. **Access**: Tap the Daily Brief icon in the toolbar or wait for the evening notification
2. **Write**: Share how your day went in natural language
3. **Submit**: The app will automatically extract and create tasks from your text

#### Examples of Daily Brief Input:
- "I need to call John tomorrow about the project"
- "Must complete the urgent report by Friday"
- "Should schedule a meeting with the team next week"

## Permissions Required
- `POST_NOTIFICATIONS`: To send daily brief reminders
- `SCHEDULE_EXACT_ALARM`: To schedule precise notification times

## Future Enhancements
- Task reminders and notifications
- Task categories and tags
- Search functionality
- Data export/import
- Cloud synchronization
- Subtasks support
- Recurring tasks

## License
This project is available for personal and educational use.