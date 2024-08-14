
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
    wait    3
    click text    Settings
    click text    User Management
    click button with Name    Add User
    type text in label    aa2    First Name
    type text in label    22jj.jj@gmail.comù    Email
    type text in label    saha255    User Name
    type text in label    E2    Last Name
    type text in label    22    Phone Number
    type text in label    Investor    Role
    select item in list    Investor
    click button with Name    Save
    verify text alert popups    User Saved
add_user_invalid(same username or email)
    Make Valid Login
    wait    3
    click text    Settings
    click text    User Management
    click button with Name    Add User
    type text in label    ala    First Name
    type text in label    ala.jilani@esprit.tn    Email
    type text in label    alajilani    User Name
    type text in label    E    Last Name
    type text in label    200    Phone Number
    type text in label    Investor    Role
    select item in list    Investor
    click button with Name    Save
    verify text alert popups    UserName already exist
edit_user
    Make Valid Login
    wait    3
    click text    Settings
    click text    User Management
    type text for search    a777    Global Filter
    select p table row with name    laouer
    click icon by class    pi-pencil
    clear table selection
    type text in label    ${EMPTY}    First Name
    clear with backspace    20
    type text in label    anis    First Name
    click button with Name    Save
    verify text alert popups    User Saved
delete_user(to_check:problem_deleting)
    Make Valid Login
    wait    3
    click text    Settings
    click text    User Management
    type text for search    anis    Global Filter
    select p table row with name    laouer
    click icon by class    pi-trash
    clear table selection
    click button with Name    Yes
    type text in label    ALA JILANI    New Owner
    select item in list    ALA JILANI
    click button with Name    Save
