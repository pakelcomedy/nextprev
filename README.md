# Pagination with Next and Previous Buttons

![Pagination Example](https://github.com/pakelcomedy/nextprev/assets/92992500/0a45ac81-3fb5-45d9-958c-e1d225ad2e88)

This Java Swing application demonstrates pagination functionality using "Next" and "Previous" buttons with a table to display data.

## Features

- **Next Button**: Clicking the "Next" button loads the next page of data in the table.
- **Previous Button**: Clicking the "Previous" button loads the previous page of data in the table.
- **Table**: The table displays paginated data retrieved from a database. Each page contains a fixed number of rows.

## How It Works

1. **Next Button**: 
   - When the "Next" button is clicked, the application increments the current page counter.
   - It then fetches data corresponding to the next page from the database and updates the table to display the new data.
   - The pagination label is also updated to reflect the current page.

2. **Previous Button**:
   - When the "Previous" button is clicked, the application decrements the current page counter if the current page is greater than 1.
   - It then fetches data corresponding to the previous page from the database and updates the table to display the new data.
   - The pagination label is also updated to reflect the current page.

3. **Table**:
   - The table component is used to display the paginated data.
   - Each page contains a fixed number of rows, and the table updates dynamically as the user navigates between pages.

## Usage

To use this pagination functionality in your Java Swing application:
1. Clone or download this repository.
2. Integrate the `nextActionPerformed` and `prevActionPerformed` methods into your application.
3. Customize the `loadAttendanceData` method to fetch data from your database or data source.
4. Modify the table columns and row data as needed to suit your application's requirements.

## What's New?

- Added `updateTotalEntriesLabel` method for updating the label that displays information about the range of entries being displayed and the total number of entries.

![nextprev](https://github.com/pakelcomedy/nextprev/assets/92992500/d30d1ad8-d5f9-43fa-a191-200be3b97f6e)

Feel free to raise any issues or ask questions

---
