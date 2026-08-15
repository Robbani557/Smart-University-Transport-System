SMART UNIVERSITY TRANSPORT SYSTEM

University Academic Project Transport Management, Route Management, Bus
Allocation and Student Seat Booking

1. PROJECT OVERVIEW

The Smart University Transport System is a university transportation
management application designed for both students and administrators.

Students can access transport information and book seats, while
administrators can manage students, buses, routes, bookings, schedules
and bus allocation.

The project demonstrates GUI development, object-oriented programming,
data management, application integration, debugging and teamwork.

2. MAIN FEATURES

STUDENT SIDE

-   Student login
-   Student dashboard
-   Transport and route information
-   Available seat information
-   Seat booking
-   Booking confirmation
-   My Bookings
-   Booking status information
-   Upcoming trip information
-   Dashboard booking statistics

ADMIN SIDE

-   Admin login
-   Admin dashboard
-   Manage Students
-   Manage Buses
-   Manage Routes
-   Bus Allocation
-   Booking management
-   Schedule management
-   Reports
-   Settings

3. STUDENT FEATURES

STUDENT LOGIN

Students enter the Student workflow through the application’s
role-selection and student-login process.

STUDENT DASHBOARD

The dashboard provides information such as:

-   Upcoming Trip
-   Available Seats
-   Available Routes
-   My Bookings

SEAT BOOKING

Students can select available transport and book a seat. After a
successful booking, the booking information is stored and reflected in
the student’s booking information.

MY BOOKINGS

Students can view their existing transport bookings and related
information. The dashboard booking counter is connected to booking data
and updates after successful bookings.

4. ADMIN FEATURES

ADMIN LOGIN

Administrators enter the administration workflow through the Admin
login.

ADMIN DASHBOARD

The Admin Dashboard provides access to:

-   Manage Students
-   Manage Buses
-   Manage Routes
-   Bus Allocation
-   Schedules
-   Bookings
-   Reports
-   Settings

MANAGE STUDENTS

-   View students
-   Add students
-   Edit student information
-   Delete students
-   Search student records

MANAGE BUSES

-   View buses
-   Add buses
-   Edit bus information
-   Delete buses
-   Search buses
-   Display bus capacity
-   Display driver information
-   Display availability/status

MANAGE ROUTES

-   View routes
-   Add routes
-   Edit routes
-   Delete routes
-   Search routes
-   Display student demand
-   Display bus capacity
-   Calculate required buses
-   Display allocated buses
-   Display allocation status

BUS ALLOCATION

The administrator can select a route and travel time. The system
determines student demand, calculates the number of buses required and
allocates available buses.

BOOKING MANAGEMENT

The administration side provides access to booking information and
related transport details.

SCHEDULE MANAGEMENT

A schedule-management area is included for university transport
scheduling.

REPORTS AND SETTINGS

Administrative areas are included for reporting and system
configuration.

5. BUS ALLOCATION LOGIC

Required Buses:

    Required Buses = CEILING(Students / Bus Capacity)

Allocation Difference:

    Difference = Allocated Buses - Required Buses

Interpretation:

    Negative Difference
    More buses are required.

    Zero Difference
    The allocation is sufficient.

    Positive Difference
    Extra buses have been allocated.

6. DATA MODEL

Important project entities include:

-   Student
-   Booking
-   Bus
-   Route
-   TransportData
-   AppData
-   Booking Manager
-   Bus Allocation Manager
-   Allocation Controller

The transport data connects students, bookings, buses and routes so that
student demand can be used for transport allocation.

7. APPLICATION FLOW

                         APPLICATION
                              |
                              v
                       SPLASH / ENTRY
                              |
                              v
                    SELECT USER TYPE
                         /                                /                                 v            v
                   STUDENT        ADMIN
                       |            |
                       v            v
                 STUDENT LOGIN   ADMIN LOGIN
                       |            |
                       v            v
              STUDENT DASHBOARD  ADMIN DASHBOARD
                       |            |
                       |       +----+----+---------+
                       |       |         |         |
                       |       v         v         v
                       |    Students   Buses     Routes
                       |                         |
                       |                         v
                       |                    Allocation
                       |
                       v
                  BOOK A SEAT
                       |
                       v
                BOOKING CONFIRMED
                       |
                       v
                  MY BOOKINGS

8. TECHNOLOGY STACK

Programming Language: - Java

GUI Technologies: - Java Swing - JavaFX

IDE: - Apache NetBeans

Build System: - Apache Ant / NetBeans project build system

9. USER INTERFACE

The application uses a clean university-management design.

Admin pages use: - Blue navigation sidebar - White top bar - Light
background - White content cards - Clean tables - Search controls - Blue
primary buttons - Status indicators - Consistent typography

