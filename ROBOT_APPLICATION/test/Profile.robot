
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
edit_password_invalid
    Make Valid Login
    wait    3
    click icon by class    p-menubar-root-list
    click text    Profile
    wait    1
    click edit profile    Password    Edit
    type text in label    ala1234jilani    Current Password
    type text in label    122    New Password
    type text in label    122    Confirm Password
    wait    1
    verify texxt span    Password length must contain at least 8 characters
    wait    1
edit_password_valid
    Make Valid Login
    wait    2
    click icon by class    p-menubar-root-list
    click text    Profile
    click edit profile    Password    Edit
    type text in label    ala1234jilani    Current Password
    type text in label    ala1234jilani    New Password
    type text in label    ala1234jilani    Confirm Password
    click button with Name    Save
    verify text alert popups    password updated successfully
    wait    1
edit_email
    Make Valid Login
    wait    2
    click icon by class    p-menubar-root-list
    click text    Profile
    wait    1
    click edit profile    E-mail    Edit
    type text with name    ${EMPTY}    email
    clear with backspace    30
    type text with name    ala.jilani@esprit.tn    email
    click icon by class    pi-check
    verify text in table    E-mail    ala.jilani@esprit.tn
edit_Name
    Make Valid Login
    wait    2
    click icon by class    p-menubar-root-list
    click text    Profile
    wait    1
    click edit profile    Name    Edit
    type text with name    ${EMPTY}    firstName
    clear with backspace    20
    type text with name    ALA    firstName
    type text with name    ${EMPTY}    LastName    
    clear with backspace    20
    type text with name    JILANI    LastName    
    click icon by class    pi-check
    verify text in table    Name    ALA JILANI
edit_username
    Make Valid Login
    wait    2
    click icon by class    p-menubar-root-list
    click text    Profile
    wait    1
    click edit profile    Username    Edit
    type text with name    ${EMPTY}    userName
    clear with backspace    24
    type text with name    alajilani    userName
    click icon by class    pi-check
    verify text in table    Username    alajilani
