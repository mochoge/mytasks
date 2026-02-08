# MyTasks Setup Guide

## Quick Start

### Prerequisites
- **Android Studio**: Arctic Fox (2020.3.1) or newer
- **JDK**: Version 17 or higher
- **Android SDK**: API Level 24 (minimum) to 34 (target)
- **Gradle**: 8.1.0 or higher (included in wrapper)

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/mochoge/mytasks.git
   cd mytasks
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory
   - Wait for Gradle sync to complete

3. **Build the Project**
   ```bash
   ./gradlew build
   ```
   Or use Android Studio's Build menu: `Build > Make Project`

4. **Run the App**
   - Connect an Android device (API 24+) or start an emulator
   - Click the "Run" button in Android Studio
   - Or use command line:
     ```bash
     ./gradlew installDebug
     ```

## Building from Command Line

### Debug Build
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

### Release Build
```bash
./gradlew assembleRelease
```
Output: `app/build/outputs/apk/release/app-release-unsigned.apk`

### Install on Device
```bash
./gradlew installDebug
```

### Run Tests
```bash
./gradlew test
```

## Project Configuration

### Minimum Requirements
- **Min SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Compile SDK**: API 34

### Key Dependencies
- Kotlin 1.9.0
- AndroidX Core 1.12.0
- Material Design 1.10.0
- Room Database 2.6.0
- WorkManager 2.8.1

## First Run Setup

### Permissions
The app will request the following permissions on first run:
- **POST_NOTIFICATIONS**: For daily brief reminders
- **SCHEDULE_EXACT_ALARM**: For precise notification timing at 7 PM

Grant these permissions for full functionality.

### Initial Usage

1. **Launch the App**
   - You'll see an empty task list with a summary showing 0 tasks

2. **Add Your First Task**
   - Tap the "+" floating action button
   - Enter task title and description
   - Select priority (High/Medium/Low)
   - Select status (Pending/In Progress/Completed)
   - Tap "Save Task"

3. **View Tasks**
   - Use tabs to filter: All, Pending, In Progress, Completed
   - Tap any task card to edit

4. **Daily Brief**
   - Tap the menu icon (three dots) in the toolbar
   - Select "Daily Brief"
   - Or wait for the 7 PM notification
   - Write your daily summary in natural language
   - Tap "Submit Brief" to auto-extract tasks

## Features Overview

### Summary Dashboard
The main screen shows three key metrics:
- **Pending**: Tasks waiting to be started (Orange)
- **In Progress**: Tasks currently being worked on (Blue)
- **Completed**: Finished tasks (Green)

### Task Management
- **Create**: Tap the + button
- **Edit**: Tap any task card
- **Delete**: Open task details and tap "Delete Task"
- **Filter**: Use the tabs to view specific task categories

### Priority Color Coding
Tasks are visually distinguished by priority:
- 🔴 **High Priority**: Red indicator
- 🟠 **Medium Priority**: Orange indicator
- 🟢 **Low Priority**: Green indicator

### Daily Brief Task Extraction
Write naturally and the app will extract tasks:

**Example Input:**
```
Today was productive. I need to call John tomorrow 
about the urgent project proposal. Must finish the 
report by Friday. Should also schedule a team 
meeting next week when possible.
```

**Extracted Tasks:**
1. "Call John about the project proposal"
   - Priority: HIGH (keyword: urgent)
   - Due: Tomorrow
   
2. "Finish the report"
   - Priority: HIGH (keyword: must)
   - Due: Friday
   
3. "Schedule a team meeting"
   - Priority: LOW (keyword: when possible)
   - Due: Next week

## Troubleshooting

### Gradle Sync Failed
```bash
./gradlew clean
./gradlew build --refresh-dependencies
```

### App Crashes on Launch
- Check logcat in Android Studio
- Verify minimum SDK version (API 24+)
- Ensure all permissions are granted

### Notifications Not Working
- Check system notification settings
- Verify POST_NOTIFICATIONS permission
- Ensure battery optimization is disabled for the app

### Database Issues
- Clear app data: Settings > Apps > MyTasks > Storage > Clear Data
- Uninstall and reinstall the app

## Development Tips

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable names
- Add comments for complex logic

### Adding New Features
1. Update data models in `data/` package
2. Add repository methods if needed
3. Update ViewModel to expose new data
4. Create/update UI components
5. Test thoroughly

### Database Changes
If you modify the Task model:
1. Update the `@Entity` class
2. Increment database version in `TaskDatabase.kt`
3. Implement migration if preserving data
4. Or use destructive migration for development

## Support

### Common Issues
- **Build errors**: Clean and rebuild project
- **Dependency issues**: Sync Gradle files
- **Runtime crashes**: Check logcat for stack traces

### Resources
- [Android Developer Guide](https://developer.android.com)
- [Kotlin Documentation](https://kotlinlang.org/docs)
- [Material Design Guidelines](https://material.io/design)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)

## Next Steps

After successful setup:
1. ✅ Explore the app features
2. ✅ Create some test tasks
3. ✅ Try the daily brief feature
4. ✅ Customize as needed
5. ✅ Build and share with others

Happy task tracking! 📝✨
