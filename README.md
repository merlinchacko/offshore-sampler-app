# Offshore Sampler RCP Application

This project is an **Eclipse Rich Client Platform (RCP)** application designed for managing ground samples. It provides a table view to display sample data and offers options to add, edit, or delete samples.

---

## Features

### 1. **Main Window**
- Displays all samples in a table format with the following columns:
  - Sample ID
  - Location
  - Date Collected
  - Unit Weight (kN/m³)
  - Water Content (%)
  - Shear Strength (kPa)

### 2. **Add/Edit/Delete Samples**
- **Add new samples** using a dialog.
- **Edit selected samples** directly.
- **Delete selected samples** from the table.

### 3. **Backend Integration**
- Loads location and sample data from a backend service (can be extended to save, update, and delete sample data as well).

### 4. **Validation**
- Ensures proper values for water content, unit weight, and shear strength.

---

## Requirements

- **Java Development Kit (JDK)**: Version 17 or later.
- **Eclipse IDE for RCP and RAP Developers**.
- **Eclipse RCP Target Platform**: Based on Eclipse 3.x.

### Additional Dependencies added in lib folder
- **Jackson**: Core, Datatype.jsr310, Annotations,


### 4. Run the Application
- First run offshore-ground-sampler spring boot application
- Right-click the offshore-sampler-rcp-app and select **Run As > Eclipse Application**.
- The application will open in a new Eclipse instance.

---

## Key Files and Structure

- **SampleView**: Displays the main table with sample data and buttons for managing samples.
- **SampleDialog**: Handles adding and editing samples.
- **SampleLabelProvider**: Dynamically provides values for each table column.
- **Sample and Location Models**: Represent the data structure of samples and locations.
- **MANIFEST.MF**: Defines dependencies and entry points for the application.

---

## Screenshots

### Main Window
The main table displaying all samples:
![Main Window](https://github.com/user-attachments/assets/5877c625-5280-42b6-8a8d-f1c647983235)

### Add/Edit Sample Dialog
Dialog for adding or editing a sample:
![Add/Edit Sample Dialog](https://github.com/user-attachments/assets/8a861433-ab23-49f8-8e8c-28a275ed914c)

---


