Owned by the Login module member - not implemented here.

Integration point: once a login.LoginFrame class exists, launch it from
Main.java instead of MainFrame directly, and have a successful login call:

    new component.MainFrame().setVisible(true);

The Sidebar's Logout button already has a marked integration point
(see Sidebar.NavigationListener#onLogout in MainFrame.java) for returning
to the login screen instead of exiting the app.
