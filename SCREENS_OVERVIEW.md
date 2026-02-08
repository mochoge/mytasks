# MyTasks - App Screens Overview

## Visual Guide to the Application

This document provides a text-based overview of the app's screens and their layouts.

---

## Screen 1: Main Activity (Home Screen)

```
╔═══════════════════════════════════════════╗
║  MyTasks                    ⋮ Daily Brief ║
╠═══════════════════════════════════════════╣
║                                           ║
║  ┌──────────┐ ┌──────────┐ ┌──────────┐  ║
║  │    5     │ │    3     │ │    12    │  ║
║  │  Pending │ │In Progress│ │Completed │  ║
║  │   🟠     │ │    🔵    │ │    🟢    │  ║
║  └──────────┘ └──────────┘ └──────────┘  ║
║                                           ║
║  ┌─────────────────────────────────────┐ ║
║  │ All │Pending│In Progress│Completed  │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║  ┌─────────────────────────────────────┐ ║
║  │▌Call client about proposal          │ ║
║  │▌Meeting at 2pm tomorrow             │ ║
║  │▌High │ IN_PROGRESS │ Due: Jan 15    │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║  ┌─────────────────────────────────────┐ ║
║  │▌Finish quarterly report             │ ║
║  │▌Review and submit financials        │ ║
║  │▌Medium │ PENDING │ Due: Jan 20      │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║  ┌─────────────────────────────────────┐ ║
║  │▌Update documentation                │ ║
║  │▌Add new features to user guide      │ ║
║  │▌Low │ PENDING │ No due date         │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║                                      [+]  ║
╚═══════════════════════════════════════════╝

Legend:
▌ = Color-coded priority bar (Red/Orange/Green)
⋮ = Menu button
[+] = Floating action button to add new task
```

**Features:**
- Summary cards showing task counts at top
- Tab layout for filtering tasks
- Scrollable list of task cards
- Each card shows: title, description, priority, status, due date
- Color-coded vertical bar on left indicates priority
- Tap any card to open task details
- Tap + button to create new task
- Tap menu to access Daily Brief

---

## Screen 2: Task Detail Activity (Create/Edit Task)

```
╔═══════════════════════════════════════════╗
║  ← New Task                               ║
╠═══════════════════════════════════════════╣
║                                           ║
║  Task Title                               ║
║  ┌─────────────────────────────────────┐ ║
║  │ Call client about proposal          │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║  Description                              ║
║  ┌─────────────────────────────────────┐ ║
║  │ Discuss the new project proposal    │ ║
║  │ and timeline expectations           │ ║
║  │                                     │ ║
║  │                                     │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║  Priority                                 ║
║  ┌─────────────────────────────────────┐ ║
║  │ High                            ▼   │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║  Status                                   ║
║  ┌─────────────────────────────────────┐ ║
║  │ PENDING                         ▼   │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║  ┌─────────────────────────────────────┐ ║
║  │         SAVE TASK                   │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║  ┌─────────────────────────────────────┐ ║
║  │         DELETE TASK                 │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
╚═══════════════════════════════════════════╝

Legend:
← = Back button
▼ = Dropdown spinner
```

**Features:**
- Text input for task title (required)
- Multi-line text input for description
- Priority dropdown: High / Medium / Low
- Status dropdown: Pending / In Progress / Completed
- Save button to create/update task
- Delete button (only shown when editing existing task)
- Back arrow returns to main screen

---

## Screen 3: Daily Brief Activity

```
╔═══════════════════════════════════════════╗
║  ← Daily Brief                            ║
╠═══════════════════════════════════════════╣
║                                           ║
║  How was your day?                        ║
║                                           ║
║  Share a brief about your day. I'll       ║
║  extract any tasks mentioned and add      ║
║  them to your task list.                  ║
║                                           ║
║  Enter your daily brief                   ║
║  ┌─────────────────────────────────────┐ ║
║  │ Today was productive. Had a great   │ ║
║  │ meeting with the team. I need to    │ ║
║  │ call John tomorrow about the urgent │ ║
║  │ proposal. Must finish the report by │ ║
║  │ Friday. Should also schedule a team │ ║
║  │ retrospective next week when        │ ║
║  │ possible.                           │ ║
║  │                                     │ ║
║  │                                     │ ║
║  │                                     │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
║  💡 Tip: Mention tasks like "I need to   ║
║  call John tomorrow" or "Must complete    ║
║  report by Friday"                        ║
║                                           ║
║  ┌─────────────────────────────────────┐ ║
║  │         SUBMIT BRIEF                │ ║
║  └─────────────────────────────────────┘ ║
║                                           ║
╚═══════════════════════════════════════════╝

Legend:
← = Back button
💡 = Tip icon
```