Student pages use: - Dashboard summary cards - Transport information -
Booking information - User information - Clean panels and tables

10. PROJECT STRUCTURE

SmartUniversityTransport/ | +– src/ | | | +– admin/ | | +–
AdminDashboard.java | | +– ManageStudents.java | | +– ManageBuses.java |
| +– ManageRoutes.java | | +– BusAllocation.java | | +– … | | | +–
student/ | | +– StudentDashboardPanel.java | | +– … | | | +– model/ | |
+– Student.java | | +– Booking.java | | +– Bus.java | | +– Route.java |
| +– … | | | +– data/ | | +– AppData.java | | +– TransportData.java | |
+– … | | | +– transport/ | +– model/ | +– ui/ | +– nbproject/ +–
build.xml +– README.txt

11. TEAM MEMBERS AND CLEAR CONTRIBUTIONS

MEMBER 1 - PRIMARY CONTRIBUTOR

Name: Md. Mehedi Hasan ID: 251-15-279 Role: Primary Developer / Lead
Contributor

Md. Mehedi Hasan completed the majority of the project’s implementation,
integration, debugging and final refinement.

Major contribution areas:

-   Overall project development and integration
-   Application flow and module integration
-   Student-side workflow development and refinement
-   Student dashboard development
-   Student booking-flow implementation
-   My Bookings functionality
-   Dashboard booking-count integration
-   Admin login workflow
-   Admin / Student role-selection flow
-   Admin Dashboard development
-   Admin-side module integration
-   Manage Students functionality
-   Manage Buses functionality
-   Manage Routes functionality
-   Bus Allocation interface and integration
-   Shared transport-data integration
-   Debugging compilation and integration problems
-   Resolving cross-module issues
-   UI/UX improvement and visual consistency
-   Testing and refinement across major modules
-   Final integration of the major project components

Md. Mehedi Hasan handled the largest share of the overall development
and completed substantial integration and debugging work after the
individual parts were developed.

MEMBER 2 - STUDENT SIDE

Name: Md. Nishan ID: 251-15-209 Role: Student-Side Developer

Md. Nishan completed his assigned portion of the project by working
primarily on the Student side of the Smart University Transport System.

Major contribution areas:

-   Student-side application workflow
-   Student login / student access flow
-   Student dashboard
-   Student transport information
-   Route information presentation
-   Available transport information
-   Student seat-booking workflow
-   Booking interaction and confirmation
-   My Bookings functionality
-   Student booking information
-   Student-side UI components
-   Student-side testing and refinement

His work established the main Student-facing portion of the application
and provided the foundation for students to interact with university
transport services.

MEMBER 3 - ADMIN SIDE, FIRST HALF

Name: Golam Robbani ID: 251-15-557 Role: Admin-Side Developer

Md. Golam Robbani completed his assigned portion of the project on the
Admin side, covering approximately one half of the administrative
functionality.

Major contribution areas:

-   Admin-side workflow
-   Admin Dashboard functionality
-   Administrative navigation
-   Student management
-   Student record presentation
-   Student search functionality
-   Student administrative operations
-   Part of bus management
-   Part of route management
-   Administrative tables
-   Admin-side controls and actions
-   Testing and refinement of assigned Admin modules

His work contributed a major portion of the administrative management
layer used by university administrators.

MEMBER 4 - ADMIN SIDE, SECOND HALF + TRANSPORT

Name: Mahbubul Haque ID: 251-15-417 Role: Admin-Side and Transport-Side
Developer

Md. Mahbubul Haque completed the other major portion of the Admin-side
work and also worked on the Transport-related portion of the project.

Major contribution areas:

-   Remaining Admin-side functionality
-   Bus management
-   Bus information handling
-   Transport resource management
-   Route-related transport information
-   Bus capacity information
-   Bus availability information
-   Transport data organization
-   Bus and route data integration
-   Transport management UI components
-   Support for bus allocation workflows
-   Testing and refinement of assigned Admin and Transport modules

His work helped connect the administrative interface with the core
transport resources, especially buses, routes and related transport
information.

MEMBER 5

Name: Abdur Rahim ID: 251-15-714

No additional development contribution is listed for this member in this
project report.

12. TEAM CONTRIBUTION SUMMARY

The team initially divided the project work across three major areas:

1.  Student-side development
2.  Admin-side development
3.  Transport/resource management

Contribution distribution:

Md. Mehedi Hasan

Primary contributor and lead developer. Completed the majority of
overall implementation, integration, debugging and final refinement.

Md. Nishan

Completed the Student-side portion, including student workflow,
dashboard, transport information, seat booking and My Bookings
functionality.

Golam Robbani

Completed approximately the first half of the Admin-side development,
including Admin Dashboard, student management and parts of
administrative management.

