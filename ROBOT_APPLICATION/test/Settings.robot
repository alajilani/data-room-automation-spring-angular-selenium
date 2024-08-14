
*** Settings ***
Resource          Login/LoginPage.robot
Library           lib/Inputs.py
Library           lib/Clickables.py
Library           lib/Window.py

Library           lib/Selection.py
Library           lib/Verification.py

Test Teardown     Close All Browsers


*** Variables ***
*** Test Cases ***
add_user
    Make Valid Login
    refresh page    10
    liste deroulante    2
    click text    User Management
    click button with Name    Add User
    type text in label    aa    First Name
    type text in label    jj.jj@gmail.comù    Email
    type text in label    saha    User Name
    type text in label    E    Last Name
    type text in label    2    Phone Number
    type text in label    investor    Role
    wait    1
    select choice    2
    click text    Save
    verify text alert popups    User Saved
add_user_invalid(same username or email)
    Make Valid Login
    refresh page    10
    liste deroulante    2
    click text    User Management
    click button with Name    Add User
    type text in label    ala    First Name
    type text in label    ala.jilani@esprit.tn    Email
    type text in label    alajilani    User Name
    type text in label    E    Last Name
    type text in label    200    Phone Number
    type text in label    investor    Role
    wait    1
    select choice    3
    click text    Save
    verify text alert popups    UserName already exist
edit_user
    Make Valid Login
    refresh page    10
    liste deroulante    2
    click text    User Management
    type text for search    alla58    Global Filter
    select p table row with name    aaaa
    click icon    pi-pencil
    clear table selection
    select window    Edit User
    type text in label    1    First Name
    clear with backspace    20
    type text in label    alla    First Name
    click text    Save    
    clear selection
    verify text alert popups    User Saved