**Features:**
- Large multi-line text input for daily brief
- Instructional text explaining the feature
- Example tip showing how to write briefs
- Submit button to process the brief
- Natural language processing extracts tasks automatically
- Returns to main screen after submission
- Shows toast with number of tasks extracted

---

## Notification

```
┌─────────────────────────────────────┐
│ 🔔 MyTasks              7:00 PM     │
├─────────────────────────────────────┤
│ Daily Brief Time                    │
│ How was your day? Share your brief  │
│ to track tasks.                     │
└─────────────────────────────────────┘
```

**Features:**
- Appears every evening at 7:00 PM
- Tap to open Daily Brief screen
- Dismissible if user is busy
- Scheduled using WorkManager for reliability

---

## Task Extraction Examples

### Input Text:
```
Today was busy. I need to call John tomorrow about 
the urgent project. Must complete the report by Friday. 
Should also update the documentation when possible.
```

### Extracted Tasks:

**Task 1:**
- Title: "Call John about the project"
- Priority: HIGH (keywords: urgent)
- Status: PENDING
- Due Date: Tomorrow

**Task 2:**
- Title: "Complete the report"
- Priority: HIGH (keyword: must)
- Status: PENDING
- Due Date: Friday

**Task 3:**
- Title: "Update the documentation"
- Priority: LOW (keyword: when possible)
- Status: PENDING
- Due Date: Not specified

---

## Color Coding System

### Priority Colors

**High Priority** 🔴
- Color: Red (#F44336)
- Use: Urgent, critical, time-sensitive tasks
- Visual: Red vertical bar on task card

**Medium Priority** 🟠
- Color: Orange (#FF9800)
- Use: Normal tasks, regular deadlines
- Visual: Orange vertical bar on task card

**Low Priority** 🟢
- Color: Green (#4CAF50)
- Use: Nice-to-have, future items
- Visual: Green vertical bar on task card

### Status Colors (in Summary)

- **Pending**: Orange counter (#FF9800)
- **In Progress**: Blue counter (#2196F3)
- **Completed**: Green counter (#4CAF50)

---

## Navigation Flow

```
Main Screen (Home)
    ├─→ [+] Button → Task Detail (Create)
    │                    └─→ Save → Return to Main
    │
    ├─→ Tap Task Card → Task Detail (Edit)
    │                    ├─→ Save → Return to Main
    │                    └─→ Delete → Return to Main
    │
    ├─→ Menu → Daily Brief
    │            └─→ Submit → Return to Main
    │
    └─→ Tabs → Filter View (Same Screen)
                ├─→ All
                ├─→ Pending
                ├─→ In Progress
                └─→ Completed

Notification (7 PM)
    └─→ Tap → Daily Brief
              └─→ Submit → Main Screen
```

---

## User Interactions Summary

### Main Screen
- **Tap task card** → View/edit task
- **Tap + button** → Create new task
- **Tap menu** → Open Daily Brief
- **Tap tabs** → Filter tasks by status
- **Scroll** → See more tasks

### Task Detail Screen
- **Type in fields** → Enter task information
- **Tap dropdowns** → Select priority/status
- **Tap Save** → Create or update task
- **Tap Delete** → Remove task
- **Tap back** → Return without saving

### Daily Brief Screen
- **Type text** → Enter daily summary
- **Tap Submit** → Extract and create tasks
- **Tap back** → Return without submitting

---

## Data Persistence

All tasks are stored locally in a Room SQLite database:
- Survives app restarts
- Fast query performance
- Type-safe access
- Observable with Flow

---

## Responsive Design

The app adapts to different screen sizes:
- Portrait orientation optimized
- Scrollable content areas
- Material Design responsive layouts
- Touch-friendly button sizes (48dp minimum)

---

## Accessibility Features

- High contrast text
- Material Design touch targets
- Clear visual hierarchy
- Descriptive labels
- Color + text for priority (not just color)

---

This visual overview provides a complete picture of how users interact with the MyTasks application!