Mahbubul Haque

Completed the other major half of the Admin-side development and worked
on the Transport-side portion, including bus, route and
transport-resource management.

Abdur Rahim

Listed as a project team member. No additional development contribution
is claimed in this report.

13. CONTRIBUTION STATEMENT

The team members who contributed to development completed their assigned
portions of the project.

After the individual parts were developed, the application required
integration, debugging, UI refinement and cross-module work.

Md. Mehedi Hasan completed the largest share of this final
implementation and integration effort and brought together the major
Student, Admin and Transport components into the working project.

The contribution section is intended to give clear academic credit to
each member for the part they completed while accurately identifying the
larger share of implementation and integration completed by the primary
contributor.

14. SAMPLE TRANSPORT DATA

The project contains demonstration transport data for development and
testing.

Example routes include:

-   Mirpur
-   Dhanmondi
-   Uttara
-   Mohammadpur
-   Badda
-   Jatrabari
-   Gulshan
-   Rampura

Example bus resources demonstrate:

-   Bus capacity
-   Driver information
-   Availability
-   Route allocation

15. HOW TO RUN

1.  Install a compatible Java JDK.

2.  Install Apache NetBeans.

3.  Open SmartUniversityTransport in NetBeans.

4.  Allow NetBeans to load the project configuration.

5.  Clean and Build the project.

6.  Run the application.

7.  Start from the splash / entry screen.

8.  Select ADMIN or STUDENT.

9.  Continue to the appropriate login panel.

10. TESTING CHECKLIST

STUDENT: [ ] Student login [ ] Student dashboard [ ] Route viewing [ ]
Transport availability [ ] Seat booking [ ] Booking confirmation [ ] My
Bookings [ ] Booking count update [ ] Upcoming trip information

ADMIN: [ ] Admin login [ ] Admin dashboard [ ] Manage Students [ ]
Student search [ ] Add / edit / delete student [ ] Manage Buses [ ] Bus
search [ ] Add / edit / delete bus [ ] Manage Routes [ ] Route search [
] Add / edit / delete route [ ] Required-bus calculation [ ] Bus
allocation [ ] Allocation status [ ] Booking management [ ] Schedule
management

17. CURRENT DEVELOPMENT STATUS

The Smart University Transport System is an academic project and may
continue to receive improvements.

Some areas may contain demonstration data, placeholder sections, modules
under further integration, or features planned for future development.

The project should therefore be considered an academic software project
and not a production-ready university transportation platform.

18. FUTURE IMPROVEMENTS

Potential future improvements include:

-   Database integration
-   Persistent user accounts
-   Secure password hashing
-   Real-time seat availability
-   Real-time bus tracking
-   GPS integration
-   Driver management
-   Automated schedule generation
-   Email and SMS notifications
-   QR-code boarding
-   Digital student transport pass
-   Advanced reports
-   PDF/Excel report export
-   Improved user permissions
-   Backend/API integration
-   Cloud deployment
-   Automated testing
-   Centralized data persistence
-   Improved exception handling
-   Removal of legacy/duplicate UI components

19. ACADEMIC PURPOSE

This project was developed as a university academic project to
demonstrate:

-   Object-oriented programming
-   GUI application development
-   Event-driven programming
-   Data management
-   CRUD operations
-   Application architecture
-   Software integration
-   Problem solving
-   Debugging
-   Team collaboration
-   User-interface design

The project demonstrates how a university transportation process can be
organized into a digital management and booking system.

20. ACADEMIC NOTICE

This project is an academic university project intended for educational,
demonstration and coursework purposes.

It should not be represented as an officially deployed university
transport system unless separately authorized and deployed by the
relevant institution.

21. ACKNOWLEDGEMENT

The Smart University Transport System represents the combined work of
the project team across Student, Admin and Transport components.

Special acknowledgement is given to Md. Mehedi Hasan (251-15-279) for
completing the majority of the overall implementation, integration,
debugging and final refinement.

The contributions of Md. Nishan (251-15-209), Golam Robbani
(251-15-557), and Mahbubul Haque (251-15-417) are recognized for their
assigned Student, Admin and Transport-side development work.

Abdur Rahim (251-15-714) is included as a member of the project team.

============================================================ SMART
UNIVERSITY TRANSPORT SYSTEM University Academic Project
============================================================

TEAM

1.  Md. Mehedi Hasan - 251-15-279 Primary Contributor

2.  Md. Nishan - 251-15-209 Student-Side Developer

3.  Golam Robbani - 251-15-557 Admin-Side Developer - First Half

4.  Mahbubul Haque - 251-15-417 Admin-Side + Transport Developer -
    Second Half

5.  Abdur Rahim - 251-15-714 Team Member

============================================================
